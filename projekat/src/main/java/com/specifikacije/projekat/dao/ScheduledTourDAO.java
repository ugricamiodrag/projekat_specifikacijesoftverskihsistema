package com.specifikacije.projekat.dao;

import java.util.List;

import com.specifikacije.projekat.model.ScheduledTour;

public interface ScheduledTourDAO {
	public ScheduledTour findOne(Long id);

	public List<ScheduledTour> findAll();

	public void save(ScheduledTour scheduledTour);

	public void update(ScheduledTour scheduledTour);

	public void delete(Long id);
}
