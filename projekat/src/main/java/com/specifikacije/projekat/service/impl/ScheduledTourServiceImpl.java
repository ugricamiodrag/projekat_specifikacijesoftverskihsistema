package com.specifikacije.projekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.specifikacije.projekat.dao.impl.ScheduledTourDAOimpl;
import com.specifikacije.projekat.model.ScheduledTour;
import com.specifikacije.projekat.service.ScheduledTourService;

public class ScheduledTourServiceImpl implements ScheduledTourService {

	@Autowired
	private ScheduledTourDAOimpl scheduledTourDAO;
	
	@Override
	public ScheduledTour findOne(Long id) {
		return scheduledTourDAO.findOne(id);
	}

	@Override
	public List<ScheduledTour> findAll() {
		return scheduledTourDAO.findAll();
	}

	@Override
	public ScheduledTour save(ScheduledTour scheduledTour) {
		scheduledTourDAO.save(scheduledTour);
		return scheduledTour;
	}

	@Override
	public void delete(Long id) {
		scheduledTourDAO.delete(id);
	}

	@Override
	public void update(ScheduledTour d) {
		scheduledTourDAO.update(d);
	}

}
