package com.specifikacije.projekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.specifikacije.projekat.dao.impl.AdministratorDAOimpl;
import com.specifikacije.projekat.model.Administrator;
import com.specifikacije.projekat.service.AdministratorService;

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

}
