package com.specifikacije.projekat.service.impl;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specifikacije.projekat.dao.impl.AgentDAOimpl;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.service.AgentService;

import java.util.ArrayList;

import java.util.Set;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specifikacije.projekat.dao.impl.AgentDAOimpl;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.RealEstate;
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
	public List<Agent> findAll(List<RealEstate> realEstates) {
		 	
		 List<Agent> agents = new ArrayList<>();
		 List<Agent> agentSet = findAll();
		 
		 for (Agent agent : agentSet) {
		        if (!agents.contains(agent)) {
		            agents.add(agent);
		            System.out.println(agent.getId());
		        }
		    }
		return agents;
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

	@Override
	public Agent findByUsername(String username) {
		return agentDAO.findByUsername(username);

	}

	@Override
	public boolean usernameExists(String username) {
		return agentDAO.usernameExists(username);
	}

	@Override
	public boolean emailExists(String email) {
		return agentDAO.emailExists(email);
	}

	@Override
	public List<Agent> findAgents(Long agencyid) {
		return agentDAO.findAgents(agencyid);
		
	}

	


}
