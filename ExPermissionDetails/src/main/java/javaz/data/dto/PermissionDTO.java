package javaz.data.dto;

import java.io.Serializable;

/**
 * @author SUCCESS\tungo
 * @date Oct 2, 2014 4:46:46 PM
 */
public class PermissionDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String name;

	public PermissionDTO() {
	}

	public PermissionDTO(Integer id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
