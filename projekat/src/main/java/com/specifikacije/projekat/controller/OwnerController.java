package com.specifikacije.projekat.controller;

import com.specifikacije.projekat.bean.SecondConfiguration;
import com.specifikacije.projekat.model.Administrator;
import com.specifikacije.projekat.model.AgencyOwner;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.service.AdministratorService;
import com.specifikacije.projekat.service.AgencyOwnerService;
import com.specifikacije.projekat.service.AgentService;
import com.specifikacije.projekat.service.UserService;
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

@Controller
@RequestMapping("/owner")
public class OwnerController implements ApplicationContextAware {


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SecondConfiguration.ApplicationMemory applicationMemory;

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

    @GetMapping(value="addOwner")  // for showing users in admin page
    public String addOwner(HttpSession session)  throws IOException {
        Object obj = session.getAttribute(LoginLogoutController.KORISNIK_KEY);
        if (!(obj instanceof User)){
            return "ownerAdd";
        }
        return "404NotFound";

    }


    @PostMapping("addOwner")
    public void addOwner(HttpServletResponse response, @RequestParam String name, @RequestParam String surname, @RequestParam String username, @RequestParam String password, @RequestParam String phone, @RequestParam String email, @RequestParam String address ) throws IOException {


        AgencyOwner d = new AgencyOwner(name, surname, username, password, phone, email, address, true, false);

        ownerService.save(d);

        response.sendRedirect("/users/viewAllUsers");


    }

    @PostMapping("/deleteOwner")
    public String deleteOwner(@RequestParam Long id ) {

        AgencyOwner d = ownerService.findOne(id);

        ownerService.delete(d.getId());

        return "redirect:/users/viewAllUsers";
    }


    @GetMapping("/blockOwner")
    public String blockOwner(@RequestParam Long id ) {

        AgencyOwner d = ownerService.findOne(id);
        boolean blocked = d.isBlocked();
        if(blocked)
            d.setBlocked(false);
        else
            d.setBlocked(true);

        ownerService.update(d);

        return "redirect:/users/viewAllUsers";
    }

    @GetMapping(value="/editOwner")
    public String edit(@RequestParam Long id, HttpServletResponse response, Model model, HttpSession session) throws IOException {

        Object obj = session.getAttribute(LoginLogoutController.KORISNIK_KEY);
        if (!(obj instanceof User)){
            AgencyOwner d = ownerService.findOne(id);

            model.addAttribute("user",d);
            model.addAttribute("entityType", "Owner");
            model.addAttribute("entity", "owner");
            return "userEdit";
        }
        return "404NotFound";

    }

    @PostMapping("/editOwner")
    public void edit(@RequestParam Long id, @RequestParam String name, @RequestParam String surname, @RequestParam String username, @RequestParam String password, @RequestParam String phone, @RequestParam String email, @RequestParam String address, HttpServletResponse response) throws IOException {


        AgencyOwner d = ownerService.findOne(id);

        d.setName(name);
        d.setSurname(surname);
        d.setUsername(username);
        d.setPassword(password);
        d.setEmail(email);
        d.setAddress(address);
        d.setPhoneNumber(phone);


        ownerService.update(d);


        response.sendRedirect("/users/viewAllUsers");


    }
}
