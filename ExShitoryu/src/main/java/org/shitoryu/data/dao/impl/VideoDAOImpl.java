package org.shitoryu.data.dao.impl;

import java.util.List;

import org.shitoryu.data.dao.VideoDAO;
import org.shitoryu.data.entity.Video;
import org.springframework.stereotype.Repository;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 1:11:02 PM
 */
@SuppressWarnings("unchecked")
@Repository
public class VideoDAOImpl extends DAOImpl<Video, Integer> implements VideoDAO {

	@Override
	public List<Video> findBySubCategory(Integer subCategoryId) {
		return getSession()
				.createQuery("FROM Video v WHERE v.subCaterory.id = :subCategoryId")
				.setParameter("subCategoryId", subCategoryId).list();
	}

}
