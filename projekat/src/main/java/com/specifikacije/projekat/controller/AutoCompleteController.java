package com.specifikacije.projekat.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.service.RealEstateService;


@Controller
@RequestMapping("/autocomplete")
public class AutoCompleteController {
	

	@Autowired
	private RealEstateService realEstateService;
	
	
	// for autocomplete when searching for location
    @GetMapping("/estate")
    public ResponseEntity<List<String>> getDestinations() {
    	
    	 try {
             List<RealEstate> realEstates = realEstateService.findAll();
             List<String> realEstateLocations = realEstates.stream()
                     .map(RealEstate::getLocation)
                     .collect(Collectors.toList());

             return ResponseEntity.ok(realEstateLocations);
         } catch (Exception e) {
             e.printStackTrace();  
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
         }
    }
}