package com.specifikacije.projekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specifikacije.projekat.dao.impl.AdministratorDAOimpl;
import com.specifikacije.projekat.model.Administrator;
import com.specifikacije.projekat.service.AdministratorService;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	private AdministratorDAOimpl AdministratorDAO;
	
	@Override
	public Administrator findOne(Long id) {
		return AdministratorDAO.findOne(id);
	}

	@Override
	public List<Administrator> findAll() {
		return AdministratorDAO.findAll();
	}

	@Override
	public Administrator save(Administrator administrator) {
		AdministratorDAO.save(administrator);
		return administrator;
	}

	@Override
	public void delete(Long id) {
		AdministratorDAO.delete(id);
	}

	@Override
	public void update(Administrator d) {
		AdministratorDAO.update(d);		
	}

	@Override
	public Administrator findByUsername(String username) {
		return AdministratorDAO.findByUsername(username);
	}

	@Override
	public boolean usernameExists(String username) {
		return AdministratorDAO.usernameExists(username);
	}

	@Override
	public boolean emailExists(String email) {
		return AdministratorDAO.emailExists(email);
	}

}
