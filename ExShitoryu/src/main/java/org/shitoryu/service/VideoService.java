package org.shitoryu.service;

import java.util.List;

import org.shitoryu.data.dto.VideoDTO;

/**
 * @author SUCCESS\tungo
 * @date Nov 7, 2014 2:41:02 PM
 */
public interface VideoService extends Service {

	/**
	 * @param subCategoryId
	 * @return
	 */
	List<VideoDTO> findAllVideos(Integer subCategoryId);
	
}
