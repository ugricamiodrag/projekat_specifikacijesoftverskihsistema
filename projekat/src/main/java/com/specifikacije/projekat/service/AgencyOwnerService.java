package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.AgencyOwner;
import com.specifikacije.projekat.model.User;

public interface AgencyOwnerService {
	AgencyOwner findOne(Long id);
	AgencyOwner findByUsername(String username);

	List<AgencyOwner> findAll();
	AgencyOwner save(AgencyOwner agencyOwner);
	void delete(Long id);
	void update(AgencyOwner d);
}
