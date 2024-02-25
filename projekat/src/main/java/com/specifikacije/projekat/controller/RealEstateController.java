package com.specifikacije.projekat.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.RealEstateType;
import com.specifikacije.projekat.model.RentOrBuy;
import com.specifikacije.projekat.service.AgentService;
import com.specifikacije.projekat.service.RealEstateService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/realestate")
public class RealEstateController {

	@Autowired
	private RealEstateService realEstateService;
	
	@Autowired
	private AgentService agentService;
	
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
	public String viewOne(@RequestParam Long id, Model model) {
		
		RealEstate d = realEstateService.findOne(id);
		
		model.addAttribute("oneRealEstate", d);

		return "oneRealEstate";
	}
	
	@GetMapping("/adminPage")
	public String adminPage(Model model) {
		
		List<RealEstate> realEstateList = realEstateService.findAll();

		model.addAttribute("realEstates", realEstateList);
		return "adminPage";
	}
	
	
	
	@PostMapping("/delete")
	public String deleteRealEstate(@RequestParam Long id ) {
		
		RealEstate d = realEstateService.findOne(id);
		
		realEstateService.delete(d.getId());

		return "redirect:adminPage";
	}
	
	
	@GetMapping(value="/edit")
	public String edit(@RequestParam Long id, HttpServletResponse response, Model model) throws IOException {
		

		RealEstate d = realEstateService.findOne(id);
		
		model.addAttribute("realEstate",d);
		
		return "realEstateEdit";
		
	}
	
	@PostMapping("/edit")
	public void edit(@RequestParam Long id, @RequestParam String type, @RequestParam String location, @RequestParam double price, @RequestParam String rentOrBuy, @RequestParam int surface, HttpServletResponse response) throws IOException {
		

		RealEstate d = realEstateService.findOne(id);

		
		
		d.setId(id);
		d.setType(RealEstateType.valueOf(type));
		d.setLocation(location);
		d.setPrice(price);
		d.setRentOrBuy(RentOrBuy.valueOf(rentOrBuy));
		d.setSurface(surface);
		// TODO: set atributes

		realEstateService.update(d);
		
		
		response.sendRedirect("adminPage");
		

	}
	
	@GetMapping(value="/add")
	public String add(HttpServletResponse response, Model model) throws IOException {
		
		List<Agent> agents= agentService.findAll();
		model.addAttribute("agents",agents);
		return "realEstateAdd";
		
	}
	
	@PostMapping("add")
	public void add(HttpServletResponse response, @RequestParam Long agentId, @RequestParam int surface, @RequestParam String picture, @RequestParam String type, @RequestParam String location, @RequestParam double price, @RequestParam String rentOrBuy) throws IOException {
		

		RealEstate d = new RealEstate();

		d.setType(RealEstateType.valueOf(type));
		d.setLocation(location);
		d.setPrice(price);
		d.setPicture(picture);
		d.setRentOrBuy(RentOrBuy.valueOf(rentOrBuy));
		d.setIsActive(false);
		d.setSurface(surface);
		Agent agent = agentService.findOne(agentId);
		d.setAgent(agent);
		d.setNumberOfVisitRequests(0);
		d.setGrade(0.0);
		d.setViewNumber(0.0);

		realEstateService.save(d);
		
		response.sendRedirect("adminPage");
		
		
	}
	
	
	
			
	
}
