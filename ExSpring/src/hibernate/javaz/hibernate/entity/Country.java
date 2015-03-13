package javaz.hibernate.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tblCountry")
public class Country extends SuperString implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "Name", nullable = false, length = 50)
	private String name;

	@Column(name = "PostCode", nullable = true, length = 4)
	private String postCode;

	@Column(name = "CurrencyCode", nullable = true, length = 3)
	private String currencyCode;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
	private Set<Profile> profiles = new HashSet<Profile>(0);

	public Country() {
	}

	public Country(String id, String name, String postCode, String currencyCode) {
		this.id = id;
		this.name = name;
		this.postCode = postCode;
		this.currencyCode = currencyCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Set<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
	}

}
