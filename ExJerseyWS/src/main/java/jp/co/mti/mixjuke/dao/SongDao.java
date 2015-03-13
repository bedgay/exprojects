package jp.co.mti.mixjuke.dao;

import java.util.List;

import jp.co.mti.mixjuke.dom.Song;

public interface SongDao extends MixjukeDao<Song> {

    /**
     * Get song list include artist info by specified song id list.
     * 
     * @param mjuid
     *            user id.
     * @param ids
     *            list of song id.
     * @return list of Song object.
     */
    List<Song> getSongListBySongIds(String mjuid, List<String> ids);

    List<Song> getSongListBySongIdsWithouRandomOrder(String mjuid,
            List<String> ids);

    /**
     * Find song by product id (mapping with streamUrl column).
     * 
     * @param productId
     *            Product Id.
     * @return Song object.
     */
    Song findSongByProductId(String productId);

    /**
     * Get song list include artist info that marked by User with good, bad or
     * neutral status.
     * 
     * @param mjuid
     *            User id.
     * @param sflag
     *            Good, bad or neutral status.
     * @return Song list.
     */
    List<Song> getSongListBySflag(String mjuid, List<String> songIds);

    List<String> getListIdByGenreId(String gid);

    List<String> getListIdByGroupIds(List<String> groupIds);

    List<Song> getListByIdsWFetchArtistAuth(List<String> songIds);

}
