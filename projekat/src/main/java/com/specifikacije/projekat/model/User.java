package com.specifikacije.projekat.model;

import java.util.List;

public class User extends Person{
	private List<RealEstate> likedRealEstate;
	private List<RealEstate> dislikedRealEstate;

	private List<Rating> allRatings;

	public User(Long id, String name, String surname, String username, String password, String phoneNumber, String email, String address, boolean isActive, boolean isBLocked) {
		super(id, name, surname, username, password, phoneNumber, email, address, isActive, isBLocked);
		// TODO Auto-generated constructor stub
	}

	
	public User(String name, String surname, String username, String password, String phoneNumber, String email, String address, boolean isActive, boolean isBlocked) {
		super( name, surname, username, password, phoneNumber, email, address, isActive, isBlocked);
		// TODO Auto-generated constructor stub
	}

	public List<Rating> getAllRatings() {
		return allRatings;
	}

	public void setAllRatings(List<Rating> allRatings) {
		this.allRatings = allRatings;
	}

	public User(String name, String surname, String username, String password, String phoneNumber, String email, String address, List<Rating> allRatings, boolean isActive, boolean isBlocked) {
		super(name, surname, username, password, phoneNumber, email, address, isActive, isBlocked);
		this.allRatings = allRatings;
		// TODO Auto-generated constructor stub
	}
	public User(Long id, String name, String surname, String username, String password, String phoneNumber, String email, String address, List<RealEstate> likedRealEstate, List<Rating> allRatings, boolean isActive, boolean isBlocked) {
		super(id, name, surname, username, password, phoneNumber, email, address, isActive, isBlocked);
		this.likedRealEstate = likedRealEstate;
		this.allRatings = allRatings;
		// TODO Auto-generated constructor stub
	}
	

	public User(String name, String surname, String username, String password, String phoneNumber, String email, String address, List<RealEstate> likedRealEstate, List<Rating> allRatings, boolean isActive, boolean isBlocked) {
		super(name, surname, username, password, phoneNumber, email, address, isActive, isBlocked);
		this.likedRealEstate = likedRealEstate;
		this.allRatings = allRatings;
		// TODO Auto-generated constructor stub
	}

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


	public List<RealEstate> getDislikedRealEstate() {
		return dislikedRealEstate;
	}


	public void setDislikedRealEstate(List<RealEstate> dislikedRealEstate) {
		this.dislikedRealEstate = dislikedRealEstate;
	}


}
