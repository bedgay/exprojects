/**
 * 
 */
package jp.co.mti.mixjuke.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.web.external.request.BeginToken;
import jp.co.mti.mixjuke.web.external.request.Register;
import jp.co.mti.mixjuke.web.external.response.BeginTokenResponse;
import jp.co.mti.mixjuke.web.external.response.LoginRegisterResponse;
import jp.co.mti.mixjuke.web.request.RegisterRequestBody;
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
import com.sun.jersey.api.client.WebResource;

/**
 * @author Xuan Nguyen
 * 
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends AbstractWeb {

    /**
     * Get register page.
     * 
     * @return Register page name.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView mopitaRegister(@RequestParam(value = "register", required=false) String register) {
        return new ModelAndView("register", "register", register);
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView mopitaRegisterConfirm(@RequestParam(value = "register", required=false) String register,
    		HttpServletRequest request,
            HttpServletResponse response) {
        if (MixjukeUtils.getObjectFromCookie(request, response, USERNAME) == null
                || MixjukeUtils
                        .getObjectFromCookie(request, response, PASSWORD) == null)
            return new ModelAndView("register", "register", register);
        return new ModelAndView("register-confirm", "register", register);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    DataModel submitAction(RegisterRequestBody inputRegister,
            HttpServletRequest request, HttpServletResponse response) {
        if (inputRegister.getCheckInputValid() != null) {
            return this.checkInputValid(inputRegister.getUsername(),
                    inputRegister.getPassword(), inputRegister.getRepassword(), 
                    inputRegister.getAnswer(), inputRegister.getRegister(), request, response);
        } else {
            return this.registerProgress(inputRegister.getUsername(),
                    inputRegister.getPassword(), inputRegister.getRegister(), 
                    request, response);
        }
    }

    private @ResponseBody
    DataModel checkInputValid(String username, String password,
    		String repassword, String answer, String registerVal,
            HttpServletRequest request,
            HttpServletResponse response) {
    	
//    	Captcha captcha = (Captcha) request.getSession().getAttribute(
//				Captcha.NAME);
//		if (!captcha.isCorrect(answer)) {
//			return new DataModel(ResultCode.ERROR,
//					ResouceBundleHelper.getMessage("verify_code_incorrect"));
//		}
		
        if (!MixjukeUtils.acctIdIsCorrectFormat(username)
                || !MixjukeUtils.passwordIsCorrectFormat(password)
                || !password.equals(repassword)) {
            return new DataModel(ResultCode.ERROR,
                    ResouceBundleHelper.getMessage("register_input_invalid"));
        }
        MixjukeUtils.saveOrUpdateCookie(request, response,
                this.getCookieDomain(), USERNAME, username);
        MixjukeUtils.saveOrUpdateCookie(request, response,
                this.getCookieDomain(), PASSWORD, password);
        if (registerVal.equals(REGISTER_DEFAULT_VALUE)) {
            return new DataModel(ResultCode.SUCCESS, request.getContextPath()
                    + "/register/confirm?register=" + registerVal);
        }
        return new DataModel(ResultCode.SUCCESS, request.getContextPath()
                + "/register/confirm");
    }

    private @ResponseBody
    DataModel registerProgress(String username, String password, String registerVal,
            HttpServletRequest request, HttpServletResponse response) {
        Client client = Client.create();
        WebResource webResource = null;
        String sigBeginToken = null, reqBeginToken = null, sigRegister = null, reqRegister = null;
        BeginTokenResponse tokenBeginResponse = null;
        ObjectMapper om = null;

        try {
            // **************** Begin Token progress ****************
            // Save transaction into Cookie
            String transaction = MixjukeUtils.getTransactionString();
            MixjukeUtils.saveOrUpdateCookie(request, response,
                    this.getCookieDomain(), COOKIE_TRANSACTION_NAME,
                    transaction);

            BeginToken beginToken = new BeginToken(this.getVersion(),
                    this.getAccessKey(), MixjukeUtils.getTimeStampString(),
                    this.getSiteID(), transaction, SIGUP, null);

            om = new ObjectMapper();
            om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            String stringBeginToken = om.writeValueAsString(beginToken);
            reqBeginToken = MixjukeUtils
                    .getBase64URLEncoderString(stringBeginToken);
            sigBeginToken = MixjukeUtils.getURLEncoderString(MixjukeUtils
                    .getSignature(stringBeginToken, this.getSecretKey()));

            webResource = client.resource(this.getBaseUrl())
                    .path(this.getBeginTokenUrl())
                    .queryParam("iai_sig", sigBeginToken)
                    .queryParam("iai_req", reqBeginToken);

            String clientResponse = webResource.type(MediaType.TEXT_PLAIN).get(
                    String.class);
            System.out
                    .println("Output from Mopita Server when try to begin token :\n*********\n"
                            + clientResponse.toString() + "\n*********\n");
            tokenBeginResponse = om.readValue(clientResponse,
                    BeginTokenResponse.class);

            // Check Error code in Response from Mopita
            if (tokenBeginResponse == null
                    || !MOPITA_CODE_NORMAL.equals(tokenBeginResponse
                            .getResult().getCode())) {
                System.out.println("Receive error from Mopita: "
                        + tokenBeginResponse.getResult().getCode());
                return new DataModel(ResultCode.ERROR,
                        ResouceBundleHelper
                                .getMessage("register_input_invalid"));
            }
            // Save token into session
            MixjukeUtils.saveOrUpdateSession(request, response,
                    SESSION_TOKEN_NAME, tokenBeginResponse.getToken());
            // **************** Register progress ****************
            Register register = new Register();
            register.setIaiAver(this.getVersion());
            register.setIaiAkey(this.getAccessKey());
            register.setIaiAtms(MixjukeUtils.getTimeStampString());
            register.setIaiSiteID(this.getSiteID());
            register.setIaiCpTransactionId(transaction);
            register.setIaiToken(tokenBeginResponse.getToken());
            register.setIaiAcctid(username);
            register.setIaiPwd(password);

            String stringRegister = om.writeValueAsString(register);
            reqRegister = MixjukeUtils
                    .getBase64URLEncoderString(stringRegister);
            sigRegister = MixjukeUtils.getURLEncoderString(MixjukeUtils
                    .getSignature(stringRegister, this.getSecretKey()));

            webResource = client.resource(this.getBaseUrl())
                    .path(this.getRegisterUrl())
                    .queryParam("iai_sig", sigRegister)
                    .queryParam("iai_req", reqRegister);

            clientResponse = webResource.type(MediaType.TEXT_PLAIN).get(
                    String.class);
            System.out
                    .println("Output from Mopita Server when try to register :\n*********\n"
                            + clientResponse.toString() + "\n*********\n");
            LoginRegisterResponse loginResponse = om.readValue(clientResponse,
                    LoginRegisterResponse.class);

            // Check Error code in Response from Mopita
            if (loginResponse == null
                    || !MOPITA_CODE_NORMAL.equals(loginResponse.getResult()
                            .getCode())) {
                System.out.println("Receive error from Mopita: "
                        + loginResponse.getResult().getCode());

                return new DataModel(ResultCode.ERROR,
                        ResouceBundleHelper
                                .getMessage("register_input_invalid"));
            }
            // Save session ID into Cookie
            MixjukeUtils.saveOrUpdateCookie(request, response,
                    this.getCookieDomain(), COOKIE_SESSION_NAME,
                    loginResponse.getMtisess());
            // Save username into Session to check logined already.
            MixjukeUtils.saveOrUpdateCookie(request, response,
                    this.getCookieDomain(), COOKIE_MUID_NAME,
                    loginResponse.getMuid()); 
        } catch (Exception e) {
            System.out.println("Unexpected exception " + e);
            return new DataModel(ResultCode.ERROR,
                    ResouceBundleHelper.getMessage("register_input_invalid"));
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

        if (registerVal.equals(REGISTER_DEFAULT_VALUE)) {
            return new DataModel(ResultCode.SUCCESS, request.getContextPath()
                    + SERVICE_REGIST_AUTO_URL);
        }
        return new DataModel(ResultCode.SUCCESS, request.getContextPath()
                + "/loginSuccess");
    }

}
