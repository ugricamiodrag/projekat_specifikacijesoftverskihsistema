package com.specifikacije.projekat.service;

import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.Rented;

import java.time.LocalDate;
import java.util.List;

public interface RentedService {

    Rented findOne(Long id);

    List<Rented> findAll();

    void save(Rented rented);

    void update(Rented rented);

    void delete(Long id);

    boolean rentedExists(RealEstate estate);

    boolean rentedExists(RealEstate estate, LocalDate startDate, LocalDate endDate);

    Rented findOneRequest(Long id);

    List<Rented> findAllRequests();

    void saveRequest(Rented rented);

    void updateRequest(Rented rented);

    void deleteRequest(Long id);

    boolean rentedRequestExists(RealEstate estate);

    boolean rentedRequestExists(RealEstate estate, LocalDate startDate, LocalDate endDate);
}
