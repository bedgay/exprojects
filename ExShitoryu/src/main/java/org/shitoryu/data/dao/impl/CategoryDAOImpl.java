package org.shitoryu.data.dao.impl;

import java.util.List;

import org.shitoryu.data.dao.CategoryDAO;
import org.shitoryu.data.entity.Category;
import org.springframework.stereotype.Repository;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 1:04:58 PM
 */
@SuppressWarnings("unchecked")
@Repository
public class CategoryDAOImpl extends DAOImpl<Category, Integer> implements CategoryDAO {

	@Override
	public List<Category> findAllByType(String type) {
		return getSession()
				.createQuery("FROM Category c JOIN FETCH c.subCategories WHERE c.type = :type")
				.setParameter("type", type).list();
	}

}
