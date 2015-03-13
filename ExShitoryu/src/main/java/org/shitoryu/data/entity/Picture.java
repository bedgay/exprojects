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

import org.shitoryu.data.dto.PictureDTO;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 10:46:22 AM
 */
@Entity
@Table(name = "tbPicture")
public class Picture extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Thumb", nullable = false)
	private String thumb;

	@Column(name = "Image", nullable = false)
	private String image;

	@Column(name = "Description", length = 500)
	private String description;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "AlbumID", nullable = false)
	private Album album;

	public Picture() {
	}

	public Picture(String thumb, String image, String description) {
		this.thumb = thumb;
		this.image = image;
		this.description = description;
	}

	@SuppressWarnings("unchecked")
	@Transient
	@Override
	public PictureDTO toDTO() {
		return new PictureDTO(id, thumb, description, image);
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

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}
