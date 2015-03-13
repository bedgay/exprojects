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

import org.shitoryu.data.dto.PageDTO;

/**
 * @author SUCCESS\tungo
 * @date Oct 20, 2014 11:46:38 AM
 */
@Entity
@Table(name = "tbPages")
public class Page extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Image")
	private String image;

	@Column(name = "body", length = 1024)
	private String body;

	@Column(name = "TheOrder")
	private Integer order;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "BookID", nullable = false)
	private Book book;

	public Page() {
	}

	public Page(String image, String body, Integer order) {
		this.image = image;
		this.body = body;
		this.order = order;
	}

	@SuppressWarnings("unchecked")
	@Transient
	@Override
	public PageDTO toDTO() {
		return new PageDTO(id, image, body, order);
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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
