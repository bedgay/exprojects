package javaz.hibernate.dao.impl;

import org.springframework.stereotype.Repository;

import javaz.hibernate.dao.LearningDAO;
import javaz.hibernate.entity.Learning;

@Repository
public class LearningDAOImpl extends DAOImpl<Learning, Long> implements LearningDAO {

}
