package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.ScheduledTour;

public interface ScheduledTourService {
	ScheduledTour findOne(Long id);
	List<ScheduledTour> findAll();
	ScheduledTour save(ScheduledTour scheduledTour);
	void delete(Long id);
	void update(ScheduledTour d);
	ScheduledTour findByUserAndEstate(Long id, Long id2);
}
