package com.specifikacije.projekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specifikacije.projekat.dao.impl.RatingDAOimpl;
import com.specifikacije.projekat.model.Rating;
import com.specifikacije.projekat.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingDAOimpl ratingDAO;
	
	@Override
	public Rating findOne(Long id) {
		return ratingDAO.findOne(id);
	}

	@Override
	public List<Rating> findAll() {
		return ratingDAO.findAll();
	}

	@Override
	public Rating save(Rating rating) {
		ratingDAO.save(rating);
		return rating;
	}

	@Override
	public void delete(Long id) {
		ratingDAO.delete(id);
	}

	@Override
	public void update(Rating d) {
		ratingDAO.update(d);
	}

}
