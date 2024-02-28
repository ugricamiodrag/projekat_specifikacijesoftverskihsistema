package com.specifikacije.projekat.dao;

import java.util.List;

import com.specifikacije.projekat.model.AgencyOwner;

public interface AgencyOwnerDAO {
	
	public AgencyOwner findOne(Long id);
	
	public AgencyOwner findByUsername(String username);
	
	public List<AgencyOwner> findAll();

	public void save(AgencyOwner agencyOwner);

	public void update(AgencyOwner agencyOwner);

	public void delete(Long id);
	
	public boolean usernameExists(String username);
	public boolean emailExists(String email);
}
