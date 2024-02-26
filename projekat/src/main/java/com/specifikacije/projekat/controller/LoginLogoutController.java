package com.specifikacije.projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.specifikacije.projekat.service.AdministratorService;
import com.specifikacije.projekat.service.AgencyOwnerService;
import com.specifikacije.projekat.service.AgentService;
import com.specifikacije.projekat.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginLogoutController {

	public static final String KORISNIK_KEY = "korisnik";

	
	@GetMapping
	public String showLoginPage() {
		
		return "login.html";
		
	}
	
	
	@PostMapping("/logIn")
	public String LogIn(@RequestParam String username, @RequestParam String password) {
		
//		TODO: make a method to find user by name and lastname and set name to be unique, or add username and password to person class
		return "login.html";
		
	}
}
