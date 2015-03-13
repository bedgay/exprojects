package org.shitoryu.service.impl;

import java.util.List;

import org.shitoryu.data.dao.VideoDAO;
import org.shitoryu.data.dto.VideoDTO;
import org.shitoryu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SUCCESS\tungo
 * @date Nov 7, 2014 2:42:35 PM
 */
@SuppressWarnings("unchecked")
@Service
public class VideoServiceImpl extends ServiceImpl implements VideoService {

	@Autowired
	private VideoDAO videoDAO;
	
	@Override
	public List<VideoDTO> findAllVideos(Integer subCategoryId) {
		return (List<VideoDTO>) toDTOs(videoDAO.findBySubCategory(subCategoryId));
	}

}
