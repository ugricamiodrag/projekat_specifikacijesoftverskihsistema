package com.specifikacije.projekat.service;

import com.specifikacije.projekat.model.Administrator;
import com.specifikacije.projekat.model.AgencyOwner;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.User;

public interface LoginService {

	public User findUser(String username);
	public Agent findAgent(String username);
	public Administrator findAdmin(String username);
	public AgencyOwner findAgenctOwner(String username);
}
