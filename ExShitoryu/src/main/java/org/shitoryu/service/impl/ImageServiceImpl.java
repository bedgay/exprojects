package org.shitoryu.service.impl;

import java.util.List;

import org.shitoryu.data.dao.AlbumDAO;
import org.shitoryu.data.dto.AlbumDTO;
import org.shitoryu.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SUCCESS\tungo
 * @date Nov 7, 2014 1:29:23 PM
 */
@SuppressWarnings("unchecked")
@Service
public class ImageServiceImpl extends ServiceImpl implements ImageService {

	@Autowired
	private AlbumDAO albumDAO;
	
	@Override
	public List<AlbumDTO> findAllAlbums(Integer subCategoryId) {
		return (List<AlbumDTO>)toDTOs(albumDAO.findBySubCaterory(subCategoryId));
	}

	@Override
	public AlbumDTO saveAlbum(Integer id, String name, Integer subCategoryId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
