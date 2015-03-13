package org.shitoryu.data.dao.impl;

import java.util.List;

import org.shitoryu.data.dao.AlbumDAO;
import org.shitoryu.data.entity.Album;
import org.springframework.stereotype.Repository;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 1:08:16 PM
 */
@SuppressWarnings("unchecked")
@Repository
public class AlbumDAOImpl extends DAOImpl<Album, Integer> implements AlbumDAO {

	@Override
	public List<Album> findBySubCaterory(Integer subCategoryId) {
		return getSession()
				.createQuery("FROM Album a WHERE a.subCaterory.id = :subCategoryId")
				.setParameter("subCategoryId", subCategoryId).list();
	}

}
