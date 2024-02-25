package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.Agent;

public interface AgentService {
	Agent findOne(Long id);
	List<Agent> findAll();
	Agent save(Agent agent);
	void delete(Long id);
	void update(Agent d);
}
