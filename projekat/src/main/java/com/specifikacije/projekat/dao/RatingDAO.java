package com.specifikacije.projekat.dao;

import java.util.List;

import com.specifikacije.projekat.model.Rating;

public interface RatingDAO {
	public Rating findOne(Long id);

	public List<Rating> findAll();

	public void save(Rating rating);

	public void update(Rating rating);

	public void delete(Long id);
}
