package javaz.hibernate.dao.impl;

import java.util.List;

import javaz.hibernate.dao.PermissionDAO;
import javaz.hibernate.entity.Permission;
import javaz.spring.aop.AuthorityPermission;

import org.springframework.stereotype.Repository;

/**
 * @author SUCCESS\tungo
 * @date Sep 20, 2014 10:38:01 AM
 */
@SuppressWarnings("unchecked")
@Repository
public class PermissionDAOImpl extends DAOImpl<Permission, Integer> implements
		PermissionDAO {

	@Override
	public Permission getByCode(AuthorityPermission permis) {
		return getByHql("from Permission p where p.code = '" + permis.name()
				+ "'");
	}

	@Override
	public List<Permission> findByIDs(Integer[] ids) {
		return getSession()
				.createQuery("FROM Permission p WHERE p.id IN(:ids)")
				.setParameterList("ids", ids).list();
	}

	@Override
	public List<Permission> findAllByUser(Integer userId) {
		return getSession()
				.createQuery(
						"SELECT p FROM Permission p JOIN p.roles r JOIN r.users u WHERE u.id = :id")
				.setParameter("id", userId).list();
	}

	@Override
	public List<Permission> findByName(String name) {
		return getSession()
				.createQuery("FROM Permission p WHERE p.name LIKE :name")
				.setParameter("name", name).list();
	}

}
