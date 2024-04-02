package com.specifikacije.projekat.dao;

import java.util.List;

import com.specifikacije.projekat.model.Agency;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.RealEstate;


public interface RealEstateDAO {

	public RealEstate findOne(Long id);

	public List<RealEstate> findAll();

	public void save(RealEstate estate);

	public void update(RealEstate estate);

	public void delete(Long id);

	List<RealEstate> findAgenciesEstate(Agency agency);

	List<RealEstate> findAllHidden();

	public List<RealEstate> find(String location, Integer surfaceFrom, Integer surfaceTo, Double priceMin,
			Double priceMax, String rent, String buy, String popularity, List<String> propertyTypes);

	List<RealEstate> findAgentsEstate(Agent agent);

	List<RealEstate> findRemaining();
}
