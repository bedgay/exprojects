/**
 * 
 */
package jp.co.mti.mixjuke.ws;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.ws.response.SongListResponse;
import jp.co.mti.mixjuke.ws.response.SongResponse;
import jp.co.mti.mixjuke.ws.response.SuccessResponse;

/**
 * @author Xuan Nguyen
 * 
 */
public interface Songs {

    /**
     * Get recommended songs from specified genre.
     * 
     * @param uid
     *            User id.
     * @param gid
     *            Genre id.
     * @param count
     *            Max count of expected result of songs.
     * @return String in JSON format.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    SongListResponse getGenreMIX(@QueryParam("uid") String uid,
            @QueryParam("gid") String gid,
            @QueryParam("count") @DefaultValue("-1") int count,
            @QueryParam("offset") @DefaultValue("-1") int offset,
            @QueryParam("sflag") @DefaultValue("-1") int sflag);

    /**
     * Get song info of specified pid.
     * 
     * @param uid
     *            User id.
     * @param sid
     *            Song id.
     * @return String in JSON format.
     */
    @GET
    @Path("/{sid}")
    @Produces(MediaType.APPLICATION_JSON)
    SongResponse getSongInfo(@QueryParam("uid") String uid,
            @PathParam("sid") String sid);

    /**
     * Change the status of song which marked by user
     * 
     * @param uid
     *            User id.
     * @param sid
     *            Song identifier user want to update the status..
     * @param sflag
     *            The new status of the song (0,1,2).
     * @return String in JSON format.
     */
    @PUT
    @Path("/{sid}")
    @Produces(MediaType.APPLICATION_JSON)
    SuccessResponse updateSongStatus(@QueryParam("uid") String uid,
            @PathParam("sid") String sid,
            @QueryParam("sflag") @DefaultValue("-1") int sflag);

}
