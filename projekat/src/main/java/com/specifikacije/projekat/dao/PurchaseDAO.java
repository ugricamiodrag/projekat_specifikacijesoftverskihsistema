package com.specifikacije.projekat.dao;

import com.specifikacije.projekat.model.Purchase;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.Rented;
import com.specifikacije.projekat.model.User;

import java.util.List;

public interface PurchaseDAO {
    Purchase findOne(Long id);
    List<Purchase> findAll();
    Purchase save(Purchase purchase);
    void update(Purchase purchase);
    void delete(Long id);
    Purchase findOneRequest(Long id);
    List<Purchase> findAllRequests();
    Purchase saveRequest(Purchase purchase);
    void updateRequest(Purchase purchase);
    void deleteRequest(Long id);

    boolean requestExists(RealEstate estate, User user);

    void deleteRequests(Long id);

    List<Purchase> findByUser(User user);

}
