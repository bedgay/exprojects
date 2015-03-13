package org.shitoryu.data.dao;

import org.shitoryu.data.entity.User;

/**
 * @author SUCCESS\tungo
 * @date Oct 21, 2014 9:37:18 AM
 */
public interface UserDAO extends DAO<User, Integer>{

	/**
	 * @param userName
	 * @return
	 */
	User getByUsername(String userName);
	
}
