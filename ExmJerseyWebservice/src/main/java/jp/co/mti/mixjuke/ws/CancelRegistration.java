package jp.co.mti.mixjuke.ws;

import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * User: naminh Date: 10/18/13 Time: 4:18 PM
 */

public interface CancelRegistration {
    /**
     * Cancel register member
     * @param bodyData The POST body of the request.
     * @return String.
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    String cancelRegistration(String bodyData);

}
