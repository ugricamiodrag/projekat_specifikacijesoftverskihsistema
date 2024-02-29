package com.specifikacije.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

import com.specifikacije.projekat.model.Agency;
import com.specifikacije.projekat.model.AgencyOwner;
import com.specifikacije.projekat.service.AgencyOwnerService;
import com.specifikacije.projekat.service.AgencyService;


@Controller
@RequestMapping("/agency")
public class AgencyController {

	
	@Autowired
	AgencyOwnerService ownerService;
	
	@Autowired
	AgencyService agencyService;
	
	@GetMapping
	public String showLoginPage(Model model, HttpServletRequest request) {
		
		AgencyOwner user = (AgencyOwner) request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);
		
		Agency agency = agencyService.findOwnersAgency(user.getId());
		
		model.addAttribute("agency", agency);
		return "myAgency";
		
	}
	
	
}
