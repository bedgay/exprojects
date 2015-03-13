package org.shitoryu.data.dto;

import java.io.Serializable;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 10:23:07 AM
 */
public class SubCategoryDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private CategoryDTO category;

	public SubCategoryDTO() {
	}

	public SubCategoryDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public SubCategoryDTO(Integer id, String name, CategoryDTO category) {
		this.id = id;
		this.name = name;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

}
