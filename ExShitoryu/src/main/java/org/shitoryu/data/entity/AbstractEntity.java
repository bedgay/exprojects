package org.shitoryu.data.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.shitoryu.data.dto.DTO;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 9:59:26 AM
 */
@MappedSuperclass
public abstract class AbstractEntity<T extends DTO> {

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

	@Transient
	public T toDTO() {
		return null;
	}

}
