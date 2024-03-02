package com.specifikacije.projekat.model;

public class Administrator extends Person {
	public Administrator(Long id, String name, String surname, String username, String password, String phoneNumber, String email, String address, boolean isActive, boolean isBlocked) {
		super(id, name, surname, username, password, phoneNumber, email, address, isActive, isBlocked);
		// TODO Auto-generated constructor stub
	}

	
	public Administrator(String name, String surname, String username, String password, String phoneNumber, String email, String address, boolean isActive, boolean isBlocked) {
		super(name, surname, username, password, phoneNumber, email, address, isActive, isBlocked);
		// TODO Auto-generated constructor stub
	}


	
}
