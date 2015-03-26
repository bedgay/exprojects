package jp.co.mti.mixjuke.ws;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Akamai Authentication Callback												.
 * User: nhphuoc
 * Date: 10/22/13
 * Time: 1:27 PM
 */
public interface ServiceAuthentication {
    /**
     * Check if user can access specified song stream.
     * @param uid uid passed by mopita proxy
     * @param devid The uniquely identifies the device (assuming the IMEI at the moment)
     * @param server Parameter let akamai know witch server it should redirect request to, we don't check it
     * @param pid Product ID of the song
     * @param deviceName The device model name
     * @param streamingDt The device time of request
     * @param playSessionId Session ID generated in app(per track playback)
     * @param playType The playback type. trial=0ã€�full=1
     * @param seedArtistId The Artist ID
     * @param seedGenreId The Genre ID
     * @return  If user can access the song, return http status code 200, if no return http status code 304
     */
 @GET
 @Produces(MediaType.TEXT_PLAIN)
 public Response serviceAuthenticate(@QueryParam("uid") String uid,
                                 @QueryParam("dev_unique_id") String devid,
                                 @QueryParam("sv") String server,
                                 @QueryParam("product_id") String pid,
                                 @QueryParam("device_nm") String deviceName,
                                 @QueryParam("streaming_dt") String streamingDt,
                                 @QueryParam("play_session_id") String playSessionId,
                                 @QueryParam("play_type") String playType,
                                 @QueryParam("seed_artist_id") @DefaultValue("-1") Integer seedArtistId,
                                 @QueryParam("seed_genre_id") @DefaultValue("-1") Integer seedGenreId);
}
