package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.User;

public interface AgentService {
	Agent findOne(Long id);
	Agent findByUsername(String username);

	List<Agent> findAll();
	Agent save(Agent agent);
	void delete(Long id);
	void update(Agent d);
	public boolean usernameExists(String username);
	public boolean emailExists(String email);
	List<Agent> findAgents(Long id);
}
