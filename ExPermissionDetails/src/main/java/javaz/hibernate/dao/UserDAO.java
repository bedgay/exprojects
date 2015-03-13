package javaz.hibernate.dao;

import javaz.hibernate.entity.User;

/**
 * @author SUCCESS\tungo
 * @date Sep 20, 2014 10:24:10 AM
 */
public interface UserDAO extends DAO<User, Integer> {
	
	/**
	 * @return
	 */
	User getByUsername(String username);

}
