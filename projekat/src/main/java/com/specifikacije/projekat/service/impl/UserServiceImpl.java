package com.specifikacije.projekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specifikacije.projekat.dao.impl.UserDAOimpl;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAOimpl userDAO;
	
	@Override
	public User findOne(Long id) {
		return userDAO.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public User save(User user) {
		userDAO.save(user);
		return user;
	}

	@Override
	public void delete(Long id) {
		userDAO.delete(id);
	}

	@Override
	public void update(User d) {
		userDAO.update(d);
	}

	@Override
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);

	}

	@Override
	public boolean usernameExists(String username) {
		return userDAO.usernameExists(username);
	}

	@Override
	public boolean emailExists(String email) {
		return userDAO.emailExists(email);
	}

}
