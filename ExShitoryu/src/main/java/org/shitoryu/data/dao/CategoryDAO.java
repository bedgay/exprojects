package org.shitoryu.data.dao;

import java.util.List;

import org.shitoryu.data.entity.Category;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 1:03:46 PM
 */
public interface CategoryDAO extends DAO<Category, Integer>{

	/**
	 * @param type
	 * @return
	 */
	List<Category> findAllByType(String type);
	
}
