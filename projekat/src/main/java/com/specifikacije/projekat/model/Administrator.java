package com.specifikacije.projekat.model;

public class Administrator extends Person {
	public Administrator(Long id, String name, String surname, String phoneNumber, String email, String address) {
		super(id, name, surname, phoneNumber, email, address);
		// TODO Auto-generated constructor stub
	}

	public void addAgencyOwner(AgencyOwner agencyOwner) {
		
	}
	
	public Administrator(String name, String surname, String phoneNumber, String email, String address) {
		super(name, surname, phoneNumber, email, address);
		// TODO Auto-generated constructor stub
	}

	public void deactivateUser(User user) {
		
	}

	public void hideRealEstate(RealEstate realEstate) {
		
	}
	
}
