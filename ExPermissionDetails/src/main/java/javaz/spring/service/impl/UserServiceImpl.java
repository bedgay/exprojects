package javaz.spring.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javaz.data.dto.RoleDTO;
import javaz.data.dto.UserDTO;
import javaz.hibernate.dao.PermissionDAO;
import javaz.hibernate.dao.RoleDAO;
import javaz.hibernate.dao.UserDAO;
import javaz.hibernate.entity.Permission;
import javaz.hibernate.entity.Role;
import javaz.hibernate.entity.User;
import javaz.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author TuNgo
 * @date Sep 22, 2014 11:20:36 PM
 */
@Service
public class UserServiceImpl extends ServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private PermissionDAO permissionDAO;

	@Override
	public UserDTO login(User user) {
		User dbUser = userDAO.getByUsername(user.getUsername());
		if (dbUser != null && user.getPassword().equals(dbUser.getPassword())) {
			UserDTO userDTO = dbUser.toDTO(); 
			userDTO.setPermissions(listPermissions(permissionDAO.findAllByUser(dbUser.getId())));
			return userDTO;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserDTO getUser(Integer id) {
		User user = userDAO.get(id);
		UserDTO userDTO = user.toDTO();

		List<RoleDTO> roles = (List<RoleDTO>) toDTOs(roleDAO.findAll());
		for (Iterator<RoleDTO> i = roles.iterator(); i.hasNext();) {
			RoleDTO roleDTO = i.next();
			for (Role role : user.getRoles()) {
				if (roleDTO.getId().equals(role.getId())) {
					roleDTO.setChosen(Boolean.TRUE);
					break;
				}
			}
		}

		userDTO.setRoleDTOs(roles);
		return userDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> findAllUser() {
		List<User> users = userDAO.findAll();
		return (List<UserDTO>) toDTOs(users);
	}

	@Override
	public boolean applyRoles4User(Integer userId, Integer[] roleIDs) {
		if (userId != null) {
			User user = userDAO.get(userId);
			if (user != null) {
				if (roleIDs != null && roleIDs.length > 0) {
					user.setRoles(roleDAO.findByIDs(roleIDs));
				} else {
					user.setRoles(null);
				}
				userDAO.save(user);
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
	private List<String> listPermissions(List<Permission> permissions) {
		List<String> list = new ArrayList<>();
		if (!CollectionUtils.isEmpty(permissions)) {
			for (Permission permis : permissions) {
				list.add(permis.getCode());
			}
		}
		return list;
	}

}
