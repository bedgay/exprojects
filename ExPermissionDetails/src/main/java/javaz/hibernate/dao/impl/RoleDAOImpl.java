package javaz.hibernate.dao.impl;

import java.util.List;

import javaz.hibernate.dao.RoleDAO;
import javaz.hibernate.entity.Role;

import org.springframework.stereotype.Repository;

/**
 * @author SUCCESS\tungo
 * @date Sep 20, 2014 10:34:58 AM
 */
@SuppressWarnings("unchecked")
@Repository
public class RoleDAOImpl extends DAOImpl<Role, Integer> implements RoleDAO {

	@Override
	public List<Role> findByIDs(Integer[] ids) {
		return getSession().createQuery("FROM Role r WHERE r.id IN(:ids)")
				.setParameterList("ids", ids).list();
	}

	@Override
	public List<Role> findRoleByUserAndPermis(Integer userId, String[] permis) {
		return getSession()
				.createQuery(
						"SELECT r FROM Role r JOIN r.users u JOIN r.permissions p WHERE u.id = :userId AND p.code IN(:permis)")
				.setParameter("userId", userId)
				.setParameterList("permis", permis).list();
	}

	@Override
	public List<Role> findByName(String name) {
		return getSession()
				.createQuery(
						"FROM Role r WHERE r.name LIKE :name")
				.setParameter("name", name + "%").list();
	}

}
