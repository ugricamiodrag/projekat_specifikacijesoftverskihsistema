package com.specifikacije.projekat.model;

public class AgencyOwner extends Person {
	public AgencyOwner(Long id, String name, String surname, String username, String password, String phoneNumber, String email, String address, Boolean isActive) {
		super(id, name, surname, username, password, phoneNumber, email, address, isActive);
		// TODO Auto-generated constructor stub
	}

	public AgencyOwner(String name, String surname, String username, String password, String phoneNumber, String email, String address, Boolean isActive) {
		super(name, surname, username, password, phoneNumber, email, address, isActive);
		// TODO Auto-generated constructor stub
	}


}
