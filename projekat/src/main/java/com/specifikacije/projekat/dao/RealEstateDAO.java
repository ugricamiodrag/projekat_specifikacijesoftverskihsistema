package com.specifikacije.projekat.dao;

import java.util.List;

import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.RealEstate;


public interface RealEstateDAO {

	public RealEstate findOne(Long id);

	public List<RealEstate> findAll();

	public void save(RealEstate estate);

	public void update(RealEstate estate);

	public void delete(Long id);

	List<RealEstate> find(String location, Integer surfaceFrom, Integer surfaceTo, Double priceMin,
						  Double priceMax, String rent, String buy, List<String> propertyTypes);

	List<RealEstate> findAgenciesEstate(Agent agent);
}
