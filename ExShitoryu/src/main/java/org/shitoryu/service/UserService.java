package org.shitoryu.service;

import org.shitoryu.data.dto.UserDTO;

/**
 * @author SUCCESS\tungo
 * @date Oct 21, 2014 9:43:31 AM
 */
public interface UserService {

	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	UserDTO login(String userName, String password);

}
