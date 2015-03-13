package javaz.hibernate.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import javaz.data.dto.UserDTO;

import com.google.common.base.Joiner;

/**
 * @author SUCCESS\tungo
 * @date Sep 19, 2014 11:41:52 AM
 */
/**
 * @author SUCCESS\tungo
 * @date Sep 20, 2014 11:34:28 AM
 */
@Entity
@Table(name = "tblUser")
public class User extends SuperEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Username", unique = true, nullable = false)
	private String username;

	@Column(name = "Password", nullable = false)
	private String password;

	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinTable(name = "refUserRoles", joinColumns = { @JoinColumn(name = "UserID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "RoleID", referencedColumnName = "ID") })
	private List<Role> roles = new ArrayList<Role>();
	
	/**
	 * Constructor
	 */
	public User() {
	}
	
	/**
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@SuppressWarnings("unchecked")
	@Transient
	public UserDTO toDTO() {
		List<String> roles = new ArrayList<>();
		for (Role role: this.getRoles()) {
			roles.add(role.getName());
		}
		return new UserDTO(id, username, Joiner.on(", ").skipNulls().join(roles));
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
