package javaz.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tblCourse")
public class Course extends SuperInteger implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Name", nullable = false, length = 50)
	private String name;

	public Course() {
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
