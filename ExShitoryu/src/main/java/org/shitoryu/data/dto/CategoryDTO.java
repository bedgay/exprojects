package org.shitoryu.data.dto;

import java.io.Serializable;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 10:12:09 AM
 */
public class CategoryDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String type;
	private String name;

	public CategoryDTO() {
	}

	public CategoryDTO(Integer id, String type, String name) {
		this.id = id;	
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
