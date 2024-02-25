package com.specifikacije.projekat.model;

import java.util.List;

public class User extends Person{
	private List<RealEstate> likedRealEstate;
	private List<Rating> allRatings;

	public User(Long id, String name, String surname, String phoneNumber, String email, String address) {
		super(id, name, surname, phoneNumber, email, address);
		// TODO Auto-generated constructor stub
	}
	
	public User(Long id, String name, String surname, String phoneNumber, String email, String address, boolean isActive) {
		super(id, name, surname, phoneNumber, email, address, isActive);
		// TODO Auto-generated constructor stub
	}
	
	public User(String name, String surname, String phoneNumber, String email, String address, boolean isActive) {
		super( name, surname, phoneNumber, email, address, isActive);
		// TODO Auto-generated constructor stub
	}

	public List<Rating> getAllRatings() {
		return allRatings;
	}

	public void setAllRatings(List<Rating> allRatings) {
		this.allRatings = allRatings;
	}

	public User(String name, String surname, String phoneNumber, String email, String address, List<Rating> allRatings) {
		super(name, surname, phoneNumber, email, address);
		this.allRatings = allRatings;
		// TODO Auto-generated constructor stub
	}
	public User(Long id, String name, String surname, String phoneNumber, String email, String address, List<RealEstate> likedRealEstate, List<Rating> allRatings) {
		super(id, name, surname, phoneNumber, email, address);
		this.likedRealEstate = likedRealEstate;
		this.allRatings = allRatings;
		// TODO Auto-generated constructor stub
	}
	
	public User(Long id, String name, String surname, String phoneNumber, String email, String address, boolean isActive, List<RealEstate> likedRealEstate, List<Rating> allRatings) {
		super(id, name, surname, phoneNumber, email, address, isActive);
		this.likedRealEstate = likedRealEstate;
		this.allRatings = allRatings;
		// TODO Auto-generated constructor stub
	}

	public User(String name, String surname, String phoneNumber, String email, String address, List<RealEstate> likedRealEstate, List<Rating> allRatings) {
		super(name, surname, phoneNumber, email, address);
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
