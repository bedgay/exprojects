package jp.co.mti.mixjuke.ws;

import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * User: naminh 
 * Date: 10/18/13 
 * Time: 4:18 PM
 */

public interface Registration {
    /**
     * Register member
     * 
     * @param ci
     * @param cs
     * @param uid
     * @param act
     * @param iai_tms
     * @param iai_paytype
     * @param iai_ordid
     * @param arg
     * 
     * @return String.
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    String registerMember(String bodyData);

}
