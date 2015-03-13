package jp.co.mti.mixjuke.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import jp.co.mti.mixjuke.ws.response.SuccessResponse;

/**
 * User: nhphuoc
 * Date: 11/27/13
 * Time: 3:56 PM
 */
public interface PlaybackDoLog {
    /**
     * Save playback data into database.
     * @param postData the playback data.
     * @return result status
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public SuccessResponse playbacklog(MultivaluedMap<String,String> params);
}
