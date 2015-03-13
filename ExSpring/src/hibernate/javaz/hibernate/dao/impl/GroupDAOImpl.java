package javaz.hibernate.dao.impl;

import org.springframework.stereotype.Repository;

import javaz.hibernate.dao.GroupDAO;
import javaz.hibernate.entity.Group;

@Repository
public class GroupDAOImpl extends DAOImpl<Group, Integer> implements GroupDAO {

}
