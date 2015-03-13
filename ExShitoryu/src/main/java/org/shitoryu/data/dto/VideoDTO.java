package org.shitoryu.data.dto;

import java.io.Serializable;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 11:35:36 AM
 */
public class VideoDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String thumb;
	private String video;
	private String description;

	public VideoDTO() {
	}

	public VideoDTO(Integer id, String name, String thumb, String video,
			String description) {
		this.id = id;
		this.name = name;
		this.thumb = thumb;
		this.video = video;
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

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
