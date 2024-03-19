package com.specifikacije.projekat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.Rating;
import com.specifikacije.projekat.service.AgentService;
import com.specifikacije.projekat.service.RatingService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class RatingController {
	
		@Autowired
		RatingService ratingService;
		
		@Autowired
		AgentService agentService;

	 	@GetMapping("/rateAgent")
	    @ResponseBody
	    public ResponseEntity<Rating>  rateAgent( HttpServletRequest request, @RequestParam String comment, @RequestParam int rating, @RequestParam Long agentId) {
	        
	 		User obj = (User) request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);
	 		Agent agent = agentService.findOne(agentId);
	 		Rating rate = new Rating(obj, agent, (double) rating, comment);
			ratingService.save(rate);
			return ResponseEntity.status(HttpStatus.OK).body(rate);
	    }
	
}

