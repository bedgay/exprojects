package org.shitoryu.data.dao;

import java.util.List;

import org.shitoryu.data.entity.Album;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 1:07:44 PM
 */
public interface AlbumDAO extends DAO<Album, Integer> {

	/**
	 * @param subCategoryId
	 * @return
	 */
	List<Album> findBySubCaterory(Integer subCategoryId);
	
}
