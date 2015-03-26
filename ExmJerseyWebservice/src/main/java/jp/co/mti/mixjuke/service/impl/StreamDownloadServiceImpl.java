package jp.co.mti.mixjuke.service.impl;

import java.util.List;

import jp.co.mti.mixjuke.dao.StreamDownloadDao;
import jp.co.mti.mixjuke.dom.Song;
import jp.co.mti.mixjuke.dom.StreamDownload;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.StreamDownloadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author natu
 * @date 2014-01-16
 */
@Service
@Transactional
public class StreamDownloadServiceImpl extends AbstractService<StreamDownload>
		implements StreamDownloadService {
	
    @Autowired
    private StreamDownloadDao streamDownloadDao;

    protected StreamDownloadServiceImpl() {
        super(StreamDownload.class);
    }

	/**
	 * Find all download by Song list
	 * @param songs
	 * @return
	 */
    @Override
	public List<StreamDownload> findBySongs(List<Song> songs) {
    	return streamDownloadDao.findBySongs(songs);
    }

}
