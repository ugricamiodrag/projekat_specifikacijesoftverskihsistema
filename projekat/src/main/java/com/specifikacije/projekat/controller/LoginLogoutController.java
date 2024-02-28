package com.specifikacije.projekat.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.model.AgencyOwner;
import com.specifikacije.projekat.model.Administrator;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.service.AdministratorService;
import com.specifikacije.projekat.service.AgencyOwnerService;
import com.specifikacije.projekat.service.AgentService;
import com.specifikacije.projekat.service.LoginService;
import com.specifikacije.projekat.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginLogoutController {

	public static final String KORISNIK_KEY = "korisnik";

	@Autowired 
	LoginService loginService;
	
	@GetMapping
	public String showLoginPage(Model model) {
		
        String message = (String) model.getAttribute("message");
        System.out.println(message);
		return "login.html";
		
	}
	
	
	@SuppressWarnings("unused")
	@PostMapping("/logIn")
	public String LogIn(@RequestParam String username, @RequestParam String password, Model model, RedirectAttributes redirectAttributes, HttpSession session) throws IOException {
		
		// error message that will be displayed in .html
		String error = "";

		
		redirectAttributes.addFlashAttribute("username", username);
		redirectAttributes.addFlashAttribute("password", password);
		
		//check if the user is already logged in
		if (session.getAttribute(LoginLogoutController.KORISNIK_KEY)!=null) {
			error = "You are already logged in, you need to log out first!";
			redirectAttributes.addFlashAttribute("errorPassword", error);
			return "redirect:/login";

		}

		//check if username is empty
		if(username != null && username.isEmpty()) {
			error = "Username can`t be blank!";
			redirectAttributes.addFlashAttribute("errorUsername", error);
			return "redirect:/login";
		}
			
		//check if password is empty
		if(password != null && password.isEmpty()) {
			error = "Password can`t be blank!";
			redirectAttributes.addFlashAttribute("errorPassword", error);
			return "redirect:/login";
		
		}

		
		//find user, agent, owner and admin if found, before login check if password is correct if not send with 
		User user = loginService.findUser(username);
		Administrator admin = loginService.findAdmin(username);
		AgencyOwner owner = loginService.findAgenctOwner(username);
    	Agent agent = loginService.findAgent(username);

    	//if any user doesnt exist then set error message that the user doesnt exist in database
    	if(user == null && agent == null && owner == null && admin == null) {
    		error="User doesn`t exist.";
    		model.addAttribute("errorPassword", error);
    		redirectAttributes.addFlashAttribute("errorPassword", error);
    		return "redirect:/login";
    	}
    			
    			
    	
        if(user != null) {
        	if (!user.getPassword().equals(password)) {
    			error="Wrong password.";
    			redirectAttributes.addFlashAttribute("errorPassword", error);
    			return "redirect:/login";
    		}
        	session.setAttribute(LoginLogoutController.KORISNIK_KEY, user); 
        	
        }
		
        if(agent != null) {
    		if (!agent.getPassword().equals(password)) {
    			error="Wrong password.";
    			redirectAttributes.addFlashAttribute("errorPassword", error);
    			return "redirect:/login";
    		}
    		session.setAttribute(LoginLogoutController.KORISNIK_KEY, agent);
    		
        }
			
		if(owner != null) {
			if (!owner.getPassword().equals(password)) {
				error="Wrong password.";
				redirectAttributes.addFlashAttribute("errorPassword", error);
				return "redirect:/login";
			}
				session.setAttribute(LoginLogoutController.KORISNIK_KEY, owner);
				
		}
       
		if(admin != null) {
			if (!admin.getPassword().equals(password)) {
				error="Wrong password.";
				redirectAttributes.addFlashAttribute("errorPassword", error);
				return "redirect:/login";
			}
				session.setAttribute(LoginLogoutController.KORISNIK_KEY, admin);
				
		}
		
		// if no error occured then redirect to main page
		return "redirect:/realestate";
	}
	
	@GetMapping(value="/logOut")
	public String logout(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpServletResponse response, Long idKorisnika) throws IOException {	
		
		Object user = request.getSession().getAttribute(LoginLogoutController.KORISNIK_KEY);
		
		String error = "";
		
		if(user==null) {
			error="User is not logged in";
			redirectAttributes.addFlashAttribute("errorPassword", error);
			return "redirect:/login";
		}
			
		
		request.getSession().removeAttribute(LoginLogoutController.KORISNIK_KEY);
		request.getSession().invalidate();
		
		
		return "redirect:/login";	
	}
}
