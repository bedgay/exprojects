package javaz.hibernate.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tblGroup")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"permissions", "users"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Group extends SuperInteger implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Name", nullable = false, length = 50)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "tblGroupPermission", joinColumns = { @JoinColumn(name = "GroupID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "PermissionID", nullable = false, updatable = false) })
	@JsonIgnore
	private Set<Permission> permissions = new HashSet<Permission>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	@JsonIgnore
	private Set<User> users = new HashSet<User>(0);
	
	public Group() {
	}

	public Group(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
