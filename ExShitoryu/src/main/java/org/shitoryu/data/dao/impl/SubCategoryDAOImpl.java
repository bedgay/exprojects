package org.shitoryu.data.dao.impl;

import java.util.List;

import org.shitoryu.data.dao.SubCategoryDAO;
import org.shitoryu.data.entity.SubCategory;
import org.springframework.stereotype.Repository;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 1:06:32 PM
 */
@SuppressWarnings("unchecked")
@Repository
public class SubCategoryDAOImpl extends DAOImpl<SubCategory, Integer> implements SubCategoryDAO {

	@Override
	public List<SubCategory> findByCategory(Integer categoryId) {
		return getSession()
				.createQuery("FROM SubCategory sc JOIN FETCH sc.category c WHERE c.id = :categoryId")
				.setParameter("categoryId", categoryId).list();
	}

}
