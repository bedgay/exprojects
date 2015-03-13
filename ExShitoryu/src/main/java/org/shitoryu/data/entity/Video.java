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

import org.shitoryu.data.dto.VideoDTO;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 10:31:45 AM
 */
@Entity
@Table(name = "tbEntities")
public class Video extends AbstractEntity<VideoDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Name", nullable = false, length = 100)
	private String name;

	@Column(name = "Thumb")
	private String thumb;

	@Column(name = "Video", nullable = false)
	private String video;

	@Column(name = "Description")
	private String description;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "SubCategoryID", nullable = false)
	private SubCategory subCaterory;

	public Video() {
	}

	public Video(String name, String thumb, String video, String description) {
		this.name = name;
		this.thumb = thumb;
		this.video = video;
		this.description = description;
	}

	@Transient
	@Override
	public VideoDTO toDTO() {
		return new VideoDTO(id, name, thumb, video, description);
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

	public SubCategory getSubCaterory() {
		return subCaterory;
	}

	public void setSubCaterory(SubCategory subCaterory) {
		this.subCaterory = subCaterory;
	}

}
