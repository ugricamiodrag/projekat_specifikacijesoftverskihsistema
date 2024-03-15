package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.RealEstate;


public interface RealEstateService {

	RealEstate findOne(Long id);
	List<RealEstate> findAll();
	List<RealEstate> findAllHidden();
	RealEstate save(RealEstate realEstate);
	void delete(Long id);
	void update(RealEstate d);
	List<RealEstate> findAgenciesEstate(Agent agent);
	List<RealEstate> find(String location, Integer parseInteger, Integer parseInteger2, Double parseDouble,
			Double parseDouble2, String rent, String buy, String popularity, List<String> propertyTypes);
}
