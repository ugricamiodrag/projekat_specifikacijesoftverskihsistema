package com.specifikacije.projekat.model;

import java.time.LocalDateTime;

public class ScheduledTour {
	private Long id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private User user;
	private RealEstate realEstate;
	private Boolean isApproved = null;
	
	public ScheduledTour() {
	}
	
	
	
	public ScheduledTour(Long id, LocalDateTime startTime, LocalDateTime endTime, User user, RealEstate realEstate,
			Boolean isApproved) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.user = user;
		this.realEstate = realEstate;
		this.isApproved = isApproved;
	}



	public ScheduledTour(LocalDateTime startTime, LocalDateTime endTime, User user, RealEstate realEstate,
			Boolean isApproved) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.user = user;
		this.realEstate = realEstate;
		this.isApproved = isApproved;
	}

	public ScheduledTour(LocalDateTime startTime, LocalDateTime endTime, User user, RealEstate realEstate) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.user = user;
		this.realEstate = realEstate;
	}

	public Long getId() {
		return id;
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







	public void setId(Long id) {
		this.id = id;
	}
	
	public Boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}



	public LocalDateTime getStartTime() {
		return startTime;
	}



	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}



	public LocalDateTime getEndTime() {
		return endTime;
	}



	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
}

