package org.shitoryu.data.dto;

import java.io.Serializable;

/**
 * @author SUCCESS\tungo
 * @date Oct 21, 2014 9:33:38 AM
 */
public class UserDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private String fullName;

	public UserDTO() {
	}

	public UserDTO(Integer id, String userName, String password, String fullName) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
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
