package javaz.store.service;

import java.util.List;

import javaz.store.entity.Customer;
import javaz.store.entity.PaymentCompare;

public interface StoreService {

	List<Customer> findAllCustomer();

	List<Customer> findCustomerByPayment(Float paymnet, PaymentCompare compare);

}
