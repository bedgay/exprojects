package org.shitoryu.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

import org.shitoryu.data.dto.AlbumDTO;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 10:37:14 AM
 */
@Entity
@Table(name = "tbAlbums")
public class Album extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Name", nullable = false, length = 100)
	private String name;

	@Column(name = "CreateDate")
	private Date createDate = new Date();

	@Column(name = "Thumb")
	private String thumb;

	@Column(name = "Image")
	private String image;

	@Column(name = "Slideshow", nullable = false)
	private boolean slideshow;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "SubCategoryID", nullable = false)
	private SubCategory subCaterory;

	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Picture> pictures = new ArrayList<>();

	public Album() {
	}

	public Album(String name, Date createDate, String thumb, String image,
			boolean slideshow) {
		this.name = name;
		this.createDate = createDate;
		this.thumb = thumb;
		this.image = image;
		this.slideshow = slideshow;
	}

	@SuppressWarnings("unchecked")
	@Transient
	@Override
	public AlbumDTO toDTO() {
		return new AlbumDTO(id, name, createDate, thumb, image, slideshow);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public boolean isSlideshow() {
		return slideshow;
	}

	public void setSlideshow(boolean slideshow) {
		this.slideshow = slideshow;
	}

	public SubCategory getSubCaterory() {
		return subCaterory;
	}

	public void setSubCaterory(SubCategory subCaterory) {
		this.subCaterory = subCaterory;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

}
