package javaz.hibernate.dao.impl;

import javaz.hibernate.dao.CountryDAO;
import javaz.hibernate.entity.Country;

import org.springframework.stereotype.Repository;

@Repository
public class CountryDAOImpl extends DAOImpl<Country, String> implements CountryDAO {

	public CountryDAOImpl() {
		super();
	}

}
