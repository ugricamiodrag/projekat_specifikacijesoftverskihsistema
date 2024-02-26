package com.specifikacije.projekat.dao;

import java.util.List;

import com.specifikacije.projekat.model.Administrator;
import com.specifikacije.projekat.model.Agent;


public interface AdministratorDAO {
	
	public Administrator findOne(Long id);

	public Administrator findByUsername(String username);

	public List<Administrator> findAll();

	public void save(Administrator admin);

	public void update(Administrator admin);

	public void delete(Long id);
}
