package com.specifikacije.projekat.dao;

import java.util.List;

import com.specifikacije.projekat.model.Agency;


public interface AgencyDAO {
	public Agency findOne(Long id);

	public List<Agency> findAll();

	public void save(Agency agency);

	public void update(Agency agency);

	public void delete(Long id);
}
