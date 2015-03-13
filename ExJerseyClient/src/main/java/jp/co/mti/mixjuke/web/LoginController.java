/**
 * 
 */
package jp.co.mti.mixjuke.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.web.external.request.Login;
import jp.co.mti.mixjuke.web.external.response.LoginRegisterResponse;
import jp.co.mti.mixjuke.web.request.LoginRequestBody;
import jp.co.mti.mixjuke.web.response.DataModel;
import jp.co.mti.mixjuke.web.response.ResultCode;
import jp.co.mti.mixjuke.web.utils.MixjukeUtils;
import jp.co.mti.mixjuke.web.utils.ResouceBundleHelper;
import nl.captcha.Captcha;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Xuan Nguyen
 * 
 */
@Controller
@RequestMapping("/login")
public class LoginController extends AbstractWeb {
    
	private Client client;

    /**
     * Get Login page.
     * 
     * @return Login page name.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView mopitaLogin(@RequestParam(value = "register", required=false) String register,
    		HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("login", "register", register);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    DataModel submitAction(LoginRequestBody inputLogin,
            HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(USERNAME, inputLogin.getUsername());
        Captcha captcha = (Captcha) request.getSession().getAttribute(
                Captcha.NAME);
        if (!captcha.isCorrect(inputLogin.getAnswer())) {
            return new DataModel(ResultCode.ERROR,
                    ResouceBundleHelper.getMessage("verify_code_incorrect"));
        }
        if (StringUtils.isBlank(inputLogin.getUsername())
                || StringUtils.isBlank(inputLogin.getPassword())) {
            return new DataModel(ResultCode.ERROR,
                    ResouceBundleHelper.getMessage("login_input_invalid"));
        }
        
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        String sig = null, req = null;
        LoginRegisterResponse loginResponse = null;
        try {
        	client = Client.create();
        	
            Login loginData = new Login(this.getVersion(), this.getAccessKey(),
                    MixjukeUtils.getTimeStampString(),
                    inputLogin.getUsername(), inputLogin.getPassword(), null,
                    null);

            String stringLogin = om.writeValueAsString(loginData);

            req = MixjukeUtils.getBase64URLEncoderString(stringLogin);
            sig = MixjukeUtils.getURLEncoderString(MixjukeUtils.getSignature(
                    stringLogin, this.getSecretKey()));

            WebResource webResource = client.resource(this.getBaseUrl())
                    .path(this.getLoginUrl()).queryParam("iai_sig", sig)
                    .queryParam("iai_req", req);

            String clientResponse = webResource.type(MediaType.TEXT_PLAIN).get(
                    String.class);

//            System.out.println("Request infomation................");
//            System.out.println("Date: "+ new Date());
//            System.out.println("Mopita ID: " + loginData.getIaiAcctid());
//            System.out.println("Json string:" + stringLogin);
//            System.out.println("iai_req: " + req);
//            System.out.println("iai_sig: "+ sig);
//            System.out.println("URI: "+webResource.getURI());
            
            System.out
                    .println("Output from Mopita Server when try to login :\n*********\n"
                            + clientResponse.toString() + "\n*********\n");

            loginResponse = om.readValue(clientResponse,
                    LoginRegisterResponse.class);

            // Check Error code in Response from Mopita
            if (loginResponse == null
                    || !MOPITA_CODE_NORMAL.equals(loginResponse.getResult()
                            .getCode())) {
                System.out.println("Receive error from Mopita: "
                        + loginResponse.getResult().getCode());
                return new DataModel(ResultCode.ERROR,
                        ResouceBundleHelper.getMessage("login_input_invalid"));
            }

            // Save session ID into Cookie
            MixjukeUtils.saveOrUpdateCookie(request, response,
                    this.getCookieDomain(), COOKIE_SESSION_NAME,
                    loginResponse.getMtisess());
            // Save username into Session to check logined already.
            MixjukeUtils.saveOrUpdateCookie(request, response,
                    this.getCookieDomain(), COOKIE_MUID_NAME,
                    loginResponse.getMuid()); 
            
            if (inputLogin.getRegister().equals(REGISTER_DEFAULT_VALUE)) {
                return new DataModel(ResultCode.SUCCESS, request.getContextPath()
                        + SERVICE_REGIST_AUTO_URL);
            }
            return new DataModel(ResultCode.SUCCESS, request.getContextPath()
                    + "/loginSuccess");
        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e);
            return new DataModel(ResultCode.ERROR,
                    ResouceBundleHelper.getMessage("login_input_invalid"));
        }

    }
}
