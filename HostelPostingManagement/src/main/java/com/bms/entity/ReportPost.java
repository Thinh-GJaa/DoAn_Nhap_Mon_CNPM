package com.bms.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "report_post")
@AssociationOverrides({
@AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "user_id")),
@AssociationOverride(name = "pk.post", joinColumns = @JoinColumn(name = "post_id"))
})
public class ReportPost {
	
	
	private String id;

	@EmbeddedId
	private ReportPostId pk = new ReportPostId();
	
	@Column(length = 400)
	private String content;
	
	private boolean isSolve;
	
	@Column(name = "created_date")
	private String createdDate;
	
	public ReportPost() {
	
	}

	public ReportPostId getPk() {
		return pk;
	}

	public void setPk(ReportPostId pk) {
		this.pk = pk;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isSolve() {
		return isSolve;
	}

	public void setSolve(boolean isSolve) {
		this.isSolve = isSolve;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ReportPost(String id, ReportPostId pk, String content, boolean isSolve, String createdDate) {
		super();
		this.id = id;
		this.pk = pk;
		this.content = content;
		this.isSolve = isSolve;
		this.createdDate = createdDate;
	}

	
	
}
