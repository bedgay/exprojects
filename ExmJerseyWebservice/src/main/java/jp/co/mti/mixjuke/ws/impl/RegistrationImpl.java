package jp.co.mti.mixjuke.ws.impl;

import java.security.PublicKey;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.dom.Purchase;
import jp.co.mti.mixjuke.dom.PurchaseOrder;
import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.secret.Security;
import jp.co.mti.mixjuke.service.PurchaseOrderService;
import jp.co.mti.mixjuke.service.PurchaseService;
import jp.co.mti.mixjuke.service.UserService;
import jp.co.mti.mixjuke.util.MixjukeConstants;
import jp.co.mti.mixjuke.util.MixjukeUtils;
import jp.co.mti.mixjuke.util.PropertyUtil;
import jp.co.mti.mixjuke.ws.AbstractWebService;
import jp.co.mti.mixjuke.ws.Registration;
import jp.co.mti.mixjuke.ws.request.MemberStatus;
import jp.co.mti.mixjuke.ws.request.MopitaIDRequest;
import jp.co.mti.mixjuke.ws.request.RegistrationInput;
import jp.co.mti.mixjuke.ws.response.MobitaIdResponse;
import jp.co.mti.mixjuke.ws.response.MopitaResult;
import jp.co.mti.mixjuke.ws.util.URLUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * User: naminh Date: 10/18/13 Time: 4:18 PM
 */

