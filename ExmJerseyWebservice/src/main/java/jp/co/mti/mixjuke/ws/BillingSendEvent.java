package jp.co.mti.mixjuke.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import jp.co.mti.mixjuke.ws.response.BillingResponse;

/**
 * @author natu
 * @date 2014-01-09
 */
public interface BillingSendEvent {

    /**
     * @param bodyData
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    BillingResponse sendData(MultivaluedMap<String,String> bodyData);

}
