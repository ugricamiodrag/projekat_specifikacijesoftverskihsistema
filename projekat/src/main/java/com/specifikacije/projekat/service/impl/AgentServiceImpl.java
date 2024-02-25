package com.specifikacije.projekat.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specifikacije.projekat.dao.impl.AgentDAOimpl;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.service.AgentService;

@Service
public class AgentServiceImpl implements AgentService{

	@Autowired
	private AgentDAOimpl agentDAO;
	
	@Override
	public Agent findOne(Long id) {
		return agentDAO.findOne(id);
	}

	@Override
	public List<Agent> findAll() {
		return agentDAO.findAll();
	}

	@Override
	public Agent save(Agent agent) {
		agentDAO.save(agent);
		return agent;
	}

	@Override
	public void delete(Long id) {
		agentDAO.delete(id);
	}

	@Override
	public void update(Agent d) {
		agentDAO.update(d);		
	}

}
