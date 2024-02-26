package com.specifikacije.projekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specifikacije.projekat.dao.impl.AgencyOwnerDAOimpl;
import com.specifikacije.projekat.model.AgencyOwner;
import com.specifikacije.projekat.service.AgencyOwnerService;

@Service
public class AgencyOwnerServiceImpl implements AgencyOwnerService {

	@Autowired
	private AgencyOwnerDAOimpl agencyOwnerDAO;
	
	@Override
	public AgencyOwner findOne(Long id) {
		return agencyOwnerDAO.findOne(id);
	}

	@Override
	public List<AgencyOwner> findAll() {
		return agencyOwnerDAO.findAll();
	}

	@Override
	public AgencyOwner save(AgencyOwner agencyOwner) {
		agencyOwnerDAO.save(agencyOwner);
		return agencyOwner;
	}

	@Override
	public void delete(Long id) {
		agencyOwnerDAO.delete(id);
	}

	@Override
	public void update(AgencyOwner d) {
		agencyOwnerDAO.update(d);		
	}

	@Override
	public AgencyOwner findByUsername(String username) {
		return agencyOwnerDAO.findByUsername(username);

	}

}
