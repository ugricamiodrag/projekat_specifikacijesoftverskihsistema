package com.specifikacije.projekat.model;

import java.time.LocalDateTime;

public class ScheduledTour {
	private Long id;
	private LocalDateTime dateTime;
	private Boolean isApproved = false;
	
	public ScheduledTour() {
	}
	
	public ScheduledTour(Long id, LocalDateTime dateTime, Boolean isApproved) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.isApproved = isApproved;
	}
	
	public Long getId() {
		return id;
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
