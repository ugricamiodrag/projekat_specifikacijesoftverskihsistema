package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.AgencyOwner;

public interface AgencyOwnerService {
	AgencyOwner findOne(Long id);
	AgencyOwner findByUsername(String username);

	List<AgencyOwner> findAll();
	AgencyOwner save(AgencyOwner agencyOwner);
	void delete(Long id);
	void update(AgencyOwner d);
	public boolean usernameExists(String username);
	public boolean emailExists(String email);
}
