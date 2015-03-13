package javaz.store.entity;

import java.util.Date;

/**
 * @author SUCCESS\tungo
 *
 */
public class Invoice {

	private Integer id;

	private Date createDate;

	private Float payment;

	private Customer customer;

	public Invoice() {
	}

	public Invoice(Integer id, Date createDate, Float payment) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.payment = payment;
	}

	public Invoice(Integer id, Date createDate, Float payment, Customer customer) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.payment = payment;
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Float getPayment() {
		return payment;
	}

	public void setPayment(Float payment) {
		this.payment = payment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
