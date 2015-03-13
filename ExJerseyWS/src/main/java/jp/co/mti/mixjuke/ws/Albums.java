/**
 * 
 */
package jp.co.mti.mixjuke.ws;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.ws.response.SongListResponse;

/**
 * @author ntnxuan
 * 
 */
public interface Albums {

    @GET
    @Path("/{album_id}/songs")
    @Produces(MediaType.APPLICATION_JSON)
    SongListResponse getAlbumSongs(@QueryParam("uid") String uid,
            @PathParam("album_id") String albumId,
            @QueryParam("count") @DefaultValue("-1") int count,
            @QueryParam("offset") @DefaultValue("-1") int offset);
}
