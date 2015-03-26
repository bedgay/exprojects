/**
 * 
 */
package jp.co.mti.mixjuke.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.mti.mixjuke.web.external.response.BeginTokenResponse;
import jp.co.mti.mixjuke.web.external.response.SubscribeResponse;
import jp.co.mti.mixjuke.web.response.DataModel;
import jp.co.mti.mixjuke.web.response.ResultCode;
import jp.co.mti.mixjuke.web.utils.MixjukeUtils;
import jp.co.mti.mixjuke.web.utils.ResouceBundleHelper;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.jersey.api.client.Client;

/**
 * @author Xuan Nguyen
 * 
 */
@Controller
@RequestMapping("/serviceRegist")
public class SubscribeController extends AbstractWeb {

    private static final String AUTO_SUBSCRIBE_ERROR = "/web/serviceRegistSuccess?exist=1&service=free";
	private static final String AUTO_SUBSCRIBE_SUCCESS_ACTION = "/web/serviceRegistSuccess?exist=0&service=free";
	
	Client client = Client.create();
	ObjectMapper om = new ObjectMapper();
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView subscribeFreeService(HttpServletRequest request,
            HttpServletResponse response, 
            @RequestParam(value = "auto", required=false) String auto) {
    	if (auto != null && auto.equals(SERVICE_AUTO)) {
            String transaction = MixjukeUtils.getTransactionString();
            BeginTokenResponse tokenBeginResponse = null;
        	try {
        		om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        		tokenBeginResponse = this.beginToken(client, SUBCRIBE, request, response, om, transaction);
        		//CHG-S nhphuoc 140206 Fix bug 5646
        		//SubscribeResponse subscribeResponse = this.subscribeProcess(client, 
        	    //			MixjukeUtils.getObjectFromCookie(request, response, COOKIE_MUID_NAME).toString(), 
        		//		request, response, tokenBeginResponse, om, transaction);
        		String uid = request.getParameter(COOKIE_MUID_NAME);
        		if(uid == null){
        			Object cookie = MixjukeUtils.getObjectFromCookie(request, response, COOKIE_MUID_NAME);
        			if (cookie != null) 
        			 uid = cookie.toString();
        			else
        			 return new ModelAndView("subscribe-redirect", "redirect", AUTO_SUBSCRIBE_ERROR);	
        		} 
        		SubscribeResponse subscribeResponse = this.subscribeProcess(client,uid, 
        				request, response, tokenBeginResponse, om, transaction);
        		//CHG-E nhphuoc 140206
        	
//        		System.out
//		        .print("Output from Mopita Server when try to subcrible :\n*********\n"
//		                + subscribeResponse.toString() + "\n*********\n");
        		
        		if (subscribeResponse == null) {
        		} else if (MOPITA_CODE_NORMAL.equals(subscribeResponse.getResult()
        		                .getCode())) {
        	        return new ModelAndView("subscribe-redirect", "redirect", AUTO_SUBSCRIBE_SUCCESS_ACTION);
        		} else if (MOPITA_CODE_SUBSCRIBED.equals(subscribeResponse.getResult()
        		                .getCode())) {
        	        return new ModelAndView("subscribe-redirect", "redirect", AUTO_SUBSCRIBE_ERROR);
        		}
			} catch (Exception e) {
	             e.printStackTrace();
	            return new ModelAndView("subscribe-redirect", "redirect", AUTO_SUBSCRIBE_ERROR);
			} finally {
	            try {
	                // ********** Discard token **********
	                endToken(client, tokenBeginResponse.getToken(), om);
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
			}
        } else {
	        String uid = request.getParameter("uid");
	        if (uid == null || USER_NOT_LOGIN.equals(uid)) {
	            return new ModelAndView("login");
	        }
        }
        
        return new ModelAndView("subscribe");
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    DataModel submitAction(HttpServletRequest request,
            HttpServletResponse response) {
        String transaction = MixjukeUtils.getTransactionString();
        BeginTokenResponse tokenBeginResponse = null;
    	try {
    		om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    		tokenBeginResponse = this.beginToken(client, SIGUP, request, response, om, transaction);
    		SubscribeResponse subscribeResponse = this.subscribeProcess(client, 
    				MixjukeUtils.getObjectFromCookie(request, response, COOKIE_MUID_NAME).toString(), 
    				request, response, tokenBeginResponse, om, transaction);
    		if (subscribeResponse == null
    		        || !MOPITA_CODE_NORMAL.equals(subscribeResponse.getResult()
    		                .getCode())) {
    			System.out.println("Receive error from Mopita: "
                        + tokenBeginResponse.getResult().getCode());
	            return new DataModel(ResultCode.ERROR, ResouceBundleHelper.getMessage("subscrible_error"));
    		}
		} catch (Exception e) {
            System.out.println("Unexpected exception " + e);
            return new DataModel(ResultCode.ERROR, ResouceBundleHelper.getMessage("subscrible_error"));
		} finally {
            try {
                // ********** Discard token **********
            	if (tokenBeginResponse != null) {
            		endToken(client, tokenBeginResponse.getToken(), om);
            	}
            } catch (Exception e2) {
                System.out.println("Unexpected exception " + e2);
            }
		}

        return new DataModel(ResultCode.SUCCESS, request.getContextPath()
                + "/serviceRegistSuccess");
    }

}
