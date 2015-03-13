package javaz.data.dto;

import java.io.Serializable;

/**
 * @author SUCCESS\tungo
 * @date Oct 16, 2014 9:38:14 AM
 */
public class NewsDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String description;

	public NewsDTO() {
	}

	public NewsDTO(Integer id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
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
