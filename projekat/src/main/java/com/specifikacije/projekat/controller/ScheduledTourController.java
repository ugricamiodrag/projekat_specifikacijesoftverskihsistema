package com.specifikacije.projekat.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.ScheduledTour;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.service.ScheduledTourService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/scheduledTour")
public class ScheduledTourController {
	
	@Autowired
	private ScheduledTourService scheduledTourService;
	
	@GetMapping
	public String showSchedulingPage() {
		
		return "scheduledTour";
		
	}
	
	@PostMapping("/ScheduleTour")
	public void ScheduleTour(@RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime, @RequestParam RealEstate realEstate, HttpServletResponse response, HttpSession session) throws IOException {
		Object obj = session.getAttribute(LoginLogoutController.KORISNIK_KEY);
	    User user = (User) obj;
		ScheduledTour newTour = new ScheduledTour(startTime, endTime, user, realEstate);
	
		scheduledTourService.save(newTour);

		response.sendRedirect("/realestate");
	}
}
