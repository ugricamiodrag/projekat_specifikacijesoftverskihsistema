package com.specifikacije.projekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/realestate")
public class RealEstateController {

	
	@GetMapping()
	public String showRealEstate(HttpServletRequest request, Model model) {
		
		// todo: get realEstates from database and add to model
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
