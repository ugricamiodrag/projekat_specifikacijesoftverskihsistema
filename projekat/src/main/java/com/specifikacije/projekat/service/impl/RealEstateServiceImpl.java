package com.specifikacije.projekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specifikacije.projekat.dao.impl.RealEstateDAOimpl;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.service.RealEstateService;

@Service
public class RealEstateServiceImpl implements RealEstateService{

	@Autowired
	private RealEstateDAOimpl realEstateDAO;
	
	
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

}
