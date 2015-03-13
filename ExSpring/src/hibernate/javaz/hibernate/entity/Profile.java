package javaz.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tblProfile")
public class Profile extends SuperInteger implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "FirstName", length = 10)
	private String firstName;

	@Column(name = "LastName", length = 10)
	private String lastName;

	@Column(name = "Phone", length = 20)
	private String phone;

	@Column(name = "email", length = 50)
	private String email;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CountryID", nullable = false)
	private Country country;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user;

	public Profile() {
	}

	public Profile(String firstName, String lastName, String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
