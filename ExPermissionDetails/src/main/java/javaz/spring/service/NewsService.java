package javaz.spring.service;

import java.util.List;

import javaz.data.dto.NewsDTO;

/**
 * @author SUCCESS\tungo
 * @date Oct 16, 2014 9:44:47 AM
 */
public interface NewsService extends Service {
	
	void init();
	
	List<NewsDTO> findByIDs(List<Integer> ids);

}
