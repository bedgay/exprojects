package org.shitoryu.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.shitoryu.data.dto.CategoryDTO;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 9:57:37 AM
 */
@Entity
@Table(name = "tbCategories")
public class Category extends AbstractEntity<CategoryDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum CType {
		IMAGE, VIDEO, BOOKS
	}

	@Column(name = "Type", nullable = false, length = 5)
	private String type;

	@Column(name = "Name", nullable = false, length = 50)
	private String name;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SubCategory> subCategories = new ArrayList<>();

	public Category() {
	}

	public Category(String type, String name) {
		this.type = type;
		this.name = name;
	}

	@Transient
	@Override
	public CategoryDTO toDTO() {
		return new CategoryDTO(id, type, name);
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

	public List<SubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}

}
