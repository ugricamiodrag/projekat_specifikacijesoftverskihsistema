package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.Rating;

public interface RatingService {
	Rating findOne(Long id);
	List<Rating> findAll();
	Rating save(Rating rating);
	void delete(Long id);
	void update(Rating d);
}
