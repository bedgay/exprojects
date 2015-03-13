package org.shitoryu.data.dto;

import java.io.Serializable;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 10:51:35 AM
 */
public class PictureDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String thumb;
	private String image;
	private String description;

	public PictureDTO() {
	}

	public PictureDTO(Integer id, String thumb, String description, String image) {
		this.id = id;
		this.thumb = thumb;
		this.image = image;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
