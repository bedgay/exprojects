package jp.co.mti.mixjuke.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * User: naminh
 * Date: 10/23/13
 * Time: 3:33 PM
 */
public interface Test {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String execute (@QueryParam("caseId") int caseId,
                      @QueryParam("act") int act

    );
}
