package org.shitoryu.data.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.shitoryu.data.dto.SubCategoryDTO;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 10:16:56 AM
 */
@Entity
@Table(name = "tbSubCategories")
public class SubCategory extends AbstractEntity<SubCategoryDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Name", nullable = false, length = 50)
	private String name;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CategoryID", nullable = false)
	private Category category;

	public SubCategory() {
	}

	public SubCategory(String name) {
		this.name = name;
	}
	
	public SubCategory(String name, Category category) {
		this.name = name;
		this.category = category;
	}

	@Transient
	@Override
	public SubCategoryDTO toDTO() {
		return new SubCategoryDTO(id, name, category.toDTO());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
