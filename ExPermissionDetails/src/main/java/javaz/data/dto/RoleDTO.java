package javaz.data.dto;

import java.io.Serializable;

/**
 * @author SUCCESS\tungo
 * @date Oct 2, 2014 4:54:29 PM
 */
public class RoleDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String permissions;

	private boolean chosen = Boolean.FALSE;

	public RoleDTO() {
	}

	public RoleDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public RoleDTO(Integer id, String name, String permissions) {
		this.id = id;
		this.name = name;
		this.permissions = permissions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public boolean isChosen() {
		return chosen;
	}

	public void setChosen(boolean isChosen) {
		this.chosen = isChosen;
	}

}
