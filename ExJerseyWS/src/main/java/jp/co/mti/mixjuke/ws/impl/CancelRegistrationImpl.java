package jp.co.mti.mixjuke.ws.impl;

import java.security.PublicKey;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.dom.Purchase;
import jp.co.mti.mixjuke.dom.PurchaseOrder;
import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.secret.Security;
import jp.co.mti.mixjuke.service.PurchaseOrderService;
import jp.co.mti.mixjuke.service.PurchaseService;
import jp.co.mti.mixjuke.service.UserService;
import jp.co.mti.mixjuke.util.MixjukeConstants;
import jp.co.mti.mixjuke.util.PropertyUtil;
import jp.co.mti.mixjuke.ws.AbstractWebService;
import jp.co.mti.mixjuke.ws.CancelRegistration;
import jp.co.mti.mixjuke.ws.request.CancelationInput;
import jp.co.mti.mixjuke.ws.request.MemberStatus;
import jp.co.mti.mixjuke.ws.response.MopitaResult;
import jp.co.mti.mixjuke.ws.util.URLUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @User: naminh 
 * @Date: 10/18/13 Time: 4:18 PM
 */

@Component("cancelRegistrationWebService")
@Path("/member/cb/cancel")
public class CancelRegistrationImpl extends AbstractWebService implements
        CancelRegistration {
    private static final Logger LOGGER = LogManager
            .getLogger(CancelRegistrationImpl.class.getName());

    @Autowired
    private UserService userService;

	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;

    @Override
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String cancelRegistration(String bodyData) {
        LOGGER.info("Trigger cancelRegistration");
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		String publicKeyData = PropertyUtil.getProperty(MixjukeConstants.PUBLIC_KEY);
		PublicKey publicKey = Security.generatePublicKey(publicKeyData);

		CancelationInput input = null;
		try {
			Map<String, String> params = URLUtils.parseQuery(bodyData, Boolean.FALSE);
			input = CancelationInput.fromParams(params);
			if (!StringUtils.isEmpty(input.getIai_inapp_sig_json())) {
				input.setIai_inapp_sig_d(om.readValue(input.getIai_inapp_sig_json(), Purchase.class));
			}
		} catch (Exception ex) {
			LOGGER.warn("Post data is invalid json", ex);
		}

		if (input == null) { // Case 06
			LOGGER.error("Can not parse query string.");
			return new String(MopitaResult.NG.getCode());
		}
		
		if (this.checkUserInvalid(input.getUid())) { // Case 05
            LOGGER.error("NotLoginException for User:" + input.getUid());
            return new String(MopitaResult.NG.getCode());
        } else {
            User user = userService.findByUid(input.getUid());
            if (isNotAMember(user)) { // Case 04
                LOGGER.error("Cannot find the User:" + input.getUid());
                return new String(MopitaResult.NG.getCode());
            } else {
            	if (input.getCi().equals(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_FREE_ID))) { // Case 01
            		if (user.getStatus() != (short)MemberStatus.PREMIUM.getStatus()) {
                		user.setStatus((short)MemberStatus.CANCELED.getStatus());
                		userService.saveOrUpdate(user);
                        LOGGER.info("Canceled a User:" + user.getUid() + " in DB!");
                        return new String(MopitaResult.OK.getCode());
            		}
            	} else if (input.getCi().equals(PropertyUtil.getProperty(MixjukeConstants.RESOURCE_PREMIUM_ID))) { // Case 02
            		if (!Security.verify(publicKey, input.getIai_inapp_sig_json(), input.getIai_inapp_sig())) { // Case 03
            			LOGGER.error("Signature data is wrong.");
            			return new String(MopitaResult.NG.getCode());
            		}
            		
            		Purchase purchase = input.getIai_inapp_sig_d();
            		if (purchase == null) {
            			LOGGER.error("Can not parse query string.");
            			return new String(MopitaResult.NG.getCode());
            		}
            		//Don't need track time here. We just track once
            		//when user registers.
            		user.setStatus((short)MemberStatus.FREE.getStatus());
            		userService.saveOrUpdate(user);
            		LOGGER.info("Update FREE membership for User:" + user.getUid());
            		
					Purchase oldPurchase = purchaseService.findById(purchase.getId());
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
					
            		LOGGER.info("Save Purchase-History for the User:" + user.getUid());
            		return new String(MopitaResult.OK.getCode());
            	}
            }
        }
		
    	return new String(MopitaResult.NG.getCode());
    }
    
}
