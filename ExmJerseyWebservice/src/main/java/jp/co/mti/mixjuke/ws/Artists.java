/**
 * 
 */
package jp.co.mti.mixjuke.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.ws.response.AlbumListResponse;
import jp.co.mti.mixjuke.ws.response.ArtistListResponse;
import jp.co.mti.mixjuke.ws.response.ArtistResponse;
import jp.co.mti.mixjuke.ws.response.SongListResponse;
import jp.co.mti.mixjuke.ws.response.SuccessResponse;

/**
 * @author Xuan Nguyen
 * 
 */
public interface Artists {
    /**
     * Get recommended songs for a specified artist.
     * 
     * @param uid
     *            User id.
     * @param aid
     *            Artist id.
     * @param count
     *            Max count of expected result of songs.
     * @return String in JSON format.
     */
    @GET
    @Path("/{aid}/songs")
    @Produces(MediaType.APPLICATION_JSON)
    SongListResponse getArtistMIX(@QueryParam("uid") String uid,
            @PathParam("aid") String aid,
            @QueryParam("count") @DefaultValue("-1") int count);

    /**
     * 1.Get artist list for artist list screen(a list mix of favorite artist
     * and recommendation)
     * <p>
     * 2.Search for artist matching user input
     * 
     * @param uid
     *            User id
     * @param recount
     *            Minimum recommend aritst count that should be included in the
     *            result list
     * @param count
     *            Max count of search result whould be returned
     * @param name
     *            User inputed name of artist
     * @param offset
     *            Offset of returned result
     * @return String in JSON format.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    ArtistListResponse getArtistList(@QueryParam("uid") String uid,
            @QueryParam("count") @DefaultValue("-1") int count,
            @QueryParam("name") String name,
            @QueryParam("offset") @DefaultValue("-1") int offset);

    /**
     * Upload local artist Upload local artist name list.
     * 
     * @param uid
     *            User ID
     * @param artist
     *            Artist list
     * @return String in JSON format.
     */
    @PUT
    @Path("/favorites")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    SuccessResponse uploadLocalArtist(@QueryParam("uid") String uid,
            String artistString);

    /**
     * Add one favorite artist for user.
     * 
     * @param uid
     *            User ID.
     * @param aid
     *            Artist ID.
     * @return String in JSON format.
     */
    @PUT
    @Path("/favorites/{aid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    SuccessResponse addFavoriteArtist(@QueryParam("uid") String uid,
            @PathParam("aid") String aid);

    /**
     * Remove one favorite artist for user.
     * 
     * @param uid
     *            User ID.
     * @param aid
     *            Artist ID.
     * @return String in JSON format.
     */
    @DELETE
    @Path("/favorites/{aid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    SuccessResponse deleteFavoriteArtist(@QueryParam("uid") String uid,
            @PathParam("aid") String aid);

    /**
     * Get favorite artist list marked by user.
     * 
     * @param uid
     *            User id.
     * @param offset
     *            Offset index that returned list start from.
     * @param count
     *            Max count of search result whould be returned.
     * @return String in JSON format.
     */
    @GET
    @Path("/favorites")
    @Produces(MediaType.APPLICATION_JSON)
    ArtistListResponse getFavoriteArtistList(@QueryParam("uid") String uid,
            @QueryParam("offset") @DefaultValue("-1") int offset,
            @QueryParam("count") @DefaultValue("-1") int count);

    /**
     * Get artist info of specified aid
     * 
     * @param uid
     *            User id.
     * @param aid
     *            Artist Id.
     * @return String in JSON format.
     */
    @GET
    @Path("/{aid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    ArtistResponse getArtistInfo(@QueryParam("uid") String uid,
            @PathParam("aid") String aid);

    /**
     * Get albums for specified artist.
     * 
     * @param uid
     *            User id.
     * @param aid
     *            Artist ID.
     * @param offset
     *            Offset index that returned list start from
     * @param count
     *            Max count of search result whould be returned.
     * @return String in JSON format.
     */
    @GET
    @Path("/{aid}/albums")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    AlbumListResponse getArtistAlbum(@QueryParam("uid") String uid,
            @PathParam("aid") String aid,
            @QueryParam("offset") @DefaultValue("-1") int offset,
            @QueryParam("count") @DefaultValue("-1") int count);
}
