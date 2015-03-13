package javaz.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import javaz.data.dto.DTO;


/**
 * @author SUCCESS\tungo
 * @date Sep 19, 2014 11:43:57 AM
 */
@MappedSuperclass
public abstract class SuperEntity {
	
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
	public <T extends DTO> T toDTO() {
		return null;
	}

}
