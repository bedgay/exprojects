package javaz.hibernate.dao;

import java.util.List;

import javaz.hibernate.entity.News;

/**
 * @author SUCCESS\tungo
 * @date Oct 16, 2014 9:34:43 AM
 */
public interface NewsDAO extends DAO<News, Integer> {
	
	/**
	 * @param ids
	 * @return
	 */
	List<News> findByIDs(List<Integer> ids);

}
