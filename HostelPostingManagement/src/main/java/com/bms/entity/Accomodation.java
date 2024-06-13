package com.bms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Accomodation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 250)
	private String address;

	private boolean airCondittion;

	private boolean cableTv;

	private boolean heater;

	private boolean internet;

	private boolean parking;

	private boolean status;

	private double electricPrice;

	private double waterPrice;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_id")
	private Post post;

	public Accomodation(long id, String address, boolean airCondittion, boolean cableTv, boolean heater,
			boolean internet, boolean parking, boolean status, double electricPrice, double waterPrice, Post post) {
		super();
		this.id = id;
		this.address = address;
		this.airCondittion = airCondittion;
		this.cableTv = cableTv;
		this.heater = heater;
		this.internet = internet;
		this.parking = parking;
		this.status = status;
		this.electricPrice = electricPrice;
		this.waterPrice = waterPrice;
		this.post = post;
	}

	public long getId() {
		return id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAirCondittion() {
		return airCondittion;
	}

	public void setAirCondittion(boolean airCondittion) {
		this.airCondittion = airCondittion;
	}

	public boolean isCableTv() {
		return cableTv;
	}

	public void setCableTv(boolean cableTv) {
		this.cableTv = cableTv;
	}

	public boolean isHeater() {
		return heater;
	}

	public void setHeater(boolean heater) {
		this.heater = heater;
	}

	public boolean isInternet() {
		return internet;
	}

	public void setInternet(boolean internet) {
		this.internet = internet;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getElectricPrice() {
		return electricPrice;
	}

	public void setElectricPrice(double electricPrice) {
		this.electricPrice = electricPrice;
	}

	public double getWaterPrice() {
		return waterPrice;
	}

	public void setWaterPrice(double waterPrice) {
		this.waterPrice = waterPrice;
	}

	public Accomodation() {

	}

}
