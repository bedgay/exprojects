package org.shitoryu.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.shitoryu.data.dto.UserDTO;

/**
 * @author SUCCESS\tungo
 * @date Oct 21, 2014 9:28:52 AM
 */
@Entity
@Table(name = "tbUsers")
public class User extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "UserName", unique = true, nullable = false, length = 50)
	private String userName;

	@Column(name = "Password", nullable = false, length = 50)
	private String password;

	@Column(name = "FullName", nullable = false, length = 50)
	private String fullName;

	public User() {
	}

	public User(String userName, String password, String fullName) {
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
	}

	@SuppressWarnings("unchecked")
	@Transient
	@Override
	public UserDTO toDTO() {
		return new UserDTO(id, userName, password, fullName);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
