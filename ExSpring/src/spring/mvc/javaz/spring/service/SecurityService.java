package javaz.spring.service;

import java.io.IOException;
import java.util.List;

import javaz.hibernate.entity.User;
import javaz.spring.aop.transaction.session.CSession;

/**
 * @author SUCCESS\tungo
 * @date Aug 5, 2014 11:42:23 AM
 */
public interface SecurityService {
	
	public static final String USER_AUTH = "USER";
	
	User findUserByUsername(String username);
	
	boolean checkLogin(User user);
	
	List<User> findAllUser();
	
	List<User> findAllUserWithGroup();
	
	void generateAllUserFiles(CSession cSession, List<User> users) throws IOException;

}