@Component("registrationWebService")
@Path("/member/cb/regist")
public class RegistrationImpl extends AbstractWebService implements
		Registration {

	private static final Logger LOGGER = LogManager
			.getLogger(RegistrationImpl.class.getName());

	@Autowired
	private UserService userService;

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private PurchaseOrderService purchaseOrderService;

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String registerMember(String bodyData) {
		LOGGER.info("Trigger registerMember");
		ObjectMapper om = new ObjectMapper();

		RegistrationInput input = null;
		try {
			Map<String, String> params = URLUtils.parseQuery(bodyData,
					Boolean.FALSE);
			input = RegistrationInput.fromParams(params);
			if (!StringUtils.isEmpty(input.getIai_inapp_sig_json())) {
				input.setIai_inapp_sig_d(om.readValue(
						input.getIai_inapp_sig_json(), Purchase.class));
			}
		} catch (Exception ex) {
			LOGGER.warn("Post data is invalid json", ex);
		}

		if (input == null) { // Case 08
			LOGGER.error("Can not parse query string.");
			return new String(MopitaResult.NG.getCode());
		}

		if (this.checkUserInvalid(input.getUid())) { // Case 07
			LOGGER.error("NotLoginException for User:" + input.getUid());
			return new String(MopitaResult.NG.getCode());
		}

		User user = userService.findByUid(input.getUid());
		String publicKeyData = PropertyUtil
				.getProperty(MixjukeConstants.PUBLIC_KEY);
		PublicKey publicKey = Security.generatePublicKey(publicKeyData);

		if (input.getAct().equals(MixjukeConstants.MEMBER_ACT_REG)
				&& input.getCi()
						.equals(PropertyUtil
								.getProperty(MixjukeConstants.RESOURCE_FREE_ID))
				&& !StringUtils.isEmpty(input.getUid())
				&& !checkUserInvalid(input.getUid())) {
			if (user == null) { // Case 01 Free membership subscription
				// Craete new User & account profile with uid
				user = new User();
				user.setUid(input.getUid());
				String mopitaID = getMuidFromWS(input.getUid());
				user.setMopitaid(mopitaID);
				user.setName(mopitaID);
				//CHG-S nhphuoc 140204 Fix bug 5712
				//user.setStatus((short)MemberStatus.FREE_TRIAL.getStatus());
				user.setStatus((short)MemberStatus.FREE.getStatus());
				user.setFreeSubscribeDatetime(new java.sql.Date(new java.util.Date().getTime()));
				//CHG-E nhphuoc
				userService.save(user);
				return new String(MopitaResult.OK.getCode());
			} else if (isNotAMember(user)) {
				user.setStatus((short)MemberStatus.FREE.getStatus());
				userService.saveOrUpdate(user);
				return new String(MopitaResult.OK.getCode());				
			} else { // Case 06
				LOGGER.error("User:" + input.getUid() + " is exist.");
				return new String(MopitaResult.NG.getCode());
			}
		} else if (input.getAct().equals(MixjukeConstants.MEMBER_ACT_CHK)
				&& input.getCi()
						.equals(PropertyUtil
								.getProperty(MixjukeConstants.RESOURCE_PREMIUM_ID))
				&& !checkUserInvalid(input.getUid())
				&& user != null
				&& user.getStatus() != (short) MemberStatus.PREMIUM.getStatus()
				&& input.getIai_paytype() == MixjukeConstants.MEMBER_PREMIUM_PLAY_TYPE) { // Case 02 Check premium member
			return new String(MopitaResult.OK.getCode());
		} else if (input.getAct().equals(MixjukeConstants.MEMBER_ACT_REG)
				&& input.getCi()
						.equals(PropertyUtil
								.getProperty(MixjukeConstants.RESOURCE_PREMIUM_ID))
				&& !checkUserInvalid(input.getUid())
				&& Security.verify(publicKey, input.getIai_inapp_sig_json(),
						input.getIai_inapp_sig())
				&& input.getIai_paytype() == MixjukeConstants.MEMBER_PREMIUM_PLAY_TYPE) {
			if (user == null) { // Case 04 New Premium membership register
				user = new User();
				user.setUid(input.getUid());
				String mopitaID = getMuidFromWS(input.getUid());
                user.setMopitaid(mopitaID);
                user.setName(mopitaID);
				user.setStatus((short)MemberStatus.FREE.getStatus());
				user.setFreeSubscribeDatetime(new java.sql.Date(new java.util.Date().getTime()));
			} else if (user.getStatus() == (short) MemberStatus.PREMIUM
					.getStatus()) {
				LOGGER.info("The User:" + user.getUid()
						+ " is the premium User");
				return new String(MopitaResult.NG.getCode());
			}

			Purchase purchase = input.getIai_inapp_sig_d();
			try { // Case 03 Premium membership register
				user.setStatus((short) MemberStatus.PREMIUM.getStatus());
				userService.saveOrUpdate(user);

				Purchase oldPurchase = purchaseService.findById(purchase
						.getId());
				if (oldPurchase != null) {
					for (int i = 0; i < purchase.getOrders().size(); i++) {
						PurchaseOrder order = purchase.getOrders().get(i);
						order.setPurchase(oldPurchase);
						purchaseOrderService.saveOrUpdate(order);
					}
				} else {
					purchase.setUser(user);
					if (purchase.getOrders() != null) {
						for (int i = 0; i < purchase.getOrders().size(); i++) {
							purchase.getOrders().get(i).setPurchase(purchase);
						}
					}
					purchaseService.saveOrUpdate(purchase);
				}

				LOGGER.info("Save Purchase-History for the User:"
						+ user.getUid());
				return new String(MopitaResult.OK.getCode());
			} catch (Exception e) {
				LOGGER.error("Can not save data.", e);
				return new String(MopitaResult.NG.getCode());
			}
		}

		LOGGER.warn("Post data not in any case");
		return new String(MopitaResult.NG.getCode());
	}

	private String getMuidFromWS(String uid) {
		String muid = "";

		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

		WebResource webResource;
		try {
			MopitaIDRequest mopitaIDRequest = new MopitaIDRequest();
			mopitaIDRequest.setIaiAver(PropertyUtil.getProperty(MixjukeConstants.IAI_AVER));
			mopitaIDRequest.setMuid(uid);
			mopitaIDRequest.setIaiAkey(PropertyUtil.getProperty(MixjukeConstants.ACCESS_KEY));
			mopitaIDRequest.setIaiAtms(MixjukeUtils.getTimeStampString());
			
			String data = om.writeValueAsString(mopitaIDRequest);
			LOGGER.info("Json when try to get mopitaID: " + data);
			String req = MixjukeUtils.getBase64URLEncoderString(data);
			String sig = MixjukeUtils.getURLEncoderString(MixjukeUtils
					.getSignature(data, PropertyUtil
							.getProperty(MixjukeConstants.SECRET_KEY)));

			webResource = Client
					.create()
					.resource(
							PropertyUtil
									.getProperty(MixjukeConstants.MOBITA_ID_API))
					.queryParam("iai_sig", sig).queryParam("iai_req", req);
			
			String clientResponse = webResource.type(MediaType.TEXT_PLAIN).get(String.class);

	         System.out
	            .println("Output from Mopita Server when try to get mopitaID :\n*********\n"
	                    + clientResponse.toString() + "\n*********\n");
	        
			MobitaIdResponse response = om.readValue(clientResponse, MobitaIdResponse.class);
			
			if (response.getResult().getCode().equals(MopitaResult.I000.getCode())) {
				muid = response.getAcctid();
			} else {
				LOGGER.info("Have error<" + response.getResult().getCode() + "> to get MopitaID with UID:" + uid);
			}
			
		} catch (Exception e) {
			LOGGER.info("Error: "+e +", msg:" + e.getMessage());
		}

		return muid;
	}

	@GET
	@Path("/mopitaid")
	public String getMUId(@QueryParam("uid") String uid) {
		String result = "Not result";
		ObjectMapper om = new ObjectMapper();

		WebResource webResource;
		try {
			MopitaIDRequest mopitaIDRequest = new MopitaIDRequest();
			mopitaIDRequest.setIaiAver(PropertyUtil.getProperty(MixjukeConstants.IAI_AVER));
			mopitaIDRequest.setMuid(uid);
			mopitaIDRequest.setIaiAkey(PropertyUtil.getProperty(MixjukeConstants.ACCESS_KEY));
			mopitaIDRequest.setIaiAtms(MixjukeUtils.getTimeStampString());
			
			String data = om.writeValueAsString(mopitaIDRequest);
			String req = MixjukeUtils.getBase64URLEncoderString(data);
			String sig = MixjukeUtils.getURLEncoderString(MixjukeUtils
					.getSignature(data, PropertyUtil
							.getProperty(MixjukeConstants.SECRET_KEY)));

			webResource = Client
					.create()
					.resource(
							PropertyUtil
									.getProperty(MixjukeConstants.MOBITA_ID_API))
					.queryParam("iai_sig", sig).queryParam("iai_req", req);
			result = webResource.type(MediaType.TEXT_PLAIN).get(String.class);
		} catch (Exception e) {
			LOGGER.info("Can not get Mopita ID with UID:" + uid);
		}
		return result;
	}

}