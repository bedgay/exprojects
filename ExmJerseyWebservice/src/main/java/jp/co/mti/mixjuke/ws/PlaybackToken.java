package jp.co.mti.mixjuke.ws;

import jp.co.mti.mixjuke.ws.response.PlaybackTokenRespone;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
/**
 * User: nhphuoc
 * Date: 10/21/13
 * Time: 5:57 PM
 */
public interface PlaybackToken {
    /**
     * Take token or check if a device have playback token
     * @param uid uid passed by mopita proxy
     * @param devid The uniquely identifies the device (assuming the IMEI at the moment)
     * @param take  0:Check only, 1:Take the playback token
     * @return {@link PlaybackTokenRespone}
     */
 @GET
 @Produces(MediaType.APPLICATION_JSON)
 PlaybackTokenRespone controlPlaybackToken(@QueryParam("uid") String uid,
                                           @QueryParam("dev_unique_id") String devid,
                                           @QueryParam("take") int take );
}
