package javaz.spring.startup;

import javax.annotation.PostConstruct;

import javaz.spring.service.NewsService;
import javaz.spring.service.PermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SUCCESS\tungo
 * @date Oct 8, 2014 10:37:42 AM
 */
@Service
public class StartupService {

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private NewsService newsService;

	/**
	 * Initialize data for Permission module
	 */
	@PostConstruct
	private void start() {
		permissionService.init();
		newsService.init();
	}
	
}
