package javaz.security.service.impl;

import javaz.security.service.LoginService;

public class LoginServiceImpl implements LoginService {

	public String login(String username, String password) {
//		if (user.containsKey(username)) {
			return user.get(username).equals(password) ? username : null;
//		}
//		return null;
	}
	
	public Boolean changePassword(String username, String oldPassword, String newPassword) {
		return null;
	}
	
}
