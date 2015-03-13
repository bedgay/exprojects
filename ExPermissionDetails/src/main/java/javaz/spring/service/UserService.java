package javaz.spring.service;

import java.util.List;

import javaz.data.dto.UserDTO;
import javaz.hibernate.entity.User;

/**
 * @author TuNgo
 * @date Sep 22, 2014 11:19:26 PM
 */
public interface UserService extends Service {
	
	/**
	 * @return
	 */
	UserDTO login(User user);
	
	/**
	 * @param id
	 * @return
	 */
	UserDTO getUser(Integer id);
	
	/**
	 * @return
	 */
	List<UserDTO> findAllUser();
	
	/**
	 * @param userId
	 * @param roleIDs
	 * @return
	 */
	boolean applyRoles4User(Integer userId, Integer[] roleIDs);

}
