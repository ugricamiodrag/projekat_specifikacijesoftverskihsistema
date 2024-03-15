package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.Agency;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.RealEstate;


public interface AgencyService {
	Agency findOne(Long id);
	List<Agency> findAll();
	Agency save(Agency agency);
	void delete(Long id);
	void update(Agency d);
	Agency findOwnersAgency(Long id);
}
