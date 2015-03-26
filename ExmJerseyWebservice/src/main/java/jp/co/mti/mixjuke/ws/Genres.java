/**
 * 
 */
package jp.co.mti.mixjuke.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.ws.response.GenreListResponse;

/**
 * @author Xuan Nguyen
 * 
 */
public interface Genres {
    /**
     * Get genre info list.
     * 
     * @param uid
     *            User ID
     * @return String in JSON format.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    GenreListResponse getGenreList(@QueryParam("uid") String uid);

}
