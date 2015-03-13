package javaz.hibernate.dao.impl;

import org.springframework.stereotype.Repository;

import javaz.hibernate.dao.UserDAO;
import javaz.hibernate.entity.User;

/**
 * @author SUCCESS\tungo
 * @date Sep 20, 2014 10:27:59 AM
 */
@Repository
public class UserDAOImpl extends DAOImpl<User, Integer> implements UserDAO {

	@Override
	public User getByUsername(String username) {
		return getByHql("FROM User u WHERE u.username = '" + username + "'");
	}

}
