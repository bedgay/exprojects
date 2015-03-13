package javaz.hibernate.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import javaz.data.dto.PermissionDTO;

/**
 * @author SUCCESS\tungo
 * @date Sep 19, 2014 1:49:28 PM
 */
@Entity
@Table(name = "tblPermission")
public class Permission extends SuperEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Code", unique = true, nullable = false)
	private String code;

	@Column(name = "Name", unique = true, nullable = false)
	private String name;

	@ManyToMany(mappedBy = "permissions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Role> roles = new ArrayList<>();

	/**
	 * Constructor
	 */
	public Permission() {
	}

	/**
	 * @param code
	 * @param name
	 */
	public Permission(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	@SuppressWarnings("unchecked")
	@Transient
	@Override
	public PermissionDTO toDTO() {
		return new PermissionDTO(id, code, name);
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
