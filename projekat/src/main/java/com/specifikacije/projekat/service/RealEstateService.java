package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.RealEstate;


public interface RealEstateService {

	RealEstate findOne(Long id);
	List<RealEstate> findAll();
	RealEstate save(RealEstate realEstate);
	void delete(Long id);
	void update(RealEstate d);
	List<RealEstate> find(String location, Integer surfaceFrom, Integer surfaceTo, Double priceMin, Double priceMax,
			String rent, String buy, List<String> propertyTypes);
	List<RealEstate> findAgenciesEstate(Agent agent);
}
