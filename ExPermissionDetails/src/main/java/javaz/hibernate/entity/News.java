package javaz.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javaz.data.dto.NewsDTO;

/**
 * @author SUCCESS\tungo
 * @date Oct 16, 2014 9:17:21 AM
 */
@Entity
public class News extends SuperEntity {

	@Column(name = "Title", nullable = false)
	private String title;

	@Column(name = "Description", nullable = false)
	private String description;

	public News() {
	}

	public News(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public NewsDTO toDTO() {
		return new NewsDTO(id, title, description);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
