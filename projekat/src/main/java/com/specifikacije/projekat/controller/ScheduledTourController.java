package com.specifikacije.projekat.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import com.specifikacije.projekat.model.Agency;
import com.specifikacije.projekat.model.Notification;
import com.specifikacije.projekat.service.NotificationService;
import com.specifikacije.projekat.service.RealEstateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.ScheduledTour;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.service.ScheduledTourService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/scheduledTour")
public class ScheduledTourController {
	
	@Autowired
	private ScheduledTourService scheduledTourService;

	@Autowired
	private RealEstateService realestateService;
	
	@Autowired
	private NotificationService notificationService;
	
	@GetMapping
	public String showSchedulingPage(@RequestParam("realEstateId") Long realEstateId, Model model, HttpServletRequest request) {
		List<ScheduledTour> scheduledTours = scheduledTourService.findAll();
		model.addAttribute("scheduledTours", scheduledTours);
		model.addAttribute("estateId", realEstateId);
		return "scheduledTour";
		
	}
	
	@PostMapping(value = "/ScheduleTour")
	public void ScheduleTour(@RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime, @RequestParam String realEstateId, HttpServletResponse response, HttpSession session) throws IOException {
		Object obj = session.getAttribute(LoginLogoutController.KORISNIK_KEY);
	    User user = (User) obj;
	    RealEstate realEstate = realestateService.findOne(Long.parseLong(realEstateId));
		ScheduledTour newTour = new ScheduledTour(startTime, endTime, user, realEstate);
		ScheduledTour savedTour = scheduledTourService.save(newTour);
		
		System.out.println("Saved ScheduledTour ID: " + savedTour.getId());
		
		Notification notification = new Notification(realEstate.getAgent(), newTour, false);

	
		notificationService.save(notification);

		response.sendRedirect("/realestate");
	}

	@PostMapping(value = "/decline")
	public void decline(@RequestParam Long notificationId, HttpServletResponse response) throws IOException {
		Notification notification = notificationService.findOne(notificationId);
		ScheduledTour tour = notification.getTour();
		tour.setIsApproved(false);
		scheduledTourService.update(tour);
		response.sendRedirect("/notifications/unread");
	}

	@PostMapping(value = "/approve")
	public void approve(@RequestParam Long notificationId, HttpServletResponse response) throws IOException {
		Notification notification = notificationService.findOne(notificationId);
		ScheduledTour tour = notification.getTour();
		tour.setIsApproved(true);
		scheduledTourService.update(tour);
		response.sendRedirect("/notifications/unread");
	}
}
