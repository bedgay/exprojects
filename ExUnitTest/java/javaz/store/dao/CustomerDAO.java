package javaz.store.dao;

import java.util.List;

import javaz.store.entity.Customer;

/**
 * @author SUCCESS\tungo
 *
 */
public interface CustomerDAO {
	
	List<Customer> findAll();

}
