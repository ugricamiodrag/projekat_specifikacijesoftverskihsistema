package com.specifikacije.projekat.model;

import java.util.List;

public class User extends Person{
	private List<RealEstate> likedRealEstate;

	public List<RealEstate> getLikedRealEstate() {
		return likedRealEstate;
	}

	public void setLikedRealEstate(List<RealEstate> likedRealEstate) {
		this.likedRealEstate = likedRealEstate;
	}
	
	public RealEstate realEstateSearch() {
		return null;
	}
	
	public void Registration() {
	}
}
