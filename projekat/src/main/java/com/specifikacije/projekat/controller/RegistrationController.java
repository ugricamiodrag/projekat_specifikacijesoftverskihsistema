package com.specifikacije.projekat.controller;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.model.Person;

import com.specifikacije.projekat.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String showRegistrationPage() {
		
		return "registration.html";
		
	}
	
	@PostMapping("/register")
	public void Register(@RequestParam String name, @RequestParam String surname, @RequestParam String username, @RequestParam String password, @RequestParam String phoneNumber, @RequestParam String email, @RequestParam String address, HttpServletResponse response) throws IOException {
		
		
		User newUser = new User(name, surname, username, password, phoneNumber, email, address, true);
	
		userService.save(newUser);

		response.sendRedirect("/realestate");
	}
	
}
