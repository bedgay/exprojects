package javaz.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import javaz.store.dao.CustomerDAO;
import javaz.store.entity.Customer;
import javaz.store.entity.Invoice;
import javaz.store.entity.PaymentCompare;
import javaz.store.service.StoreService;

public class StoreServiceImpl implements StoreService {

	private CustomerDAO customerDAO;

	public StoreServiceImpl(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	public List<Customer> findAllCustomer() {
		return customerDAO.findAll();
	}

	@Override
	public List<Customer> findCustomerByPayment(Float payment,
			PaymentCompare compare) {
		List<Customer> list = new ArrayList<Customer>();
		List<Customer> customers = customerDAO.findAll();

		for (Customer cus : customers) {
			Float total = 0f;
			for (Invoice inv : cus.getIvoices()) {
				total += inv.getPayment();
			}
			if (compare.equals(PaymentCompare.LESS)) {
				if (payment.compareTo(total) < 0) {
					list.add(cus);
				}
			} else if (compare.equals(PaymentCompare.LESS_OR_EQUALS)) {
				if (payment.compareTo(total) <= 0) {
					list.add(cus);
				}
			} else if (compare.equals(PaymentCompare.EQUALS)) {
				if (payment == total) {
					list.add(cus);
				}
			} else if (compare.equals(PaymentCompare.MORE)) {
				if (payment.compareTo(total) > 0) {
					list.add(cus);
				}
			} else if (compare.equals(PaymentCompare.MORE_OR_EQUALS)) {
				if (payment.compareTo(total) >= 0) {
					list.add(cus);
				}
			}
		}

		return list;
	}

}
