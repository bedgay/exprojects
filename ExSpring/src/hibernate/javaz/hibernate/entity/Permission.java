package javaz.hibernate.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tblPermission")
public class Permission extends SuperInteger implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Code", nullable = false, length = 10, unique = true)
	private String code;

	@Column(name = "Name", nullable = false, length = 50)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
	private Set<Group> groups = new HashSet<Group>(0);

	public Permission() {
	}

	public Permission(String code, String name) {
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

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
