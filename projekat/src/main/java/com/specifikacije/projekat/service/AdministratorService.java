package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.Administrator;


public interface AdministratorService {

	Administrator findOne(Long id);
	List<Administrator> findAll();
	Administrator save(Administrator administrator);
	void delete(Long id);
	void update(Administrator d);
}
