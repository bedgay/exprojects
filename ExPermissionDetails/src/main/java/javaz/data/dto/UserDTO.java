package javaz.data.dto;

import java.util.List;

import javaz.hibernate.entity.User;

/**
 * @author SUCCESS\tungo
 * @date Sep 30, 2014 2:24:28 PM
 */
public class UserDTO extends DTO {

	private String username;

	private String roles;

	private List<RoleDTO> roleDTOs;

	private List<String> permissions;

	public UserDTO() {
	}

	public UserDTO(Integer id, String username) {
		this.id = id;
		this.username = username;
	}

	public UserDTO(Integer id, String username, String roles) {
		this.id = id;
		this.username = username;
		this.roles = roles;
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public List<RoleDTO> getRoleDTOs() {
		return roleDTOs;
	}

	public void setRoleDTOs(List<RoleDTO> roleDTOs) {
		this.roleDTOs = roleDTOs;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

}
