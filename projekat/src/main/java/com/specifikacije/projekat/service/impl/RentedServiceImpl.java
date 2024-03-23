package com.specifikacije.projekat.service.impl;

import com.specifikacije.projekat.dao.RentedDAO;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.Rented;
import com.specifikacije.projekat.service.RentedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentedServiceImpl implements RentedService {

    @Autowired
    private RentedDAO rentedDAO;

    @Override
    public Rented findOne(Long id) {
        return rentedDAO.findOne(id);
    }

    @Override
    public List<Rented> findAll() {
        return rentedDAO.findAll();
    }

    @Override
    public void save(Rented rented) {
        rentedDAO.save(rented);
    }

    @Override
    public void update(Rented rented) {
        rentedDAO.update(rented);
    }

    @Override
    public void delete(Long id) {
        rentedDAO.delete(id);
    }

    @Override
    public boolean rentedExists(RealEstate estate) {
        return rentedDAO.rentedExists(estate);
    }

    @Override
    public boolean rentedExists(RealEstate estate, LocalDate startDate, LocalDate endDate) {
        return rentedDAO.rentedExists(estate, startDate, endDate);
    }
}
