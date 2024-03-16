package com.specifikacije.projekat.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import com.specifikacije.projekat.model.Notification;
import com.specifikacije.projekat.service.NotificationService;
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

	@Autowired
	private NotificationService notificationService;
	
	@GetMapping
	public String showSchedulingPage() {
		
		return "scheduledTour";
		
	}
	
	@PostMapping("/ScheduleTour")
	public void ScheduleTour(@RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime, @RequestParam RealEstate realEstate, HttpServletResponse response, HttpSession session) throws IOException {
		Object obj = session.getAttribute(LoginLogoutController.KORISNIK_KEY);
	    User user = (User) obj;
		ScheduledTour newTour = new ScheduledTour(startTime, endTime, user, realEstate);
		Notification notification = new Notification(realEstate.getAgent(), newTour, false);

	
		scheduledTourService.save(newTour);
		notificationService.save(notification);

		response.sendRedirect("/realestate");
	}

	@PostMapping(value = "/decline")
	public void decline(@RequestParam Long notificationId, HttpServletResponse response) throws IOException {
		Notification notification = notificationService.findOne(notificationId);
		ScheduledTour tour = notification.getTour();
		tour.setIsApproved(false);
		response.sendRedirect("/notifications/unread");
	}

	@PostMapping(value = "/approve")
	public void approve(@RequestParam Long notificationId, HttpServletResponse response) throws IOException {
		Notification notification = notificationService.findOne(notificationId);
		ScheduledTour tour = notification.getTour();
		tour.setIsApproved(true);
		response.sendRedirect("/notifications/unread");
	}
}
