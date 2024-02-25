package com.specifikacije.projekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.specifikacije.projekat.dao.impl.UserDAOimpl;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.service.UserService;



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

}
