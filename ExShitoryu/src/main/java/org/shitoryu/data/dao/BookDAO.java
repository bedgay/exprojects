package org.shitoryu.data.dao;

import java.util.List;

import org.shitoryu.data.entity.Book;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 1:11:56 PM
 */
public interface BookDAO extends DAO<Book, Integer> {

	/**
	 * @param subCategoryId
	 * @return
	 */
	List<Book> findAllBySubCategory(Integer subCategoryId);
	
}
