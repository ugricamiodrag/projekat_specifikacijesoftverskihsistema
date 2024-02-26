package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.User;

public interface UserService {
	
	User findOne(Long id);
	User findByUsername(String username);
	List<User> findAll();
	User save(User user);
	void delete(Long id);
	void update(User d);
}
