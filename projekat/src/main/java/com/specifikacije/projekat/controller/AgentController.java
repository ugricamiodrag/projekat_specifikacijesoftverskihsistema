package com.specifikacije.projekat.controller;

import com.specifikacije.projekat.bean.SecondConfiguration;
import com.specifikacije.projekat.model.Administrator;
import com.specifikacije.projekat.model.Agency;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.service.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/agents")
public class AgentController  implements ApplicationContextAware {


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SecondConfiguration.ApplicationMemory applicationMemory;

    @Autowired
    private AgentService agentService;

    @Autowired
    private AgencyService agencyService;

    public static final String USER_KEY = "users";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @GetMapping(value="addAgent")  // for showing users in admin page
    public String addAgent(Model model, HttpSession session)  throws IOException {
        Object obj = session.getAttribute(LoginLogoutController.KORISNIK_KEY);
        if (!(obj instanceof User)){
            List<Agency> allAgencies = agencyService.findAll();
            model.addAttribute("allAgencies", allAgencies);
            return "agentAdd";
        }
        return "404NotFound";

    }

    @PostMapping("addAgent")
    public void addAdmin(HttpServletResponse response, @RequestParam String name, @RequestParam String surname, @RequestParam String username, @RequestParam String password, @RequestParam String phone, @RequestParam String email, @RequestParam String address, @RequestParam Long agencySelect) throws IOException {

        Agency agency = agencyService.findOne(agencySelect);
        Agent d = new Agent(name, surname, username, password, phone, email, address, null, agency, true, false);

        agentService.save(d);

        response.sendRedirect("/users/viewAllUsers");


    }
    
    
    @GetMapping(value="/editAgent")
	public String edit(@RequestParam Long id, HttpServletResponse response, Model model, HttpSession session) throws IOException {
        Object obj = session.getAttribute(LoginLogoutController.KORISNIK_KEY);
        if (!(obj instanceof User)){
            Agent d = agentService.findOne(id);

            model.addAttribute("user",d);
            model.addAttribute("entityType", "Agent");
            model.addAttribute("entity", "agents");

            return "userEdit";
        }
        return "404NotFound";


		
	}
	
	@PostMapping("/editAgent")
	public void edit(@RequestParam Long id, @RequestParam String name, @RequestParam String surname, @RequestParam String username, @RequestParam String password, @RequestParam String phone, @RequestParam String email, @RequestParam String address, HttpServletResponse response) throws IOException {
		

		Agent d = agentService.findOne(id);

		d.setName(name);
		d.setSurname(surname);
		d.setUsername(username);
		d.setPassword(password);
		d.setEmail(email);
		d.setAddress(address);
		d.setPhoneNumber(phone);

		agentService.update(d);

		response.sendRedirect("viewAllUsers");

	}
	


    @PostMapping("/deleteAgent")
    public String deleteAgent(@RequestParam Long id ) {

        Agent d = agentService.findOne(id);

        agentService.delete(d.getId());

        return "redirect:/users/viewAllUsers";
    }


    @GetMapping("/blockAgent")
    public String blockAgent(@RequestParam Long id ) {

        Agent d = agentService.findOne(id);
        boolean blocked = d.isBlocked();
        if(blocked) {
            d.setBlocked(false);
        } else {
            d.setBlocked(true);
        }
        agentService.update(d);

        return "redirect:/users/viewAllUsers";
    }
}
