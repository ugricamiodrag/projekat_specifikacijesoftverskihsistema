package com.specifikacije.projekat.service.impl;

import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.specifikacije.projekat.dao.impl.PurchaseDAOimpl;
import com.specifikacije.projekat.model.Purchase;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.RealEstateType;
import com.specifikacije.projekat.model.RentOrBuy;
import com.specifikacije.projekat.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService{

	
	@Autowired
	private PurchaseDAOimpl purchaseDAO;
	
	
	@Override
	public Purchase findOne(Long id) {
		return purchaseDAO.findOne(id);
	}

	@Override
	public List<Purchase> findAll() {
		return purchaseDAO.findAll();
	}

	@Override
	public Purchase save(Purchase agency) {
		return purchaseDAO.save(agency);
	}

	@Override
	public void delete(Long id) {
		purchaseDAO.delete(id);
		
	}

	@Override
	public void update(Purchase d) {
		purchaseDAO.update(d);
		
	}
	
	@Override
	public boolean purchaseExists(RealEstate estate){
		List<Purchase> purchases = purchaseDAO.findAll();
		for(Purchase p: purchases) {
			if(p.getEstate().getId() == estate.getId()) {
				return true;
			}
		}
		
		return false;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public double getMonthlyIncome() {
		
		LocalDate currentDate = LocalDate.now();		
		LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
		LocalDate lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());
        List<Purchase> allPurchases = purchaseDAO.findAll();
        double income = 0;
        

        for (Purchase p : allPurchases) {
        	LocalDate purchaseDate = p.getPurchaseTime().toLocalDate();
        	System.out.println("purchase date: " + purchaseDate);

        	if (purchaseDate.isEqual(firstDayOfMonth) || (purchaseDate.isAfter(firstDayOfMonth) && purchaseDate.isBefore(lastDayOfMonth)) || purchaseDate.isEqual(lastDayOfMonth)) {
        		 
        			double price = p.getEstate().getPrice();
        			
        			if(p.getEstate().getEstateType().equals(RentOrBuy.valueOf("Rent"))) 
        				 income += price * 0.01;// for rented estates, calculate 1% of the price
        			else 
        				income += price * 0.001;// for bought estates, calculate 0.1% of the price
        			
        	        
        	    }
}
        
		 return income;
	}

}
