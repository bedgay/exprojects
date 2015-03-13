package javaz.test.easymock;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javaz.store.dao.CustomerDAO;
import javaz.store.dao.impl.CustomerDAOImpl;
import javaz.store.entity.Customer;
import javaz.store.entity.Invoice;
import javaz.store.entity.PaymentCompare;
import javaz.store.service.StoreService;
import javaz.store.service.impl.StoreServiceImpl;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StoreServiceTest {

	private CustomerDAO customerDAO;
	private StoreService storeService;

	@BeforeClass
	public static void oneTimeSetUp() {
		// one-time initialization code
		System.out.println("@BeforeClass - oneTimeSetUp");
	}

	@AfterClass
	public static void oneTimeTearDown() {
		// one-time cleanup code
		System.out.println("@AfterClass - oneTimeTearDown");
	}

	@Before
	public void setUp() {
		System.out.println("@Before - setUp");

		customerDAO = EasyMock.createMock(CustomerDAOImpl.class);
		storeService = EasyMock.createMockBuilder(StoreServiceImpl.class)
				.withConstructor(CustomerDAO.class)
				.withArgs(customerDAO).createMock();

	}

	@After
	public void tearDown() {
		System.out.println("@After - tearDown");
		storeService = null;
	}

	private List<Customer> createCustomers(int size) {
		List<Customer> customers = new ArrayList<>(size);

		for (int i = 1; i <= size; i++) {
			Customer cus = new Customer(i, "Name " + i);
			List<Invoice> invoices = new ArrayList<>();
			for (int j = 1; j <= i; j++) {
				String str = "" + i + j;
				Invoice inv = new Invoice(Integer.valueOf(str), new Date(),
						j * 1000f);
				invoices.add(inv);
			}
			cus.setIvoices(invoices);
			customers.add(cus);
		}

		return customers;
	}

	@Test
	public void case_1_1_findCustomerByPayment() {
		System.out.println("@Test - case_1_1_findCustomerByPayment");

		EasyMock.expect(customerDAO.findAll()).andReturn(createCustomers(5));
		EasyMock.replay(customerDAO);

		List<Customer> customers = storeService.findCustomerByPayment(1000.0f,
				PaymentCompare.EQUALS);
		assertTrue(customers.size() > 0);
	}

	@Test
	public void case_2_1_findCustomerByPayment() {
		System.out.println("@Test - case_2_1_findCustomerByPayment");

		EasyMock.expect(customerDAO.findAll()).andReturn(createCustomers(5));
		EasyMock.replay(customerDAO);

		List<Customer> customers = storeService.findCustomerByPayment(null,
				PaymentCompare.MORE_OR_EQUALS);
		assertTrue(customers.size() > 0);
	}

	@Test
	public void case_2_2_findCustomerByPayment() {
		System.out.println("@Test - case_2_2_findCustomerByPayment");

		List<Customer> customers = storeService.findCustomerByPayment(null,
				PaymentCompare.MORE_OR_EQUALS);
		assertTrue(customers.size() > 0);
	}

}
