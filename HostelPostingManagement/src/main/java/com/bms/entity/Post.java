package com.bms.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition = "TEXT")
	private String title;
	
	@Column(name = "short_description",columnDefinition = "TEXT")
	private String shortDescription;
	
	@Column(name = "created_date")
	private String createdDate;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@Column(name = "last_update")
	private String lastUpdate;
	
	private double price;
	
	private double area;
	
	private boolean isHidden;
	
	private boolean isApprove;
	
	private boolean isActive;

	//category of post
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Image> images = new HashSet<Image>();
	
	@ManyToMany(mappedBy = "posts", fetch = FetchType.EAGER)
	private Set<User> users = new HashSet<User>();
	
	 //post reported
    @OneToMany(mappedBy = "pk.post", cascade = CascadeType.ALL)
    private Set<ReportPost> reportPosts = new HashSet<ReportPost>();
    
	//district of post
    @ManyToOne
	@JoinColumn(name = "district_id")
	private District district;
    
  //author of post
    @ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public Post(long id, String title, String shortDescription, String createdDate, String content, String lastUpdate,
			double price, double area, boolean isHidden, boolean isApprove, boolean isActive, Category category,
			Set<Image> images, Set<User> users, Set<ReportPost> reportPosts, District district, User user) {
		super();
		this.id = id;
		this.title = title;
		this.shortDescription = shortDescription;
		this.createdDate = createdDate;
		this.content = content;
		this.lastUpdate = lastUpdate;
		this.price = price;
		this.area = area;
		this.isHidden = isHidden;
		this.isApprove = isApprove;
		this.isActive = isActive;
		this.category = category;
		this.images = images;
		this.users = users;
		this.reportPosts = reportPosts;
		this.district = district;
		this.user = user;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isApprove() {
		return isApprove;
	}

	public void setApprove(boolean isApprove) {
		this.isApprove = isApprove;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}
	
//	public List<Image> getListImages(){
//		List<Image> listImage = new ArrayList<Image>();
//		listImage.addAll(this.images);
//		return listImage;
//	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<ReportPost> getReportPosts() {
		return reportPosts;
	}

	public void setReportPosts(Set<ReportPost> reportPosts) {
		this.reportPosts = reportPosts;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	public Post() {
		
	}

	
}
