package javaz.hibernate.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tblUser")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class User extends SuperInteger implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "UserName", unique = true)
	private String username;

	@Column(name = "Password")
	private String password;

	@Column(name = "Active")
	private Boolean active;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GroupID", nullable = false)
	private Group group;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Profile profile;

	public User() {
	}

	public User(String username, String password, Boolean active) {
		this.username = username;
		this.password = password;
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
