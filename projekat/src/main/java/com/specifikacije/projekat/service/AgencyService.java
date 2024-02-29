package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.Agency;


public interface AgencyService {
	Agency findOne(Long id);
	List<Agency> findAll();
	Agency save(Agency agency);
	void delete(Long id);
	void update(Agency d);
	Agency findOwnersAgency(Long id);
}
