package org.shitoryu.service.impl;

import org.shitoryu.data.dao.UserDAO;
import org.shitoryu.data.dto.UserDTO;
import org.shitoryu.data.entity.User;
import org.shitoryu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SUCCESS\tungo
 * @date Oct 21, 2014 9:48:11 AM
 */
@Service
public class UserServiceImpl extends ServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDTO login(String userName, String password) {
		User user = userDAO.getByUsername(userName);
		if (user != null && user.getPassword().equals(password)) {
			return user.toDTO();
		}
		return null;
	}

}
