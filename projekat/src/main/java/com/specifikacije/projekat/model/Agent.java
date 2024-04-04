package com.specifikacije.projekat.model;

import java.util.List;

public class Agent extends Person {
	private List<RealEstate> realEstates;
	private Agency agency;

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public List<RealEstate> getRealEstates() {
		return realEstates;
	}

	public void setRealEstates(List<RealEstate> realEstates) {
		this.realEstates = realEstates;
	}

	public Agent(Long id, String name, String surname, String username, String password, String phoneNumber, String email, String address, List<RealEstate> realEstates, Agency agency, boolean isActive, boolean isBlocked) {
		super(id, name, surname, username, password, phoneNumber, email, address, isActive, isBlocked);
		this.realEstates = realEstates;
		this.agency = agency;
		// TODO Auto-generated constructor stub
	}

	public Agent(String name, String surname, String username, String password, String phoneNumber, String email, String address, List<RealEstate> realEstates, Agency agency, boolean isActive, boolean isBlocked) {
		super(name, surname, username, password, phoneNumber, email, address, isActive, isBlocked);
		this.realEstates = realEstates;
		this.agency = agency;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Agent [realEstates=" + realEstates + ", agency=" + agency + "]";
	}


}

