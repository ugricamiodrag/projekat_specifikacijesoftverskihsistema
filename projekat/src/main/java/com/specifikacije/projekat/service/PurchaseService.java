package com.specifikacije.projekat.service;

import java.util.List;

import com.specifikacije.projekat.model.Purchase;
import com.specifikacije.projekat.model.RealEstate;

public interface PurchaseService {

	Purchase findOne(Long id);
	List<Purchase> findAll();
	Purchase save(Purchase agency);
	void delete(Long id);
	void update(Purchase d);
	boolean purchaseExists(RealEstate estate);
	double getMonthlyIncome();
}
