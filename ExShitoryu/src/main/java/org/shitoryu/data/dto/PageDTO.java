package org.shitoryu.data.dto;

import java.io.Serializable;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 12:02:56 PM
 */
public class PageDTO extends DTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String image;
	private String body;
	private Integer order;

	public PageDTO() {
	}

	public PageDTO(Integer id, String image, String body, Integer order) {
		this.id = id;
		this.image = image;
		this.body = body;
		this.order = order;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}
