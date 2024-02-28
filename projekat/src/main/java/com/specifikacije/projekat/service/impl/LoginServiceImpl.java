package com.specifikacije.projekat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specifikacije.projekat.model.Administrator;
import com.specifikacije.projekat.model.AgencyOwner;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.User;

import com.specifikacije.projekat.service.AdministratorService;
import com.specifikacije.projekat.service.AgencyOwnerService;
import com.specifikacije.projekat.service.AgentService;
import com.specifikacije.projekat.service.LoginService;
import com.specifikacije.projekat.service.UserService;

@Service
public class LoginServiceImpl implements LoginService {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private AdministratorService adminService;
	
	@Autowired
	private AgencyOwnerService ownerService;

	@Override
	public User findUser(String username) {

		return userService.findByUsername(username);
	}

	@Override
	public Agent findAgent(String username) {
		
		return agentService.findByUsername(username);

	}

	@Override
	public Administrator findAdmin(String username) {
		
		return adminService.findByUsername(username);

	}

	@Override
	public AgencyOwner findAgenctOwner(String username) {
		
		return ownerService.findByUsername(username);

	}
}
