package org.shitoryu.data.dao;

import java.util.List;

import org.shitoryu.data.entity.Video;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 1:10:37 PM
 */
public interface VideoDAO extends DAO<Video, Integer> {

	/**
	 * @param subCategoryId
	 * @return
	 */
	List<Video> findBySubCategory(Integer subCategoryId);
	
}
