package javaz.hibernate.dao.impl;

import java.util.List;

import javaz.hibernate.dao.NewsDAO;
import javaz.hibernate.entity.News;

import org.springframework.stereotype.Repository;

/**
 * @author SUCCESS\tungo
 * @date Oct 16, 2014 9:36:26 AM
 */
@Repository
public class NewsDAOImpl extends DAOImpl<News, Integer> implements NewsDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<News> findByIDs(List<Integer> ids) {
		return getSession()
				.createQuery("FROM News n WHERE n.id IN(:ids)")
				.setParameterList("ids", ids).list();
	}

}
