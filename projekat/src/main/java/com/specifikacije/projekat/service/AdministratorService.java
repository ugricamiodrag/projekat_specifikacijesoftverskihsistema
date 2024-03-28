package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.Administrator;


public interface AdministratorService {
	
	Administrator findByUsername(String username);
	Administrator findOne(Long id);
	List<Administrator> findAll();
	Administrator save(Administrator administrator);
	void delete(Long id);
	void update(Administrator d);
	public boolean usernameExists(String username);
	public boolean emailExists(String email);
}
