/**
 * 
 */
package jp.co.mti.mixjuke.service.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.mti.mixjuke.dao.SongDao;
import jp.co.mti.mixjuke.dao.StreamDownloadDao;
import jp.co.mti.mixjuke.dom.Song;
import jp.co.mti.mixjuke.dom.StreamDownload;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.PerformanceService;
import jp.co.mti.mixjuke.service.RatingService;
import jp.co.mti.mixjuke.service.SongService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Xuan Nguyen
 * 
 */
@Service
@Transactional
public class SongServiceImpl extends AbstractService<Song> implements
        SongService {
    private static final Logger LOGGER = LogManager.getLogger(SongServiceImpl.class
            .getName());
    @Autowired
    private SongDao songDao;

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private StreamDownloadDao streamDownloadDao;

    protected SongServiceImpl() {
        super(Song.class);
    }

    @Override
    public List<Song> getSongListBySongIds(String mjuid, List<String> ids) {
        return songDao.getSongListBySongIds(mjuid, ids);
    }

    @Override
    public Song findSongByProductId(String productId) {
        return songDao.findSongByProductId(productId);
    }

    @Override
    public Long getTotalSongListBySflag(String mjuid, int sflag) {
        return ratingService.getTotalSongListBySflag(mjuid, sflag);
    }

    @Override
    public List<Song> getSongListByFlag(String mjuid, int sflag, int offset,
            int count) {
        List<String> songIds = ratingService.getSongIdsByFlag(mjuid, sflag,
                offset, count);
        return songDao.getSongListBySflag(mjuid, songIds);
    }

    @Override
    public Long getTotalSongListByAlbumId(String mjuid, String albumId) {
        return performanceService.getTotalPerformanceListByAlbumId(albumId);
    }

    @Override
    public List<Song> getSongListByAlbumId(String mjuid, String albumId,
            int offset, int count) {
        List<String> songIds = performanceService.getSongIdsByAlbumId(albumId,
                offset, count);
        return songDao.getSongListBySongIdsWithouRandomOrder(mjuid, songIds);
    }

    @Override
    public List<String> getListIdByGenreId(String gid) {
        return songDao.getListIdByGenreId(gid);
    }

    @Override
    public List<Song> getListByIdsWFetchArtistAuth(List<String> songIds) {
        return songDao.getListByIdsWFetchArtistAuth(songIds);
    }

    @Override
    public List<String> getListIdByGroupIds(List<String> groupIds) {
        return songDao.getListIdByGroupIds(groupIds);
    }
    
    /* (non-Javadoc)
     * @see jp.co.mti.mixjuke.service.SongService#addReadID(java.util.List)
     */
    @Override
    public List<Song> addReadID(List<Song> songs) {
    	List<StreamDownload> downloads = streamDownloadDao.findBySongs(songs);
    	
    	if (!CollectionUtils.isEmpty(songs) && !CollectionUtils.isEmpty(downloads)) {
			boolean isExist = Boolean.FALSE;
			List<Song> removeList = new ArrayList<Song>();
    		for (int i = 0; i < songs.size(); i++) {
    			Song song = songs.get(i);
    			isExist = Boolean.FALSE;
    			for (StreamDownload download : downloads) {
    				if ((song.getId().equals(download.getDlRegionId()) && song.getProductId().equals(download.getDlProdId()))
    					|| (song.getId().equals(download.getStrRegionId()) && song.getProductId().equals(download.getId()))) {
    					song.setRealId(download.getDlProdId());
    					isExist = Boolean.TRUE;
    					break;
    				}
    			}
    			if (!isExist) {
    				removeList.add(song);
    			}
    		}
    		
    		// Remove wrong data song
    		if (!CollectionUtils.isEmpty(removeList)) {
    		    LOGGER.info("Invalid songs :" + removeList.size());
    			songs.removeAll(removeList);
    		}
    	} else {
    		songs = new ArrayList<Song>();
    	}
    	
    	return songs;
    }

}
