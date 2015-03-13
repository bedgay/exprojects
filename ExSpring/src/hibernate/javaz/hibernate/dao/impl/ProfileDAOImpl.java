package javaz.hibernate.dao.impl;

import org.springframework.stereotype.Repository;

import javaz.hibernate.dao.ProfileDAO;
import javaz.hibernate.entity.Profile;

@Repository
public class ProfileDAOImpl extends DAOImpl<Profile, Integer> implements ProfileDAO {

}
