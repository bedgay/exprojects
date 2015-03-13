package org.shitoryu.service.impl;

import java.util.List;

import org.shitoryu.data.dao.BookDAO;
import org.shitoryu.data.dto.BookDTO;
import org.shitoryu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SUCCESS\tungo
 * @date Nov 7, 2014 2:58:14 PM
 */
@SuppressWarnings("unchecked")
@Service
public class BookServiceImpl extends ServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDAO;
	
	@Override
	public List<BookDTO> findAllBooks(Integer subCategoryId) {
		return (List<BookDTO>) toDTOs(bookDAO.findAllBySubCategory(subCategoryId));
	}

}
