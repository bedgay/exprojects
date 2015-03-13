package javaz.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SuperString {

	@Id
	@Column(name = "ID")
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
