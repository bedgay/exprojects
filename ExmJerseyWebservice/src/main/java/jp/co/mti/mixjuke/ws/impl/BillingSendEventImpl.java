package jp.co.mti.mixjuke.ws.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import jp.co.mti.mixjuke.dom.Purchase;
import jp.co.mti.mixjuke.util.MixjukeConstants;
import jp.co.mti.mixjuke.util.MixjukeUtils;
import jp.co.mti.mixjuke.util.PropertyUtil;
import jp.co.mti.mixjuke.ws.AbstractWebService;
import jp.co.mti.mixjuke.ws.BillingSendEvent;
import jp.co.mti.mixjuke.ws.request.BillingSendEventInput;
import jp.co.mti.mixjuke.ws.request.BillingSendEventRequest;
import jp.co.mti.mixjuke.ws.response.BillingEventRFirst;
import jp.co.mti.mixjuke.ws.response.BillingResponse;
import jp.co.mti.mixjuke.ws.response.MopitaResult;
import jp.co.mti.mixjuke.ws.response.ResultCode;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * @author natu
 * @date 2014-01-10
 */
@Component("billingSendEvent")
@Path("/iabilling/sendevent")
public class BillingSendEventImpl extends AbstractWebService implements BillingSendEvent {
	private static final Logger LOGGER = LogManager
			.getLogger(BillingSendEventImpl.class.getName());

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Override
    public BillingResponse sendData(MultivaluedMap<String,String> params) {
		LOGGER.info("Trigger BillingSendEvent");
		ObjectMapper om = new ObjectMapper();
    	BillingResponse billingResponse = null;
		
		BillingSendEventInput input = null;
		try {
			input = BillingSendEventInput.fromParams(params);
			if (!StringUtils.isEmpty(input.getDataJson())) {
				input.setData(om.readValue(input.getDataJson(), Purchase.class));
			}
		} catch (Exception ex) {
			LOGGER.error("Can not parse posting data.", ex);
		}

		if ((input == null) ||!input.isValidData()) { // Case 01
        	return new BillingResponse(ResultCode.SYSTEM_ERROR);
		}
		
    	try {
			// Call mobita api
    		Client client = Client.create();

			BillingSendEventRequest request = new BillingSendEventRequest();
			request.setIaiAver(PropertyUtil.getProperty(MixjukeConstants.IAI_AVER));
			request.setData(input.getDataJson());
			request.setIaiAkey(PropertyUtil.getProperty(MixjukeConstants.ACCESS_KEY_STANDALONE));
			request.setIaiAtms(MixjukeUtils.getTimeStampString());
			request.setSig(input.getSig());
			
			String data = om.writeValueAsString(request);

			String req = MixjukeUtils.getBase64URLEncoderString(data);
			String sig = MixjukeUtils.getURLEncoderString(MixjukeUtils
					.getSignature(data, PropertyUtil.getProperty(MixjukeConstants.SECRET_KEY_STANDALONE)));

            WebResource webResource = client.resource(PropertyUtil.getProperty(MixjukeConstants.GOOGLE_IN_APP_API))
                    .queryParam("iai_sig", sig)
                    .queryParam("iai_req", req);

            String clientResponse = webResource.type(MediaType.TEXT_PLAIN).get(String.class);
            
            BillingEventRFirst mobitaResponse = om.readValue(clientResponse, BillingEventRFirst.class);
        	
            if (mobitaResponse.getResult().getCode().equals(MopitaResult.I000.getCode())) {
            	billingResponse = new BillingResponse(ResultCode.NORMAL);
            	billingResponse.setMtiresp(mobitaResponse);
            } else {
            	billingResponse = new BillingResponse(ResultCode.SYSTEM_ERROR);
    			LOGGER.error("Have the error from Mopita with code: " + mobitaResponse.getResult().getCode());
            }
		} catch (Exception e) {
			billingResponse = new BillingResponse(ResultCode.SYSTEM_ERROR);
			LOGGER.error("Connect to Mopita error", e);
		}
		
		return billingResponse;
	}

}
