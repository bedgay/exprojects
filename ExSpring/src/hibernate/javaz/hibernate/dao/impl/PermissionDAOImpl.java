package javaz.hibernate.dao.impl;

import javaz.hibernate.dao.PermissionDAO;
import javaz.hibernate.entity.Permission;

import org.springframework.stereotype.Repository;

@Repository
public class PermissionDAOImpl extends DAOImpl<Permission, Integer> implements PermissionDAO {

}
