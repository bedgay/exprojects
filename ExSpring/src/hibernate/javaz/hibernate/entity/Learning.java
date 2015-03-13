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

@Entity
@Table(name = "tblLearning", 
		uniqueConstraints = @UniqueConstraint(columnNames = { "CourseID", "ProfileID" }))
public class Learning extends SuperLong implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "Scores")
	private Float scores;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CourseID", nullable = false)
	private Course course;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ProfileID", nullable = false)
	private Profile profile;

	public Learning() {
	}

	public Learning(Float scores) {
		this.scores = scores;
	}

	public Learning(Float scores, Course course, Profile profile) {
		this.scores = scores;
		this.course = course;
		this.profile = profile;
	}

	public Float getScores() {
		return scores;
	}

	public void setScores(Float scores) {
		this.scores = scores;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
