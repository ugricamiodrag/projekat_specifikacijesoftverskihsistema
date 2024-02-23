package com.specifikacije.projekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.service.RealEstateService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/realestate")
public class RealEstateController {

	@Autowired
	private RealEstateService realEstateService;
	
	@GetMapping
	public String showRealEstate(HttpServletRequest request, Model model) {
		
		List<RealEstate> realEstateList = realEstateService.findAll();
		
		// tocheck if the data is loaded, needed table 'estate' in database to work, get data from service              
		for(RealEstate e: realEstateList ) {
			System.out.println(e.getLocation());
		}
		
		model.addAttribute("realEstate", realEstateList);
		return "index";
	}
	
	
	@GetMapping("/viewOne")
	public String viewOne() {
		return "oneRealEstate";
	}
	
	@GetMapping("/adminPage")
	public String adminPage() {
		return "adminPage";
	}
	
			
	
}
