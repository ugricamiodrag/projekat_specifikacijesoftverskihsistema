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

	public Agent(Long id, String name, String surname, String phoneNumber, String email, String address, List<RealEstate> realEstates, Agency agency) {
		super(id, name, surname, phoneNumber, email, address);
		this.realEstates = realEstates;
		this.agency = agency;
		// TODO Auto-generated constructor stub
	}

	public Agent(String name, String surname, String phoneNumber, String email, String address, List<RealEstate> realEstates, Agency agency) {
		super(name, surname, phoneNumber, email, address);
		this.realEstates = realEstates;
		this.agency = agency;
		// TODO Auto-generated constructor stub
	}

	public void UpdateRealEstate(RealEstate realEstate) {
		
	}
	
	public void addRealEstate(RealEstate realEstate) {
		
	}
	
	public void deleteRealEstate(RealEstate realEstate) {
		
	}
}

