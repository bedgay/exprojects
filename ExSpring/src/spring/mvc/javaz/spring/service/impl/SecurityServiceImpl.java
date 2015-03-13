package javaz.spring.service.impl;

import java.io.IOException;
import java.util.List;

import javaz.common.logger.CustomLogger;
import javaz.hibernate.dao.UserDAO;
import javaz.hibernate.entity.User;
import javaz.spring.aop.annotation.CTransaction;
import javaz.spring.aop.transaction.session.CSession;
import javaz.spring.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author SUCCESS\tungo
 * @date Aug 5, 2014 11:42:15 AM
 */
@Component
public class SecurityServiceImpl implements SecurityService {

	private CustomLogger log = CustomLogger.getLogger(this.getClass());

	@Autowired
	private UserDAO userDAO;

	@Override
	public User findUserByUsername(String username) {
		log.info("findUserByUsername(String username)");
		return userDAO.findByUsername(username);
	}

	@Override
	public boolean checkLogin(final User user) {
		log.info("checkLogin(final User user)");
		User account = userDAO.findByUsername(user.getUsername());
		return account != null
				&& account.getPassword().equals(user.getPassword());
	}

	@Override
	public List<User> findAllUser() {
		log.info("findAllUser()");
		return userDAO.findAll();
	}

	@Override
	public List<User> findAllUserWithGroup() {
		log.info("findAllUserWithGroup()");
		return userDAO.findAllWithGroup();
	}

	@Override
	@CTransaction("Use custom transaction")
	public void generateAllUserFiles(CSession cSession, List<User> users) throws IOException {
		for (User user : users) {
			String file = "/path/user-" + user.getId() + ".info"; 
			cSession.put(file);
			log.info(file);
		}
		//throw new IOException("Error to create files.");
	}

}
