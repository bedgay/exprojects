package javaz.hibernate.dao;

import java.util.List;

import javaz.hibernate.entity.Role;

/**
 * @author SUCCESS\tungo
 * @date Sep 20, 2014 10:34:07 AM
 */
public interface RoleDAO extends DAO<Role, Integer> {
	
	/**
	 * @param ids
	 * @return
	 */
	List<Role> findByIDs(Integer[] ids);
	
	/**
	 * @param userId
	 * @param permis
	 * @return
	 */
	List<Role> findRoleByUserAndPermis(Integer userId, String[] permis);
	
	/**
	 * @param name
	 * @return
	 */
	List<Role> findByName(String name);

}
