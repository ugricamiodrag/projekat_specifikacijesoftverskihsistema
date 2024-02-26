package com.specifikacije.projekat.model;

public class Person {
	private Long id;
	private String name;
	private String surname;
	private String username;
	private String password;
	private String phoneNumber;
	private String email;
	private String address;
	private boolean isActive;
	
	public Person() {
	}
	

	
	public Person(Long id, String name, String surname, String username, String password, String phoneNumber,
			String email, String address, boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.isActive = isActive;
	}



	public Person(String name, String surname, String username, String password, String phoneNumber, String email,
			String address, boolean isActive) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.isActive = isActive;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", surname=" + surname + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", address=" + address + "]";
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
}
