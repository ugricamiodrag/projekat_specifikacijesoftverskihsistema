package com.specifikacije.projekat.service.impl;

import com.specifikacije.projekat.dao.RentedDAO;
import com.specifikacije.projekat.model.Purchase;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.Rented;
import com.specifikacije.projekat.model.User;
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

    @Override
    public Rented findOneRequest(Long id) {
        return rentedDAO.findOneRequest(id);
    }

    @Override
    public List<Rented> findAllRequests() {
        return rentedDAO.findAllRequests();
    }

    @Override
    public void saveRequest(Rented rented) {
        rentedDAO.saveRequest(rented);
    }

    @Override
    public void updateRequest(Rented rented) {
        rentedDAO.updateRequest(rented);
    }

    @Override
    public void deleteRequest(Long id) {
        rentedDAO.deleteRequest(id);
    }

    @Override
    public boolean rentedRequestExists(RealEstate estate) {
        return rentedDAO.rentedRequestExists(estate);
    }

    @Override
    public boolean rentedRequestExists(RealEstate estate, LocalDate startDate, LocalDate endDate) {
        return rentedDAO.rentedRequestExists(estate, startDate, endDate);
    }

    @Override
    public boolean rentedRequestExists(RealEstate estate, User user) {
        return rentedDAO.rentedRequestExists(estate, user);
    }

    @Override
    public void deleteRequests(Long id) {
        rentedDAO.deleteRequests(id);
    }

    @Override
    public List<Rented> findByUser(User user) {
        return rentedDAO.findByUser(user);
    }
}
