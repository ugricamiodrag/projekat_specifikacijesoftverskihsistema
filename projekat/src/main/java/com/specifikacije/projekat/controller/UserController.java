package com.specifikacije.projekat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.specifikacije.projekat.bean.SecondConfiguration.ApplicationMemory;
import com.specifikacije.projekat.model.Administrator;
import com.specifikacije.projekat.model.AgencyOwner;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.service.AdministratorService;
import com.specifikacije.projekat.service.AgencyOwnerService;
import com.specifikacije.projekat.service.AgencyService;
import com.specifikacije.projekat.service.AgentService;
import com.specifikacije.projekat.service.UserService;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/users")
public class UserController implements ApplicationContextAware{

	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private ApplicationMemory applicationMemory;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdministratorService adminService;
	
	@Autowired
	private AgencyOwnerService ownerService;
	
	@Autowired
	private AgentService agentService;
	
	public static final String USER_KEY = "users";
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext; 
		
	}
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {	
		applicationMemory = applicationContext.getBean(ApplicationMemory.class);
	    List<User> allUsers = userService.findAll();
	    
		
	
		
		applicationMemory.put(USER_KEY, allUsers);
	}
	
	@PostMapping(value="/check")
	@ResponseBody
	public Map<String, Boolean> check(@RequestParam String username, @RequestParam String email){
		Map<String, Boolean> response = new HashMap<>();
		Boolean usernameExists = adminService.usernameExists(username) || agentService.usernameExists(username) || ownerService.usernameExists(username) || userService.usernameExists(username);
		Boolean emailExists = adminService.emailExists(email) || agentService.emailExists(email) || ownerService.emailExists(email) || userService.emailExists(email);
		response.put("emailExists", emailExists);
		response.put("usernameExists", usernameExists);
		
		return response;
	}
	
	
	
	
	
	@GetMapping("viewAllUsers") // for showing users in admin page
	public String adminPage(Model model) {
		
		List<User> usersList = userService.findAll();
		List<Administrator> adminsList = adminService.findAll();
		List<Agent> agentsList = agentService.findAll();
		List<AgencyOwner> agencyOwnersList = ownerService.findAll();

		model.addAttribute("users", usersList);
		model.addAttribute("admins", adminsList);
		model.addAttribute("agents", agentsList);
		model.addAttribute("owners", agencyOwnersList);

		return "users";
	}
	//TODO: other CRUD operations for every user type and add attribute in every user for blocking user function        

}
