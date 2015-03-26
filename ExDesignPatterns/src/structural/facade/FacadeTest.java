package structural.facade;

/**
 * @author SUCCESS\tungo
 * @url: http://sourcemaking.com/design_patterns/facade
 */
public class FacadeTest {

	public static void main(String[] args) {
		new FacadeTest();
	}

	/**
	 * Constructor
	 */
	public FacadeTest() {
		Mortgage mortgage = new Mortgage();
		Customer customer = new Customer("TuNgo");
		Boolean check = mortgage.isEligible(customer, 1000000000);
		System.out.println(customer.getName() + "'s application form has been "
				+ (check ? "approved" : "rejected"));
	}

	class Customer {
		private String name;

		public Customer(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	class Bank {
		public Boolean hasSavings(Customer customer, Integer amount) {
			System.out.println("Check Bank for: " + customer.getName());
			return Boolean.TRUE;
		}
	}

	class Credit {
		public Boolean hasGoodCredit(Customer customer) {
			System.out.println("Check Credit for: " + customer.getName());
			return Boolean.TRUE;
		}
	}

	class Loan {
		public Boolean hasBadLoan(Customer customer) {
			System.out.println("Check Loan for: " + customer.getName());
			return Boolean.FALSE;
		}
	}

	class Mortgage {
		Bank bank = new Bank();
		Credit credit = new Credit();
		Loan loan = new Loan();

		public Boolean isEligible(Customer customer, Integer amount) {
			System.out.println(customer.getName() + " apply for " + amount
					+ " loan");
			return bank.hasSavings(customer, amount)
					&& credit.hasGoodCredit(customer)
					&& !loan.hasBadLoan(customer);
		}
	}

}
