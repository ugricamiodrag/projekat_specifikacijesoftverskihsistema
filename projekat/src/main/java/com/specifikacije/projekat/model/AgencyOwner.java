package com.specifikacije.projekat.model;

public class AgencyOwner extends Person {
	public void addAgent(Agent agent) {
		
	}
	
	public AgencyOwner(Long id, String name, String surname, String phoneNumber, String email, String address) {
		super(id, name, surname, phoneNumber, email, address);
		// TODO Auto-generated constructor stub
	}

	public AgencyOwner(String name, String surname, String phoneNumber, String email, String address) {
		super(name, surname, phoneNumber, email, address);
		// TODO Auto-generated constructor stub
	}

	public void deleteExisting(Agent agent) {
			
	}
	
	public RealEstate searchPopularEstate() {
		return null;
	}
	
	public Agent searchPopularAgent() {
		return null;
	}
}
