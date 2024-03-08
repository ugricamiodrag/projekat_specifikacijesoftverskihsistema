package com.specifikacije.projekat.controller;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.specifikacije.projekat.model.Agency;
import com.specifikacije.projekat.model.AgencyOwner;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.RealEstate;
import com.specifikacije.projekat.service.AgencyOwnerService;
import com.specifikacije.projekat.service.AgencyService;
import com.specifikacije.projekat.service.AgentService;
import com.specifikacije.projekat.service.RealEstateService;


@Controller
@RequestMapping("/agency")
public class AgencyController {

	
	@Autowired
	AgencyOwnerService ownerService;
	
	@Autowired
	RealEstateService estateService;
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	AgencyService agencyService;
	
	@GetMapping
	public String MyAgency(Model model, HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);
		if (obj instanceof AgencyOwner){
			AgencyOwner user = (AgencyOwner) request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);

			Agency agency = agencyService.findOwnersAgency(user.getId());
			if(agency != null) {
				List<Agent> agents = agentService.findAgents(agency.getId());
				model.addAttribute("agents", agents);
				}

			
			model.addAttribute("agency", agency);
			return "myAgency";
		}
		return "404NotFound";

		
	}
	
	
	@GetMapping("addAgent")
	public String AddAgent(Model model, HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);
		if (obj instanceof AgencyOwner){
			AgencyOwner user = (AgencyOwner) request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);

			Agency agency = agencyService.findOwnersAgency(user.getId());
			model.addAttribute("agency", agency);
			return "agentAddByOwner";
		}
		return "404NotFound";

		
	}
	
	 @PostMapping("addAgent")
	    public void addAdmin(HttpServletResponse response, @RequestParam String name, @RequestParam String surname, @RequestParam String username, @RequestParam String password, @RequestParam String phone, @RequestParam String email, @RequestParam String address, @RequestParam Long agencyId) throws IOException {

	        Agency agency = agencyService.findOne(agencyId);
	        Agent d = new Agent(name, surname, username, password, phone, email, address, null, agency, true, false);

	        agentService.save(d);

	        response.sendRedirect("/agency");


	    }
	 
	 @GetMapping("change")
		public String ChangeAgencyInfo(Model model, HttpServletRequest request) {
			Object obj = request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);
			if (obj instanceof AgencyOwner){
				AgencyOwner user = (AgencyOwner) request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);

				Agency agency = agencyService.findOwnersAgency(user.getId());
				model.addAttribute("agency", agency);
				return "agencyInfo";
			}
			return "404NotFound";

			
		}
	 
	 @PostMapping("create")
		public String CreateAgency(Model model, HttpServletRequest request, @RequestParam String name) {
			Object obj = request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);
			if (obj instanceof AgencyOwner){
				AgencyOwner user = (AgencyOwner) request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);
				List<Agent> agents = new ArrayList<>();
				Agency agency = new Agency(name, user, agents);
				agencyService.save(agency);
				model.addAttribute("agency", agency);
				return "redirect:agency";
			}
			return "404NotFound";

			
		}
		
	 @PostMapping("save")
	    public void SaveAgency(HttpServletResponse response, @RequestParam String name, Long agencyid) throws IOException {

	        Agency agency = agencyService.findOne(agencyid);
	        agency.setName(name);
	        agencyService.update(agency);

	        response.sendRedirect("/agency");


	    }
	 
	 @PostMapping("deleteAgent")
	    public void DeleteAgent(HttpServletResponse response, @RequestParam Long agentId) throws IOException {

	        Agent agent = agentService.findOne(agentId);
	        List<RealEstate> estateList = estateService.findAgenciesEstate(agent);
	        System.out.println(agentId);
	        agentService.delete(agentId); 	// remove agent 
	        if(!estateList.isEmpty()) {
	        	 for(RealEstate e: estateList) {		
	 	        	estateService.delete(e.getId());	// and all the estates that he made
	 	        }
	        }
	       
	        response.sendRedirect("/agency");


	    }
	
	
}
