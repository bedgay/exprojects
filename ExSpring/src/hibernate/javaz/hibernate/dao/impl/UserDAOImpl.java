package javaz.hibernate.dao.impl;

import java.util.List;

import javaz.hibernate.dao.UserDAO;
import javaz.hibernate.entity.User;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
@SuppressWarnings("unchecked")
public class UserDAOImpl extends DAOImpl<User, Integer> implements UserDAO {

	public UserDAOImpl() {
		super();
	}

	@Override
	public User findByUsername(String username) {
		String hql = "SELECT u FROM User u WHERE u.username LIKE :username";
		Query query = getSession().createQuery(hql);
		query.setString("username", username);
		List<User> users = query.list();

		if (!CollectionUtils.isEmpty(users)) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public List<User> findAllWithGroup() {
		Query query = getSession().createQuery("FROM User as u INNER JOIN FETCH u.group as g");
		return query.list();
	}

}
