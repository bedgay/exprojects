package javaz.spring.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javaz.common.logger.CustomLogger;
import javaz.hibernate.dao.CountryDAO;
import javaz.hibernate.dao.GroupDAO;
import javaz.hibernate.dao.LearningDAO;
import javaz.hibernate.dao.PermissionDAO;
import javaz.hibernate.dao.ProfileDAO;
import javaz.hibernate.dao.UserDAO;
import javaz.hibernate.entity.Country;
import javaz.hibernate.entity.Course;
import javaz.hibernate.entity.Group;
import javaz.hibernate.entity.Learning;
import javaz.hibernate.entity.Permission;
import javaz.hibernate.entity.Profile;
import javaz.hibernate.entity.User;
import javaz.spring.security.encoder.MD5;
import javaz.spring.service.DatabaseService;

import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseServiceImpl implements DatabaseService {

	private CustomLogger log = CustomLogger.getLogger(this.getClass());

	@Autowired
	private PermissionDAO permissionDAO;

	@Autowired
	private GroupDAO groupDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CountryDAO countryDAO;

	@Autowired
	private ProfileDAO profileDAO;

	@Autowired
	private LearningDAO learningDAO;

	@Autowired
	private SessionFactory sessionFactory;

	@PostConstruct
	public void init() {
		log.info("init(..)");

		SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;

		if (sessionFactoryImpl.getSettings().isAutoCreateSchema()) {
			log.info("Import data...");
			Permission roleUser = new Permission("ROLE_USER", "System configuration");
			Permission roleAdmin = new Permission("ROLE_ADMIN", "System administrator");
			Group group = new Group("Administrator");
			group.getPermissions().add(roleUser);
			group.getPermissions().add(roleAdmin);
			group = groupDAO.save(group);

			Group groupUser = new Group("User");
			groupUser.getPermissions().add(roleUser);
			groupUser = groupDAO.save(groupUser);

			User admin = new User("admin", MD5.encode("Spring" + "admin"), Boolean.TRUE);
			admin.setGroup(group);
			userDAO.save(admin);

			User user = new User("user", MD5.encode("Spring" + "user"), Boolean.TRUE);
			user.setGroup(group);
			userDAO.save(user);

			Country country = new Country("vn", "Vietnamese", "084", "VND");
			countryDAO.save(country);

			Profile adminProfile = new Profile("Anh Tu", "Ngo", "0938 340 339",
					"tungo@bonphuong.info");
			adminProfile.setUser(admin);
			adminProfile.setCountry(country);
			profileDAO.save(adminProfile);

			Profile userProfile = new Profile("Thai", "Henry", "0938 340 339",
					"henrythai@bonphuong.info");
			userProfile.setUser(user);
			userProfile.setCountry(country);
			profileDAO.save(userProfile);

			Course course = new Course("Java");
			Learning learning = new Learning(80f, course, adminProfile);
			learningDAO.save(learning);
		} else {
			log.info("Do nothing!");
		}
	}

	@PreDestroy
	public void destroy() {
		log.info("DESTROY");
	}

}
