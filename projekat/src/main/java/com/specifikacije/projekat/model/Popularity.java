package com.specifikacije.projekat.model;

public class Popularity {
	private Integer numberOfVisitRequests;
	private Double grade;
	private Double viewNumber;
	
	public Popularity() {
	}
	
	public Popularity(Integer numberOfVisitRequests, Double grade, Double viewNumber) {
		super();
		this.numberOfVisitRequests = numberOfVisitRequests;
		this.grade = grade;
		this.viewNumber = viewNumber;
	}
	
	public Integer getNumberOfVisitRequests() {
		return numberOfVisitRequests;
	}
	public void setNumberOfVisitRequests(Integer numberOfVisitRequests) {
		this.numberOfVisitRequests = numberOfVisitRequests;
	}
	public Double getGrade() {
		return grade;
	}
	public void setGrade(Double grade) {
		this.grade = grade;
	}
	public Double getViewNumber() {
		return viewNumber;
	}
	public void setViewNumber(Double viewNumber) {
		this.viewNumber = viewNumber;
	}

	@Override
	public String toString() {
		return "Popularity [numberOfVisitRequests=" + numberOfVisitRequests + ", grade=" + grade + ", viewNumber="
				+ viewNumber + "]";
	}
}
