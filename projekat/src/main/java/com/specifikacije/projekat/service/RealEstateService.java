package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.Agency;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.RealEstate;


public interface RealEstateService {

	RealEstate findOne(Long id);
	List<RealEstate> findAll();
	List<RealEstate> findAllHidden();
	RealEstate save(RealEstate realEstate);
	void delete(Long id);
	void update(RealEstate d);

	List<RealEstate> find(String location, Integer parseInteger, Integer parseInteger2, Double parseDouble,
			Double parseDouble2, String rent, String buy, String popularity, List<String> propertyTypes, Agent agent);

    List<RealEstate> findAgenciesEstate(Agency agency);

	List<RealEstate> findAgentsEstate(Agent agent);
	List<RealEstate> findRemaining();
}
