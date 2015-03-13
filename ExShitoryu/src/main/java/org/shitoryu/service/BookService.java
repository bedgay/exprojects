package org.shitoryu.service;

import java.util.List;

import org.shitoryu.data.dto.BookDTO;

/**
 * @author SUCCESS\tungo
 * @date Nov 7, 2014 2:56:20 PM
 */
public interface BookService extends Service {
	
	/**
	 * @param subCategoryId
	 * @return
	 */
	List<BookDTO> findAllBooks(Integer subCategoryId);

}
