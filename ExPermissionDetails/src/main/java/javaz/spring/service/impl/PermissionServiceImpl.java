package javaz.spring.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javaz.data.dto.PermissionDTO;
import javaz.data.dto.RoleDTO;
import javaz.data.dto.UserDTO;
import javaz.hibernate.dao.PermissionDAO;
import javaz.hibernate.dao.RoleDAO;
import javaz.hibernate.dao.RoleDetailDAO;
import javaz.hibernate.dao.UserDAO;
import javaz.hibernate.entity.Permission;
import javaz.hibernate.entity.Role;
import javaz.hibernate.entity.RoleDetail;
import javaz.hibernate.entity.User;
import javaz.spring.aop.AuthoritySubject;
import javaz.spring.aop.AuthorityPermission;
import javaz.spring.service.PermissionService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author SUCCESS\tungo
 * @date Sep 20, 2014 10:47:17 AM
 */
@SuppressWarnings("unchecked")
@Service
public class PermissionServiceImpl extends ServiceImpl implements
		PermissionService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PermissionDAO permissionDAO;

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private RoleDetailDAO roleDetailDAO;

	@SuppressWarnings("deprecation")
	@Override
	public void init() {
		List<User> users = userDAO.findAll();

		if (CollectionUtils.isEmpty(users)) {
			// Create new for Users
			User admin = new User("admin", "admin");
			admin = userDAO.save(admin);

			User user = new User("user", "user");
			userDAO.save(user);

			// Create new for Permissions
			for (AuthorityPermission permis : AuthorityPermission.values()) {
				Permission permission = new Permission(permis.name(),
						StringUtils.capitalise(permis.name().replace("_", " ")
								.toLowerCase()));
				permissionDAO.save(permission);
			}

			// Create new for Role
			List<Role> roles = new ArrayList<>();
			for (AuthoritySubject subject : AuthoritySubject.values()) {
				Role role = new Role(subject.name() + " admin");
				role.getPermissions().addAll(
						permissionDAO.findByName(subject.name() + "%"));
				roles.add(role);
			}
			admin.setRoles(roles);
			userDAO.save(admin);
		}
	}

	@Override
	public List<PermissionDTO> findAllPermis() {
		return (List<PermissionDTO>) toDTOs(permissionDAO.findAll());
	}

	@Override
	public List<RoleDTO> findAllRole() {
		return (List<RoleDTO>) toDTOs(roleDAO.findAll());
	}

	@Override
	public List<RoleDTO> findAllRole(String subject, Integer subjectId) {
		List<RoleDTO> roles = (List<RoleDTO>) toDTOs(roleDAO.findAll());

		for (Iterator<RoleDTO> i = roles.iterator(); i.hasNext();) {
			RoleDTO role = i.next();
			RoleDetail roleDetail = roleDetailDAO.findBySubjectAndRole(subject,
					subjectId, role.getId());
			if (roleDetail != null) {
				role.setChosen(Boolean.TRUE);
			}
		}

		return roles;
	}

	@Override
	public boolean checkPermis(UserDTO userDTO, String[] permissions) {
		List<Role> list = roleDAO.findRoleByUserAndPermis(userDTO.getId(),
				permissions);
		return list != null && list.size() > 0;
	}

	@Override
	public boolean checkPermisForOne(UserDTO userDTO, String subject, String[] permissions,
			Integer subjectId) {
		return roleDetailDAO.checkSubjectIDByUserAndPermissions(userDTO.getId(), permissions, subject, subjectId);
	}

	@Override
	public List<Integer> checkPermisForAll(UserDTO userDTO, String subject, String[] permissions) {
		return roleDetailDAO.findSubjectIDsByUserAndPermissions(
				userDTO.getId(), subject, permissions);
	}

	@Override
	public String[] castPermissions(AuthorityPermission[] permissions) {
		String[] permis = new String[permissions.length];
		for (int i = 0; i < permis.length; i++) {
			permis[i] = permissions[i].name();
		}
		return permis;
	}

	@Override
	public boolean saveRole(Integer roleId, String roleName, Integer[] permisIDs) {
		Role role = null;
		if (roleId == null) {
			role = new Role(roleName);
		} else {
			role = roleDAO.get(roleId);
			if (role == null) {
				role = new Role(roleName);
			} else {
				role.setName(roleName);
			}
		}
		role.setPermissions(permissionDAO.findByIDs(permisIDs));

		if (role.getId() == null) {
			roleDAO.save(role);
		} else {
			roleDAO.update(role);
		}
		return Boolean.TRUE;
	}

	@Override
	public boolean applyRole(String subject, Integer subjectId,
			Integer[] roleIDs) {
		roleDetailDAO.delBySubject(subject, subjectId);
		List<Role> roles = roleDAO.findByIDs(roleIDs);
		
		for (Iterator<Role> i = roles.iterator(); i.hasNext();) {
			RoleDetail roleDetail = new RoleDetail(subject, subjectId, i.next());
			roleDetailDAO.save(roleDetail);
		}
		
		return Boolean.TRUE;
	}

}
