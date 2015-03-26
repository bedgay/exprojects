/**
 * 
 */
package jp.co.mti.mixjuke.service;

import java.util.List;

import jp.co.mti.mixjuke.dom.Favorite;
import jp.co.mti.mixjuke.dom.Rating;

/**
 * @author ntnxuan
 * 
 */
public interface RatingService extends MixjukeService<Rating> {
    /**
     * Check rating of song that mark by User.
     * 
     * @param mjuid
     *            user id.
     * @param sid
     *            song id.
     * @return Rating object.
     */
    Rating findByUidAndSid(String mjuid, String sid);

    /**
     * Update Rating and Favorite also, we must modify them in one unit. In case
     * one of them error it will auto rollback all of them.
     * 
     * @param rating
     *            rating object will be update.
     * @param favoriteList
     *            favorite list will be update.
     * @return
     */
    boolean updateRatingAndFavorite(Rating rating, List<Favorite> favoriteList);

    /**
     * Get total song marked with specific flag by specific user.
     * 
     * @param mjuid
     * @param sflag
     * @return
     */
    Long getTotalSongListBySflag(String mjuid, int sflag);

    /**
     * Get song id list which marked with specific flag by specific user.
     * 
     * @param mjuid
     * @param sflag
     * @param offset
     * @param count
     * @return
     */
    List<String> getSongIdsByFlag(String mjuid, int sflag, int offset, int count);
}
