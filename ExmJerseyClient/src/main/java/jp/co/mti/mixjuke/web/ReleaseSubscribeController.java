/**
 * 
 */
package jp.co.mti.mixjuke.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.web.external.request.ReleaseSubscribe;
import jp.co.mti.mixjuke.web.external.request.Subscribe;
import jp.co.mti.mixjuke.web.external.response.BeginTokenResponse;
import jp.co.mti.mixjuke.web.external.response.ResultResponse;
import jp.co.mti.mixjuke.web.response.DataModel;
import jp.co.mti.mixjuke.web.response.ResultCode;
import jp.co.mti.mixjuke.web.utils.MixjukeUtils;
import jp.co.mti.mixjuke.web.utils.ResouceBundleHelper;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * @author Xuan Nguyen
 * 
 */
@Controller
@RequestMapping("/serviceRelease")
public class ReleaseSubscribeController extends AbstractWeb {
	private final String RELEASE_SUCCESS_PATH = "/serviceReleaseSuccess";
	Client client;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView releaseSubscribeFreeService(HttpServletRequest request,
			HttpServletResponse response) {
		String uid = request.getParameter("uid");
		if (uid == null || USER_NOT_LOGIN.equals(uid)) {
			return new ModelAndView("login");
		}
		// If auto=1 don't show confirmation info instead do service release
		// immediately.
		String auto = request.getParameter("auto");
		//CHG-S nhphuoc 140207 Using defined constant
		//if (auto != null && auto.equals("1")) {
		ModelMap model = new ModelMap();
		if (auto != null && auto.equals(SERVICE_AUTO)) {
	    //CHG-E nhphuoc
			System.out.println("Auto release subscribe!...");
			DataModel d = releaseSubscribleProgress(request, response);
			model.addAttribute("model", d);
			model.addAttribute("hidden", true);
			return new ModelAndView("release-subscribe",model);
		}
		model.addAttribute("hidden", false);
		return new ModelAndView("release-subscribe",model);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	DataModel submitAction(HttpServletRequest request,
			HttpServletResponse response) {
		return releaseSubscribleProgress(request, response);
	}

	private DataModel releaseSubscribleProgress(HttpServletRequest request,
			HttpServletResponse response) {
		client = Client.create();
		WebResource webResource = null;
		String sigReleaseSubscribe = null, reqReleaseSubscribe = null;
		BeginTokenResponse tokenBeginResponse = null;
		ObjectMapper om = null;
		String clientResponse = null;
		try {
			// **************** Begin Token progress ****************
			// Save transaction into Cookie
			String transaction = MixjukeUtils.getTransactionString();
			MixjukeUtils.saveOrUpdateCookie(request, response,
					this.getCookieDomain(), COOKIE_TRANSACTION_NAME,
					transaction);

			om = new ObjectMapper();
			om.configure(
					DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
					true);

			tokenBeginResponse = this.beginToken(client, RELEASE_SUBCRIBE,
					request, response, om, transaction);

			// Check Error code in Response from Mopita
			if (tokenBeginResponse == null
					|| !MOPITA_CODE_NORMAL.equals(tokenBeginResponse
							.getResult().getCode())) {
				System.out.println("Receive error from Mopita: "
						+ tokenBeginResponse.getResult().getCode());
				return new DataModel(ResultCode.ERROR,
						ResouceBundleHelper.getMessage("release_subscrible_error"));
			}
			// Save token into session
			MixjukeUtils.saveOrUpdateSession(request, response,
					SESSION_TOKEN_NAME, tokenBeginResponse.getToken());

			// **************** Release subscribe progress ****************
			ReleaseSubscribe releaseSubscribe = new Subscribe();
			releaseSubscribe.setIaiAver(this.getVersion());
			releaseSubscribe.setIaiAkey(this.getAccessKey());
			releaseSubscribe.setIaiAtms(MixjukeUtils.getTimeStampString());
			releaseSubscribe.setIaiSiteID(this.getSiteID());
			releaseSubscribe.setIaiCpTransactionId(transaction);
			releaseSubscribe.setIaiToken(tokenBeginResponse.getToken());
			//CHG-S nhphuoc 140207 Fix bug 5801
			//Get uid from query string. if it is null, get it from cookie.
			String uid = request.getParameter("uid");
			if(uid==null){
				Object uidObj = MixjukeUtils.getObjectFromCookie(request, response, COOKIE_MUID_NAME);
				if(uidObj == null){
					return new DataModel(ResultCode.ERROR,
							ResouceBundleHelper.getMessage("release_subscrible_error"));	
				}else{
					uid = uidObj.toString();
				}
			}
			//releaseSubscribe.setIiaiMuid(MixjukeUtils.getObjectFromCookie(
			//		request, response, COOKIE_MUID_NAME).toString());
			releaseSubscribe.setIiaiMuid(uid);
			//CHG-E nhphuoc
			releaseSubscribe.setIaiRid(this.getResourceID());

			String stringReleaseSubcribe = om
					.writeValueAsString(releaseSubscribe);
			reqReleaseSubscribe = MixjukeUtils
					.getBase64URLEncoderString(stringReleaseSubcribe);
			sigReleaseSubscribe = MixjukeUtils.getURLEncoderString(MixjukeUtils
					.getSignature(stringReleaseSubcribe, this.getSecretKey()));

			webResource = client.resource(this.getBaseUrl())
					.path(this.getReleaseSubscribeUrl())
					.queryParam("iai_sig", sigReleaseSubscribe)
					.queryParam("iai_req", reqReleaseSubscribe);

			clientResponse = webResource.type(MediaType.TEXT_PLAIN).get(
					String.class);
			System.out
					.println("Output from Mopita Server when try to release subscribe :\n*********\n"
							+ clientResponse.toString() + "\n*********\n");
			ResultResponse releaseResponse = om.readValue(clientResponse,
					ResultResponse.class);

			// Check Error code in Response from Mopita
			if (releaseResponse == null
					|| !MOPITA_CODE_NORMAL.equals(releaseResponse.getResult()
							.getCode())) {
				System.out.println("Receive error from Mopita: "
						+ releaseResponse.getResult().getCode());
				return new DataModel(ResultCode.ERROR,
						ResouceBundleHelper.getMessage("release_subscrible_error"));
			}

		} catch (Exception e) {
			System.out.println("Unexpected exception " + e);
			return new DataModel(ResultCode.ERROR, ResouceBundleHelper.getMessage("release_subscrible_error"));
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
				+ RELEASE_SUCCESS_PATH);
	}
}
