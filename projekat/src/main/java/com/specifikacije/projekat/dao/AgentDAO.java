package com.specifikacije.projekat.dao;

import java.util.List;

import com.specifikacije.projekat.model.Agent;


public interface AgentDAO {
	public Agent findOne(Long id);

	public Agent findByUsername(String username);

	public List<Agent> findAll();

	public void save(Agent agent);

	public void update(Agent agent);

	public void delete(Long id);
	
	public boolean usernameExists(String username);
	public boolean emailExists(String email);
}
