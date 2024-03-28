package com.specifikacije.projekat.controller;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.specifikacije.projekat.model.Administrator;
import com.specifikacije.projekat.model.Report;
import com.specifikacije.projekat.service.PurchaseService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	
	@GetMapping
	public String reportShow(Model model) {
		
		return "report";
	}
	
	@GetMapping("monthlyReport")
	@ResponseBody
	public Report monthlyReport(Model model, HttpSession session) {
		
		Administrator admin = (Administrator) session.getAttribute(LoginLogoutController.KORISNIK_KEY); // convert it to user because only user can see that button and make a purchase
		if (admin == null){
			return null; // if session expired return not found
		}
		LocalDateTime currentDateTime = LocalDateTime.now();

		double income = purchaseService.getMonthlyIncome();
		Report report = new Report(admin, currentDateTime, income);
	    return report;
	}

}
