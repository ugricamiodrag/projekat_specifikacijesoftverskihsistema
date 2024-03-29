package com.specifikacije.projekat.dao;

import com.specifikacije.projekat.model.Purchase;

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

}
