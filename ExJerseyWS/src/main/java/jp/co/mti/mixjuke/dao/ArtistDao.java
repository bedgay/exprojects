/**
 * 
 */
package jp.co.mti.mixjuke.dao;

import java.util.List;

import jp.co.mti.mixjuke.dom.Artist;
import jp.co.mti.mixjuke.dom.ModelList;

/**
 * @author Xuan Nguyen
 * 
 */
public interface ArtistDao extends MixjukeDao<Artist> {

    /**
     * Get favorite artist list marked by User.
     * 
     * @param mjuid
     *            User id.
     * @return List Artist Object.
     */
    List<Artist> getFavoritedArtistListByUid(String mjuid,
            List<String> favoriteArtistIds);

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
     * Get Artist list by name with match mode %name%.
     * 
     * @param userid
     *            user id.
     * @param artistIds
     *            artist id list.
     * @return List Artist Object.
     */
    List<Artist> getArtistListByArtistIds(String userid, List<String> artistIds,
            int offset, int count);

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
     * Get all Hot Artist by uid
     * @param userid
     * @param offset
     * @param count
     * @return
     */
    ModelList<Artist> getHotArtistListBySearch(String userid, int offset, int count);
    
}
