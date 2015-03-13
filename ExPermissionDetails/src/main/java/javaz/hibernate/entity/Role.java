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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.Joiner;

import javaz.data.dto.RoleDTO;

/**
 * @author SUCCESS\tungo
 * @date Sep 19, 2014 1:49:31 PM
 */
@Entity
@Table(name = "tblRole")
public class Role extends SuperEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Name", unique = true, nullable = false)
	private String name;

	@ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User> users = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "refRolePermissions", joinColumns = { @JoinColumn(name = "RoleID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "PermissionID", referencedColumnName = "ID") })
	private List<Permission> permissions = new ArrayList<>();

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RoleDetail> details = new ArrayList<>();

	/**
	 * Constructor
	 */
	public Role() {
	}

	/**
	 * @param name
	 */
	public Role(String name) {
		this.name = name;
	}

	@SuppressWarnings("unchecked")
	@Transient
	@Override
	public RoleDTO toDTO() {
		List<String> permissions = new ArrayList<>();
		for (Permission permis : this.getPermissions()) {
			permissions.add(permis.getName());
		}
		return new RoleDTO(id, name, Joiner.on(", ").skipNulls()
				.join(permissions));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<RoleDetail> getDetails() {
		return details;
	}

	public void setDetails(List<RoleDetail> details) {
		this.details = details;
	}

}
