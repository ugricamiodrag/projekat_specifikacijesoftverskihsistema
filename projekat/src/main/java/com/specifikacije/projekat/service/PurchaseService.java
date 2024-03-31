package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.Purchase;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.User;

public interface PurchaseService {

	Purchase findOne(Long id);
	List<Purchase> findAll();
	Purchase save(Purchase agency);
	void delete(Long id);
	void update(Purchase d);
	boolean purchaseExists(RealEstate estate);
	double getMonthlyIncome();

	Purchase findOneRequest(Long id);
	List<Purchase> findAllRequests();
	Purchase saveRequest(Purchase purchase);
	void updateRequest(Purchase purchase);
	void deleteRequest(Long id);

	boolean requestExists(RealEstate estate, User user);

	void deleteRequests(Long id);
	List<Purchase> findByUser(User user);

}
