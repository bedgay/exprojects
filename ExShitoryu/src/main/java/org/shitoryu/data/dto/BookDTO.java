package org.shitoryu.data.dto;

import java.io.Serializable;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 12:02:23 PM
 */
public class BookDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String thumb;
	private String image;
	private String description;

	public BookDTO() {
	}

	public BookDTO(Integer id, String name, String thumb, String image,
			String description) {
		this.id = id;
		this.name = name;
		this.thumb = thumb;
		this.image = image;
		this.description = description;
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

}
