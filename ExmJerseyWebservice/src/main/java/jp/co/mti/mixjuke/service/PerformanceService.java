/**
 * 
 */
package jp.co.mti.mixjuke.service;

import java.util.List;

import jp.co.mti.mixjuke.dom.Performance;

/**
 * @author ntnxuan
 * 
 */
/**
 * @author ntnxuan
 * 
 */
public interface PerformanceService extends MixjukeService<Performance> {
    /**
     * Get total performance belong to specific album.
     * 
     * @param albumid
     * @return
     */
    Long getTotalPerformanceListByAlbumId(String albumid);

    /**
     * Get Performance list by song id and fetch Artist, Song object.
     * 
     * @param sid
     *            Song id.
     * @return Performance list.
     */
    List<Performance> getListBySongId(String sid);

    /**
     * Get Song id list belong to specific album.
     * 
     * @param albumId
     * @param offset
     * @param count
     * @return
     */
    List<String> getSongIdsByAlbumId(String albumId, int offset, int count);

    /**
     * Get total artist by specific name, get from Performance to insure that
     * artist is valid (exist song or album).
     * 
     * @param artistName
     *            artist name which want to search
     * @return
     */
    Long getTotalPerformanceByArtistName(String[] artistName);

    /**
     * Get artist id list by specific name, get from Performance to insure that
     * artist is valid (exist song or album).
     * <p>
     * Can get from Artist join with Performance and set join type is
     * INNER_JOIN, BUT we will cannot limit correctly.
     * 
     * @param artistName
     * @param offset
     * @param count
     * @return
     */
    List<String> getArtistIdByAritstName(String[] artistName, int offset,
            int count);
}
