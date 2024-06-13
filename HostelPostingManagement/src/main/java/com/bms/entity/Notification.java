package com.bms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private boolean seen;

	private String createdDate;

	private String notificationName;

	// notification to user
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	// notification about post
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getNotificationName() {
		return notificationName;
	}

	public void setNotificationName(String notificationName) {
		this.notificationName = notificationName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Notification(boolean seen, String createdDate, String notificationName, User user, Post post) {
		super();
		this.seen = seen;
		this.createdDate = createdDate;
		this.notificationName = notificationName;
		this.user = user;
		this.post = post;
	}

	public Notification() {
		super();
	}
	
	

}
