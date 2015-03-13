package javaz.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass 
public abstract class SuperInteger {
	
	@Id
	@Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
