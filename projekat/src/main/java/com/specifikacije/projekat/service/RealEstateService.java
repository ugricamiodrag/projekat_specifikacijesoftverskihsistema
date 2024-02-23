package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.RealEstate;


public interface RealEstateService {

	RealEstate findOne(Long id);
	List<RealEstate> findAll();
	RealEstate save(RealEstate realEstate);
	void delete(Long id);
	void update(RealEstate d);
	
}
