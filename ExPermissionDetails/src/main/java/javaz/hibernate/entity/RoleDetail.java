package javaz.hibernate.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javaz.hibernate.entity.SuperEntity;

/**
 * @author SUCCESS\tungo
 * @date Sep 19, 2014 2:58:47 PM
 */
@Entity
@Table(name = "tblRoleDetail", uniqueConstraints = @UniqueConstraint(columnNames = {
		"RoleID", "Subject", "SubjectID" }))
public class RoleDetail extends SuperEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "RoleID", insertable = true, nullable = false)
	private Role role;

	@Column(name = "Subject", nullable = false)
	private String subject;

	@Column(name = "SubjectID", nullable = false)
	private Integer subjectId;

	public RoleDetail() {
	}

	public RoleDetail(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public RoleDetail(String subject, Integer subjectId, Role role) {
		this.subject = subject;
		this.subjectId = subjectId;
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

}
