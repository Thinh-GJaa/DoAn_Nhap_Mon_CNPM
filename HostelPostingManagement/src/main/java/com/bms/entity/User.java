package com.bms.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "full_name")
	private String fullName;

	private String phone;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username")
	private Account account;

	private String address;

	@Column(name = "update_date")
	private String updateDate;

	@Column(name = "created_date")
	private String createdDate;

	// list post user saved
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "saved_post", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
	private List<Post> posts = new ArrayList<Post>();

	public void addLikePost(Post post) {
		posts.add(post);
		post.getUsers().add(this);
	}

	public void removeLikeRole(Post post) {
		posts.remove(post);
		post.getUsers().remove(this);
	}

	// post report
	@OneToMany(mappedBy = "pk.user", cascade = CascadeType.ALL)
	private Set<ReportPost> reportPosts = new HashSet<ReportPost>();

	// list post create
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Post> postsCreate = new HashSet<Post>();

	// list notification
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Notification> notifications = new HashSet<Notification>();

	public Set<Post> getPostsCreate() {
		return postsCreate;
	}
	
	private String email;

	private boolean gender;

	private int warningCount;

	public User(long id, String fullName, String phone, Account account, String address, String updateDate,
			String createdDate, List<Post> posts, Set<ReportPost> reportPosts, Set<Post> postsCreate,
			Set<Notification> notifications, String email, boolean gender, int warningCount) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.account = account;
		this.address = address;
		this.updateDate = updateDate;
		this.createdDate = createdDate;
		this.posts = posts;
		this.reportPosts = reportPosts;
		this.postsCreate = postsCreate;
		this.notifications = notifications;
		this.email = email;
		this.gender = gender;
		this.warningCount = warningCount;
	}

	public Set<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

	public int getWarningCount() {
		return warningCount;
	}

	public void setWarningCount(int warningCount) {
		this.warningCount = warningCount;
	}

	public void setPostsCreate(Set<Post> postsCreate) {
		this.postsCreate = postsCreate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Set<ReportPost> getReportPosts() {
		return reportPosts;
	}

	public void setReportPosts(Set<ReportPost> reportPosts) {
		this.reportPosts = reportPosts;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public User() {

	}

	public User(long id, String fullName, String phone, Account account, String address, String updateDate,
			String createdDate, List<Post> posts, Set<ReportPost> reportPosts, String email, boolean gender) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.account = account;
		this.address = address;
		this.updateDate = updateDate;
		this.createdDate = createdDate;
		this.posts = posts;
		this.reportPosts = reportPosts;
		this.email = email;
		this.gender = gender;
	}

}
