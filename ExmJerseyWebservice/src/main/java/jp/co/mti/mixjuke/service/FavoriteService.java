/**
 * 
 */
package jp.co.mti.mixjuke.service;

import java.util.List;

import jp.co.mti.mixjuke.dom.Favorite;

/**
 * @author ntnxuan
 * 
 */
public interface FavoriteService extends MixjukeService<Favorite> {
    /**
     * Find Favorite by user id, artist id and fetch all them
     * 
     * @param mjuid
     *            user id.
     * @param aid
     *            artist id
     * @return Favorite object.
     */
    Favorite findByUidAnfAid(String mjuid, String aid);

    Long getTotalFavoriteListByUid(String mjuid);

    /**
     * Get favorite belong to user with random result.
     * 
     * @param mjuid
     *            user id.
     * @return Favorite list.
     */
    List<Favorite> getListByUid(String mjuid, int offset, int count);

    /**
     * Get favorite Artist Id list mark by specific user.
     * 
     * @param mjuid
     * @param offset
     * @param count
     * @return
     */
    List<String> getFavoriteArtistIds(String mjuid, int offset, int count);
}
