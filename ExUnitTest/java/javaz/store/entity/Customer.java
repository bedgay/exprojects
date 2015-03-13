package javaz.store.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SUCCESS\tungo
 *
 */
public class Customer {

	private Integer id;

	private String name;

	private List<Invoice> ivoices = new ArrayList<>();

	public Customer() {
	}

	public Customer(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Invoice> getIvoices() {
		return ivoices;
	}

	public void setIvoices(List<Invoice> ivoices) {
		this.ivoices = ivoices;
	}

}
