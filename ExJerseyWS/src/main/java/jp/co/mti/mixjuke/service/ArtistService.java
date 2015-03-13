/**
 * 
 */
package jp.co.mti.mixjuke.service;

import java.util.List;

import jp.co.mti.mixjuke.dom.Artist;
import jp.co.mti.mixjuke.dom.ModelList;
import jp.co.mti.mixjuke.ws.response.ArtistInfo;

/**
 * @author Xuan Nguyen
 * 
 */
public interface ArtistService extends MixjukeService<Artist> {
    /**
     * Get Artist list by artist's property.
     * 
     * @param propertyName
     *            property's Artist.
     * @param list
     *            list value of property.
     * @return List Artist Object.
     */
    List<Artist> getArtistListByProperty(String propertyName,
            List<String> list, String mjuid);

    /**
     * Get Artist list by artist's property and fetch favorite information.
     * 
     * @param propertyName
     *            property's Artist.
     * @param list
     *            list value of property.
     * @return List Artist Object.
     */
    List<Artist> getArtistListByPropertyWFetchFavorite(String propertyName,
            List<String> list);

    /**
     * Get favorite artist list marked by User.
     * 
     * @param uid
     *            User id.
     * @return List Artist Object.
     */
    List<Artist> getFavoritedArtistListByUid(String userid, int offset,
            int count);

    /**
     * Get artist include favorite info by artist id.
     * 
     * @param userid
     *            User id.
     * @param aid
     *            Artist id.
     * @return Artist object.
     */
    Artist getArtistByArtistId(String userid, String aid);

    /**
     * Get total artist list by specified artist name.
     * 
     * @param artistName
     * @return
     */
//    Long getTotalArtistListBySearchName(String artistName);

    /**
     * Get Artist list by name with match mode %name%.
     * 
     * @param userid
     *            user id.
     * @param artistName
     *            artist name.
     * @return List Artist Object.
     */
    ModelList<Artist> getArtistListBySearchName(String userid, String[] artistName,
            int offset, int count);
    
    /**
     * Get hot artist list
     * @param userid
     * @param offset
     * @param count
     * @return
     */
    ModelList<Artist> getHotArtistListBySearch(String userid, int offset,
			int count);

    /**
     * Get a list of ArtistInfo from the list of Artist
     * 
     * @param userId
     * @param reArtist
     * @return
     */
    List<ArtistInfo> getArtistInfoList(String userId, List<Artist> reArtist);
}
