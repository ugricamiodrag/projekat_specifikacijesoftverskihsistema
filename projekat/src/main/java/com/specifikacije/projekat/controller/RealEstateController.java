package com.specifikacije.projekat.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.specifikacije.projekat.dao.LikeDislikeDAO;
import com.specifikacije.projekat.model.Administrator;
import com.specifikacije.projekat.model.AgencyOwner;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.RealEstateType;
import com.specifikacije.projekat.model.RentOrBuy;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.service.AgentService;
import com.specifikacije.projekat.service.LikeDislikeService;
import com.specifikacije.projekat.service.RealEstateService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/realestate")
public class RealEstateController {

	@Autowired
	private RealEstateService realEstateService;

	@Autowired
	private LikeDislikeService likeDislikeService;
	
	@Autowired
	private AgentService agentService;
	
	@GetMapping
	public String showRealEstate(HttpServletRequest request, Model model,
			@RequestParam(required=false) String location, 
			@RequestParam(required=false) Integer surfaceFrom, 
			@RequestParam(required=false) Integer surfaceTo, 
			@RequestParam(required=false) Integer priceMin, 
			@RequestParam(required=false) Integer priceMax, 
			@RequestParam(required=false) String rent, 
			@RequestParam(required=false) String buy,
			@RequestParam(required=false) boolean house, 
			@RequestParam(required=false) boolean apartment, 
			@RequestParam(required=false) boolean land,
			@RequestParam(required=false) boolean office) {
		
		
		Map<String, Object> response = new HashMap<>(); // for filtering
		
		
		
		boolean isLoggedIn = false;
		// get user if exists
		Object obj =  request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);
		
		
		// check if user is logged in and users type
		if (obj instanceof User) {
		    isLoggedIn = true;
		    Class<?> objClass = obj.getClass();
		    model.addAttribute("user", objClass);
		    User user = (User) obj;
		    //get users lliked and disliked estates
		    Map<String, List<RealEstate>> likedEstates = likeDislikeService.findAllLikedEstateForUserAndSetAttributes(user.getId());
		    System.out.println("Liked Estates: " + likedEstates.get("likedEstates"));
		    System.out.println("Disiked Estates: " + likedEstates.get("dislikedEstates"));
		    //add to model
		    model.addAttribute("likedEstates", likedEstates.get("likedEstates"));
		    model.addAttribute("dislikedEstates", likedEstates.get("dislikedEstates"));
		    
		
		    
		}else if(obj instanceof Administrator){
			    isLoggedIn = true;   
			    Class<?> objClass = obj.getClass();
			    model.addAttribute("admin", objClass);
			    
		}else if(obj instanceof Agent){
		    isLoggedIn = true;
		    Class<?> objClass = obj.getClass();
		    model.addAttribute("agent", objClass);
		    
		}else if(obj instanceof AgencyOwner){
		    isLoggedIn = true;
		    Class<?> objClass = obj.getClass();
		    model.addAttribute("owner", objClass);
		    
		}else {
		    isLoggedIn = false;
		}
		
		
		
		
		//add attribures
		model.addAttribute("isLoggedIn", isLoggedIn);
		
		
		//for filtering 
		
		if(location != null && location.trim().equals(""))
			location = null;
		if(surfaceFrom != null && surfaceFrom == 0)
			surfaceFrom = null;
		if(surfaceTo != null && surfaceTo == 0)
			surfaceTo = null;
		if(priceMin != null && priceMin == 0)
			priceMin = null;
		if(priceMax != null && priceMax == 0)
			priceMax = null;
		
		// if no filters are added just find all real estates
		if(location == null && surfaceFrom == null && surfaceTo == null && priceMin == null && priceMax == null && house == false && apartment == false && land == false && office == false && rent == null && buy == null) {
			
			List<RealEstate> realEstateList = realEstateService.findAll();
			model.addAttribute("realEstate", realEstateList);
			response.put("realEstate", realEstateList);

			return "index";
			}
		
		// if filters are added find trough filter --> in RealEstateDAO
		List<RealEstate> realEstates = realEstateService.find(location, surfaceFrom, surfaceTo, priceMin, priceMax, rent, buy, house, apartment, land, office);
		
		
		
		model.addAttribute("realEstate", realEstates);

		return "index";
	}
	
	
	@GetMapping(value="/search")
	@ResponseBody
	public Map<String, Object>  search(HttpServletRequest request, Model model,
			@RequestParam(required=false) String location, 
			@RequestParam(required=false) Integer surfaceFrom, 
			@RequestParam(required=false) Integer surfaceTo, 
			@RequestParam(required=false) Integer priceMin, 
			@RequestParam(required=false) Integer priceMax, 
			@RequestParam(required=false) String rent, 
			@RequestParam(required=false) String buy,
			@RequestParam(required=false) boolean house, 
			@RequestParam(required=false) boolean apartment, 
			@RequestParam(required=false) boolean land,
			@RequestParam(required=false) boolean office) throws ParseException {
		
		
//		System.out.println(location);
//		System.out.println(surfaceFrom);
//		System.out.println(surfaceTo);
//		System.out.println(priceMax);
//		System.out.println(priceMin);
//		System.out.println(rent);
//		System.out.println(buy);
//		System.out.println(house);
//		System.out.println(apartment);
//		System.out.println(land);
//		System.out.println(office);
		
		Map<String, Object> response = new HashMap<>();
	
		if(location != null && location.trim().equals(""))
			location = null;
		if(surfaceFrom != null && surfaceFrom == 0)
			surfaceFrom = null;
		if(surfaceTo != null && surfaceTo == 0)
			surfaceTo = null;
		if(priceMin != null && priceMin == 0)
			priceMin = null;
		if(priceMax != null && priceMax == 0)
			priceMax = null;
		
		if(location == null && surfaceFrom == null && surfaceTo == null && priceMin == null && priceMax == null && house == false && apartment == false && land == false && office == false && rent == null && buy == null) {
			
			List<RealEstate> realEstateList = realEstateService.findAll();
			model.addAttribute("realEstate", realEstateList);
			response.put("realEstate", realEstateList);

			return response;
			}
		
		
		List<RealEstate> realEstates = realEstateService.find(location, surfaceFrom, surfaceTo, priceMin, priceMax, rent, buy, house, apartment, land, office);

		model.addAttribute("realEstate", realEstates);

		response.put("realEstate", realEstates);
		return response;
		
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
	
	@GetMapping("/profile")
	public String profile(Model model, HttpServletRequest request) {
		Object obj =  request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);
		String type = "";
		String theHref = "users/editUser";
		if (obj instanceof Administrator) {
			type = "- Administrator -";
			theHref = "admin/editAdmin";
		}
		if (obj instanceof Agent) {
			type = "- Agent -";
			theHref = "agents/editAgent";
		}
		if (obj instanceof AgencyOwner) {
			type = "- Agency owner -";
			theHref = "owner/editOwner";
		}
		model.addAttribute("person", obj);
		model.addAttribute("type", type);
		model.addAttribute("theHref", theHref);
		System.out.println(obj +" "+ type +" "+ theHref);
		return "profile";
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
		
		
		response.sendRedirect("viewAllUsers");
		

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
