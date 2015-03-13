package org.shitoryu.data.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 10:41:39 AM
 */
public class AlbumDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private Date createDate = new Date();
	private String thumb;
	private String image;
	private boolean slideshow;

	public AlbumDTO() {
	}

	public AlbumDTO(Integer id, String name, Date createDate, String thumb,
			String image, boolean slideshow) {
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.thumb = thumb;
		this.image = image;
		this.slideshow = slideshow;
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

}
