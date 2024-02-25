package com.specifikacije.projekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.specifikacije.projekat.dao.impl.AgencyDAOimpl;
import com.specifikacije.projekat.model.Agency;
import com.specifikacije.projekat.service.AgencyService;

public class AgencyServiceImpl implements AgencyService {

	@Autowired
	private AgencyDAOimpl agencyDAO;
	
	@Override
	public Agency findOne(Long id) {
		return agencyDAO.findOne(id);
	}

	@Override
	public List<Agency> findAll() {
		return agencyDAO.findAll();
	}

	@Override
	public Agency save(Agency agency) {
		agencyDAO.save(agency);
		return agency;
	}

	@Override
	public void delete(Long id) {
		agencyDAO.delete(id);
	}

	@Override
	public void update(Agency d) {
		agencyDAO.update(d);
	}

}
