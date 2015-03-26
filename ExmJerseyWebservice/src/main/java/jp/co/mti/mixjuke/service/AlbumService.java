/**
 * 
 */
package jp.co.mti.mixjuke.service;

import java.util.List;

import jp.co.mti.mixjuke.dom.Album;

/**
 * @author ntnxuan
 * 
 */
public interface AlbumService extends MixjukeService<Album> {
    /**
     * Get total performance perform by specific artist.
     * 
     * @param aid
     * @return
     */
    Long getTotalAlbumByArtistId(String aid);

    List<Album> getAlbumListByArtistId(String artistID, int offset, int count);
}
