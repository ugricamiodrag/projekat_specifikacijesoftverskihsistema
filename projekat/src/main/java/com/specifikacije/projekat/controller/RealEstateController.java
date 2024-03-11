package com.specifikacije.projekat.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;
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
import org.springframework.web.multipart.MultipartFile;

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
	public String showRealEstate(HttpServletRequest request, Model model) {
		
		
		Map<String, Object> response = new HashMap<>();
		List<RealEstate> realEstateList = realEstateService.findAll();
		
		
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
			Agent agent = (Agent) obj;
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
		
		List<String> types = new ArrayList<>();
		for (RealEstateType type : RealEstateType.values()){
			types.add(String.valueOf(type));

		}


		model.addAttribute("realEstate", realEstateList);
		model.addAttribute("typesOfEstate", types);
		response.put("realEstate", realEstateList);
		response.put("typesOfEstate", types);

		return "index";




	}
	
	
	@GetMapping(value="/search")
	@ResponseBody
	public Map<String, Object>  search(HttpServletRequest request, Model model,
									   @RequestParam(required=false) String location,
									   @RequestParam(required=false) String surfaceFrom,
									   @RequestParam(required=false) String surfaceTo,
									   @RequestParam(required=false) String priceMin,
									   @RequestParam(required=false) String priceMax,
									   @RequestParam(required=false) String rent,
									   @RequestParam(required=false) String buy,
									   @RequestParam(required=false) List<String> propertyTypes) throws ParseException {


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



		List<RealEstate> realEstates = realEstateService.find(location,
				parseInteger(surfaceFrom),
				parseInteger(surfaceTo),
				parseDouble(priceMin),
				parseDouble(priceMax),
				rent, buy, propertyTypes);

		model.addAttribute("realEstate", realEstates);

		response.put("realEstate", realEstates);
		return response;

	}
	private Integer parseInteger(String value) {
		if (value != null && !value.isEmpty()) {
			return Integer.parseInt(value);
		}
		return null;
	}

	private Double parseDouble(String value) {
		if (value != null && !value.isEmpty()) {
			return Double.parseDouble(value);
		}
		return null;
	}
	
	@GetMapping("/viewOne")
	public String viewOne(@RequestParam Long id, Model model) {
		
		RealEstate d = realEstateService.findOne(id);
		
		d.setViewNumber(d.getViewNumber()+ 1); // Everytime the user views the realestate add one to viewNumber
		realEstateService.update(d); // Update that view
		
		model.addAttribute("oneRealEstate", d);

		return "oneRealEstate";
	}
	
	@GetMapping("/adminPage")
	public String adminPage(Model model) {
		
		List<RealEstate> realEstateList = realEstateService.findAllHidden();

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
	public String edit(@RequestParam Long id, HttpServletResponse response, Model model, HttpSession session) throws IOException {

		Object obj = session.getAttribute(LoginLogoutController.KORISNIK_KEY);
		if (!(obj instanceof User)){
			RealEstate d = realEstateService.findOne(id);

			model.addAttribute("realEstate",d);

			return "realEstateEdit";
		}
		return "404NotFound";


		
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
	public String add(HttpServletResponse response, Model model, HttpSession session) throws IOException {
		
		Agent obj = (Agent) session.getAttribute(LoginLogoutController.KORISNIK_KEY);
		if (obj == null){
			return "404NotFound";
		}
		List<String> allTypes = new ArrayList<>();
		for (RealEstateType types : RealEstateType.values()){
			allTypes.add(types.toString());
		}
		List<RealEstate> realestates = realEstateService.findAgenciesEstate(obj);
		for(RealEstate a: realestates) {
			System.out.println(a.getId());
		}
		model.addAttribute("realEstates", realestates);
		model.addAttribute("typesOfEstate", allTypes);
		
		return "realEstateAdd";
		
	}
	
	@PostMapping("add")
	public void add(HttpServletResponse response, HttpSession session, @RequestParam int surface, @RequestParam("picture") MultipartFile picture, @RequestParam String type, @RequestParam String location, @RequestParam double price, @RequestParam String rentOrBuy) throws IOException {
		
		Agent agent = (Agent) session.getAttribute(LoginLogoutController.KORISNIK_KEY);
		RealEstate d = new RealEstate();
		String relativePath = picture.getOriginalFilename();
		d.setType(RealEstateType.valueOf(type));
		d.setLocation(location);
		d.setPrice(price);
		d.setPicture(relativePath);
		d.setRentOrBuy(RentOrBuy.valueOf(rentOrBuy));
		d.setIsActive(false);
		d.setSurface(surface);
		d.setAgent(agent);
		d.setNumberOfVisitRequests(0);
		d.setGrade(0.0);
		d.setViewNumber(0.0);
		d.setIsActive(true);

		realEstateService.save(d);
		
		response.sendRedirect("adminPage");
		
		
	}
	
	
	@GetMapping(value="/hide")
	public String hideRealEstate(@RequestParam Long id, Model model) throws IOException {

			RealEstate d = realEstateService.findOne(id);
			if(d.getIsActive())
				d.setIsActive(false);
			else
				d.setIsActive(true);
			
			realEstateService.update(d);

			return "redirect:adminPage";
		

	}
	
	
			
	
}
