package org.shitoryu.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.shitoryu.data.dto.BookDTO;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 11:39:35 AM
 */
@Entity
@Table(name = "tbBooks")
public class Book extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Name", nullable = false, length = 100)
	private String name;

	@Column(name = "Thumb")
	private String thumb;

	@Column(name = "Image")
	private String image;

	@Column(name = "Description")
	private String description;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "SubCategoryID", nullable = false)
	private SubCategory subCategory;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Page> pages = new ArrayList<>();

	public Book() {
	}

	public Book(String name, String thumb, String image, String description) {
		this.name = name;
		this.thumb = thumb;
		this.image = image;
		this.description = description;
	}

	@SuppressWarnings("unchecked")
	@Transient
	@Override
	public BookDTO toDTO() {
		return new BookDTO(id, name, thumb, image, description);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

}
