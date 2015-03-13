package org.shitoryu.data.dao;

import java.util.List;

import org.shitoryu.data.entity.SubCategory;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 1:05:36 PM
 */
public interface SubCategoryDAO extends DAO<SubCategory, Integer> {

	/**
	 * @param categoryId
	 * @return
	 */
	List<SubCategory> findByCategory(Integer categoryId);
	
}
