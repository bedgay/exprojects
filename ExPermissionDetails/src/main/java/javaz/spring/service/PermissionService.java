package javaz.spring.service;

import java.util.List;

import javaz.data.dto.PermissionDTO;
import javaz.data.dto.RoleDTO;
import javaz.data.dto.UserDTO;
import javaz.spring.aop.AuthorityPermission;




/**
 * @author SUCCESS\tungo
 * @date Sep 20, 2014 10:43:41 AM
 */
public interface PermissionService extends Service {
	
	void init();
	
	List<PermissionDTO> findAllPermis();

	List<RoleDTO> findAllRole();
	
	List<RoleDTO> findAllRole(String subject, Integer subjectId);

	boolean checkPermis(UserDTO userDTO, String[] permissions);
	
	boolean checkPermisForOne(UserDTO userDTO, String subject, String[] permissions, Integer subjectId);
	
	List<Integer> checkPermisForAll(UserDTO userDTO, String subject, String[] permissions);
	
	String[] castPermissions(AuthorityPermission[] permissions);
	
	boolean saveRole(Integer roleId, String roleName, Integer[] permisIDs);

	boolean applyRole(String subject, Integer subjectId, Integer[] roleIDs);

}
