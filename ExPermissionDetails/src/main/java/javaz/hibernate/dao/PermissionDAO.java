package javaz.hibernate.dao;

import java.util.List;

import javaz.hibernate.entity.Permission;
import javaz.spring.aop.AuthorityPermission;

/**
 * @author SUCCESS\tungo
 * @date Sep 20, 2014 10:37:01 AM
 */
public interface PermissionDAO extends DAO<Permission, Integer> {
	
	/**
	 * Get Permission by Code
	 * @param permis
	 * @return
	 */
	Permission getByCode(AuthorityPermission permis);
	
	/**
	 * @param ids
	 * @return
	 */
	List<Permission> findByIDs(Integer[] ids);
	
	/**
	 * @param userId
	 * @return
	 */
	List<Permission> findAllByUser(Integer userId);

	/**
	 * @param name
	 * @return
	 */
	List<Permission> findByName(String name);

}
