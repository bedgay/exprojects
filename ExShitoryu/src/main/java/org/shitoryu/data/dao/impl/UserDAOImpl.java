package org.shitoryu.data.dao.impl;

import java.io.Serializable;

import org.shitoryu.data.dao.UserDAO;
import org.shitoryu.data.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author SUCCESS\tungo
 * @date Oct 21, 2014 9:39:17 AM
 */
@Repository
public class UserDAOImpl extends DAOImpl<User, Integer> implements UserDAO,
		Serializable {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.shitoryu.data.dao.UserDAO#getByUsername(java.lang.String)
	 */
	@Override
	public User getByUsername(String userName) {
		return getByHql("FROM User u WHERE u.userName = '" + userName + "'");
	}

}
