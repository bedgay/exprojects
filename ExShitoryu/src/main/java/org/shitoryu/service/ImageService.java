package org.shitoryu.service;

import java.util.List;

import org.shitoryu.data.dto.AlbumDTO;

/**
 * @author SUCCESS\tungo
 * @date Oct 21, 2014 9:51:40 AM
 */
public interface ImageService extends Service {

	/**
	 * @param subCategoryId
	 * @return
	 */
	List<AlbumDTO> findAllAlbums(Integer subCategoryId);
	
	/**
	 * @param id
	 * @param name
	 * @param subCategoryId
	 * @return
	 */
	AlbumDTO saveAlbum(Integer id, String name, Integer subCategoryId);
	
}
