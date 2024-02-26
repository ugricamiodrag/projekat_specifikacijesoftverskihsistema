package com.specifikacije.projekat.model;

import java.util.List;

public class User extends Person{
	private List<RealEstate> likedRealEstate;
	private List<Rating> allRatings;

	public User(Long id, String name, String surname, String username, String password, String phoneNumber, String email, String address, boolean isActive) {
		super(id, name, surname, username, password, phoneNumber, email, address, isActive);
		// TODO Auto-generated constructor stub
	}

	
	public User(String name, String surname, String username, String password, String phoneNumber, String email, String address, boolean isActive) {
		super( name, surname, username, password, phoneNumber, email, address, isActive);
		// TODO Auto-generated constructor stub
	}

	public List<Rating> getAllRatings() {
		return allRatings;
	}

	public void setAllRatings(List<Rating> allRatings) {
		this.allRatings = allRatings;
	}

	public User(String name, String surname, String username, String password, String phoneNumber, String email, String address, List<Rating> allRatings, boolean isActive) {
		super(name, surname, username, password, phoneNumber, email, address, isActive);
		this.allRatings = allRatings;
		// TODO Auto-generated constructor stub
	}
	public User(Long id, String name, String surname, String username, String password, String phoneNumber, String email, String address, List<RealEstate> likedRealEstate, List<Rating> allRatings, boolean isActive) {
		super(id, name, surname, username, password, phoneNumber, email, address, isActive);
		this.likedRealEstate = likedRealEstate;
		this.allRatings = allRatings;
		// TODO Auto-generated constructor stub
	}
	

	public User(String name, String surname, String username, String password, String phoneNumber, String email, String address, List<RealEstate> likedRealEstate, List<Rating> allRatings, boolean isActive) {
		super(name, surname, username, password, phoneNumber, email, address, isActive);
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
}
