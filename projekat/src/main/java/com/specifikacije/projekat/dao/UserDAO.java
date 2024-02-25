package com.specifikacije.projekat.dao;

import java.util.List;

import com.specifikacije.projekat.model.User;


public interface UserDAO {
	
	public User findOne(Long id);

	public List<User> findAll();

	public void save(User user);

	public void update(User user);

	public void delete(Long id);
}
