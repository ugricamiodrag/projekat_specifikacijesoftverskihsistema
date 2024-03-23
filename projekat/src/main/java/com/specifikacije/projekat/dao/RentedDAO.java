package com.specifikacije.projekat.dao;

import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.Rented;

import java.time.LocalDate;
import java.util.List;

public interface RentedDAO {

    Rented findOne(Long id);

    List<Rented> findAll();

    void save(Rented rented);

    void update(Rented rented);

    void delete(Long id);

    boolean rentedExists(RealEstate estate);

    boolean rentedExists(RealEstate estate, LocalDate startDate, LocalDate endDate);
}
