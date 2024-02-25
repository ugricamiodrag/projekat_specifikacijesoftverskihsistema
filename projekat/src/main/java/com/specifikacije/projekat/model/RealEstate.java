package com.specifikacije.projekat.model;

import java.util.List;

public class RealEstate {
	private Long id;
	private RealEstateType estateType;
	private Agent agent;
	private String location;
	private String picture;
	private Double price;
	private RentOrBuy rentOrBuy;
	private Integer numberOfVisitRequests;
	private Double grade;
	private Double viewNumber;
	private Boolean isActive;
	private Integer surface; // dodato zbog pretrage po povrsini

	public RealEstate() {
	}
	





	public RealEstate(RealEstateType estateType, Agent agent, String location, String picture, Double price,
			RentOrBuy rentOrBuy, Integer numberOfVisitRequests, Double grade, Double viewNumber, Boolean isActive) {
		super();
		this.estateType = estateType;
		this.agent = agent;
		this.location = location;
		this.picture = picture;
		this.price = price;
		this.rentOrBuy = rentOrBuy;
		this.numberOfVisitRequests = numberOfVisitRequests;
		this.grade = grade;
		this.viewNumber = viewNumber;
		this.isActive = isActive;
	}






	public RealEstate(Long id, RealEstateType estateType, Agent agent, String location, String picture, Double price,
			RentOrBuy rentOrBuy, Integer numberOfVisitRequests, Double grade, Double viewNumber, Boolean isActive) {
		super();
		this.id = id;
		this.estateType = estateType;
		this.agent = agent;
		this.location = location;
		this.picture = picture;
		this.price = price;
		this.rentOrBuy = rentOrBuy;
		this.numberOfVisitRequests = numberOfVisitRequests;
		this.grade = grade;
		this.viewNumber = viewNumber;
		this.isActive = isActive;
	}
	
	public RealEstate(Long id, RealEstateType estateType, Agent agent, String location, String picture, Double price,
			RentOrBuy rentOrBuy, Integer numberOfVisitRequests, Double grade, Double viewNumber, Boolean isActive, Integer surface) {
		super();
		this.id = id;
		this.estateType = estateType;
		this.agent = agent;
		this.location = location;
		this.picture = picture;
		this.price = price;
		this.rentOrBuy = rentOrBuy;
		this.numberOfVisitRequests = numberOfVisitRequests;
		this.grade = grade;
		this.viewNumber = viewNumber;
		this.isActive = isActive;
		this.surface = surface;
	}






	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RealEstateType getEstateType() {
		return estateType;
	}
	public void setType(RealEstateType type) {
		this.estateType = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public RentOrBuy getRentOrBuy() {
		return rentOrBuy;
	}
	public void setRentOrBuy(RentOrBuy rentOrBuy) {
		this.rentOrBuy = rentOrBuy;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "RealEstate [id=" + id + ", type=" + estateType + ", location=" + location + ", picture=" + picture
				+ ", price=" + price + ", rentOrBuy=" + rentOrBuy + ", isActive=" + isActive + "]";
	}



	public Agent getAgent() {
		return agent;
	}



	public void setAgent(Agent agent) {
		this.agent = agent;
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






	public void setEstateType(RealEstateType estateType) {
		this.estateType = estateType;
	}






	public Integer getSurface() {
		return surface;
	}






	public void setSurface(Integer surface) {
		this.surface = surface;
	}







	
}
