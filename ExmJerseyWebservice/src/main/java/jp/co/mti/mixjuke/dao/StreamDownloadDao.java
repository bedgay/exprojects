package jp.co.mti.mixjuke.dao;

import java.util.List;

import jp.co.mti.mixjuke.dom.Song;
import jp.co.mti.mixjuke.dom.StreamDownload;

/**
 * @author natu
 * @date 2014-01-16
 */
public interface StreamDownloadDao extends MixjukeDao<StreamDownload> {
	
	/**
	 * Find all download by Song list
	 * @param songs
	 * @return
	 */
	List<StreamDownload> findBySongs(List<Song> songs);
	
}
