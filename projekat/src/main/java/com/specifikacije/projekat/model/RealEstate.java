package com.specifikacije.projekat.model;

import java.util.List;

public class RealEstate {
	private Long id;
	private RealEstateType type;
	private Agent agent;
	private String location;
	private String picture;
	private Double price;
	private RentOrBuy rentOrBuy;
	private Popularity popularity;
	private Boolean isActive;
	
	public RealEstate() {
	}
	


	public RealEstate(RealEstateType type, Agent agent, String location, String picture, Double price,
			RentOrBuy rentOrBuy, Popularity popularity, Boolean isActive) {
		super();
		this.type = type;
		this.agent = agent;
		this.location = location;
		this.picture = picture;
		this.price = price;
		this.rentOrBuy = rentOrBuy;
		this.popularity = popularity;
		this.isActive = isActive;
	}



	public RealEstate(Long id, RealEstateType type, Agent agent, String location, String picture, Double price,
			RentOrBuy rentOrBuy, Popularity popularity, Boolean isActive) {
		super();
		this.id = id;
		this.type = type;
		this.agent = agent;
		this.location = location;
		this.picture = picture;
		this.price = price;
		this.rentOrBuy = rentOrBuy;
		this.popularity = popularity;
		this.isActive = isActive;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RealEstateType getType() {
		return type;
	}
	public void setType(RealEstateType type) {
		this.type = type;
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
		return "RealEstate [id=" + id + ", type=" + type + ", location=" + location + ", picture=" + picture
				+ ", price=" + price + ", rentOrBuy=" + rentOrBuy + ", isActive=" + isActive + "]";
	}



	public Agent getAgent() {
		return agent;
	}



	public void setAgent(Agent agent) {
		this.agent = agent;
	}



	public Popularity getPopularity() {
		return popularity;
	}



	public void setPopularity(Popularity popularity) {
		this.popularity = popularity;
	}
}
