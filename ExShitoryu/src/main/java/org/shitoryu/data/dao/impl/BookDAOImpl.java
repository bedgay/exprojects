package org.shitoryu.data.dao.impl;

import java.util.List;

import org.shitoryu.data.dao.BookDAO;
import org.shitoryu.data.entity.Book;
import org.springframework.stereotype.Repository;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 1:12:35 PM
 */
@SuppressWarnings("unchecked")
@Repository
public class BookDAOImpl extends DAOImpl<Book, Integer> implements BookDAO {

	@Override
	public List<Book> findAllBySubCategory(Integer subCategoryId) {
		return getSession()
				.createQuery("FROM Book b WHERE b.subCaterory.id = :subCategoryId")
				.setParameter("subCategoryId", subCategoryId).list();
	}

}
