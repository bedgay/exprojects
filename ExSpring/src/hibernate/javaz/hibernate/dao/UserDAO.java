package javaz.hibernate.dao;

import java.util.List;

import javaz.hibernate.entity.User;

public interface UserDAO extends DAO<User, Integer> {
	
	User findByUsername(String username);
	
	List<User> findAllWithGroup();

}
