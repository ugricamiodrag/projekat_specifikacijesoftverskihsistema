package com.specifikacije.projekat.model;

import java.time.LocalDateTime;

public class ScheduledTour {
	private Long id;
	private LocalDateTime dateTime;
	private User user;
	private RealEstate realEstate;
	private Boolean isApproved = false;
	
	public ScheduledTour() {
	}
	
	
	
	public Long getId() {
		return id;
	}
	

	public ScheduledTour(LocalDateTime dateTime, User user, RealEstate realEstate, Boolean isApproved) {
		super();
		this.dateTime = dateTime;
		this.user = user;
		this.realEstate = realEstate;
		this.isApproved = isApproved;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public RealEstate getRealEstate() {
		return realEstate;
	}



	public void setRealEstate(RealEstate realEstate) {
		this.realEstate = realEstate;
	}



	public ScheduledTour(Long id, LocalDateTime dateTime, User user, RealEstate realEstate, Boolean isApproved) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.user = user;
		this.realEstate = realEstate;
		this.isApproved = isApproved;
	}



	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public Boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	@Override
	public String toString() {
		return "ScheduledTour [id=" + id + ", dateTime=" + dateTime + ", isApproved=" + isApproved + "]";
	}
}
