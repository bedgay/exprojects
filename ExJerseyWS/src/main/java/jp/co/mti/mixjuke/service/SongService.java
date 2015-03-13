/**
 * 
 */
package jp.co.mti.mixjuke.service;

import java.util.List;

import jp.co.mti.mixjuke.dom.Song;

/**
 * @author Xuan Nguyen
 * 
 */
public interface SongService extends MixjukeService<Song> {

    /**
     * Get song list include artist info by specified song id list.
     * 
     * @param mjuid
     *            user id - need this param because must be query rating and
     *            favorite of this user.
     * @param ids
     *            list of song id.
     * @return list of Song object.
     */
    List<Song> getSongListBySongIds(String mjuid, List<String> ids);

    /**
     * Find song by product id (mapping with streamUrl column).
     * 
     * @param productId
     *            Product Id.
     * @return Song object
     */
    Song findSongByProductId(String productId);

    /**
     * Get total song with specified album.
     * 
     * @param mjuid
     * @param albumId
     * @return
     */
    Long getTotalSongListByAlbumId(String mjuid, String albumId);

    /**
     * Get song list belong to specified album include artist, favorite info.
     * 
     * @param mjuid
     *            user id.
     * @param albumId
     *            album is.
     * @return Song list.
     */
    List<Song> getSongListByAlbumId(String mjuid, String albumId, int offset,
            int count);

    /**
     * Get total song with specified sflag.
     * 
     * @param mjuid
     * @param sflag
     * @return
     */
    Long getTotalSongListBySflag(String mjuid, int sflag);

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
    List<Song> getSongListByFlag(String mjuid, int sflag, int offset, int count);

    List<String> getListIdByGenreId(String gid);

    List<String> getListIdByGroupIds(List<String> groupIds);

    List<Song> getListByIdsWFetchArtistAuth(List<String> songIds);
    
    /**
     * Add real ID from StreamDownload
     * @param songs
     * @return
     */
    List<Song> addReadID(List<Song> songs);

}
