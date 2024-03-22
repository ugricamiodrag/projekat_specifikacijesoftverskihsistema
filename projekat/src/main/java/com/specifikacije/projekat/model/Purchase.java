package com.specifikacije.projekat.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Purchase {
	
	private Long id;
	private User user;
	private RealEstate estate;
	private Date purchaseTime;
	
	public Long getId() {
		return id;
	}
	public Purchase(User user, RealEstate estate, Date purchaseTime) {
		super();
		this.user = user;
		this.estate = estate;
		this.purchaseTime = purchaseTime;
	}
	public Purchase(Long id, User user, RealEstate estate, Date purchaseTime) {
		super();
		this.id = id;
		this.user = user;
		this.estate = estate;
		this.purchaseTime = purchaseTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public RealEstate getEstate() {
		return estate;
	}
	public void setEstate(RealEstate estate) {
		this.estate = estate;
	}
	public Date getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	
	

}
