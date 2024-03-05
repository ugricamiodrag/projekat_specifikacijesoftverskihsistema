package com.specifikacije.projekat.service.impl;

import java.util.List;

import com.specifikacije.projekat.dao.RealEstateDAO;
import com.specifikacije.projekat.model.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specifikacije.projekat.dao.impl.RealEstateDAOimpl;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.service.RealEstateService;

@Service
public class RealEstateServiceImpl implements RealEstateService{

	@Autowired
	private RealEstateDAO realEstateDAO;
	
	
	@Override
	public RealEstate findOne(Long id) {
		return realEstateDAO.findOne(id);
	}
	

	@Override
	public List<RealEstate> findAll() {
		return realEstateDAO.findAll();

	}

	@Override
	public RealEstate save(RealEstate realEstate) {
		realEstateDAO.save(realEstate);
		return null;
	}

	@Override
	public void delete(Long id) {
		realEstateDAO.delete(id);
	}

	@Override
	public void update(RealEstate d) {
		realEstateDAO.update(d);
		
	}


	@Override
	public List<RealEstate> find(String location, Integer surfaceFrom, Integer surfaceTo, Double priceMin,
			Double priceMax, String rent, String buy, List<String> propertyTypes) {
		return realEstateDAO.find(location, surfaceFrom, surfaceTo, priceMin, priceMax, rent, buy, propertyTypes);

	}

	@Override
	public List<RealEstate> findAgenciesEstate(Agent agent) {
		return realEstateDAO.findAgenciesEstate(agent);
	}

}
