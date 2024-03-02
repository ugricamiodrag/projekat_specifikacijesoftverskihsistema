package com.specifikacije.projekat.controller;

import com.specifikacije.projekat.bean.SecondConfiguration;
import com.specifikacije.projekat.model.Administrator;
import com.specifikacije.projekat.model.User;
import com.specifikacije.projekat.service.AdministratorService;
import com.specifikacije.projekat.service.AgencyOwnerService;
import com.specifikacije.projekat.service.AgentService;
import com.specifikacije.projekat.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
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
@RequestMapping("/admin")
public class AdministratorController implements ApplicationContextAware {


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

    @GetMapping(value="addAdmin")  // for showing users in admin page
    public String addAdmin()  throws IOException {

        return "adminAdd";
    }

    @PostMapping("addAdmin")
    public void addAdmin(HttpServletResponse response, @RequestParam String name, @RequestParam String surname, @RequestParam String username, @RequestParam String password, @RequestParam String phone, @RequestParam String email, @RequestParam String address ) throws IOException {


        Administrator d = new Administrator(name, surname, username, password, phone, email, address, true, false);

        adminService.save(d);

        response.sendRedirect("/users/viewAllUsers");


    }

    @PostMapping("/deleteAdmin")
    public String deleteAdmin(@RequestParam Long id ) {

        Administrator d = adminService.findOne(id);

        adminService.delete(d.getId());

        return "redirect:/users/viewAllUsers";
    }


    @GetMapping("/blockAdmin")
    public String blockAdmin(@RequestParam Long id ) {

        Administrator d = adminService.findOne(id);
        boolean blocked = d.isBlocked();
        if(blocked)
            d.setBlocked(false);
        else
            d.setBlocked(true);

        adminService.update(d);

        return "redirect:/users/viewAllUsers";
    }

    @GetMapping(value="/editAdmin")
    public String edit(@RequestParam Long id, HttpServletResponse response, Model model) throws IOException {


        Administrator d = adminService.findOne(id);

        model.addAttribute("user",d);
        model.addAttribute("entityType", "Admin");
        model.addAttribute("entity", "admin");
        return "userEdit";

    }

    @PostMapping("/editAdmin")
    public void edit(@RequestParam Long id, @RequestParam String name, @RequestParam String surname, @RequestParam String username, @RequestParam String password, @RequestParam String phone, @RequestParam String email, @RequestParam String address, HttpServletResponse response) throws IOException {


        Administrator d = adminService.findOne(id);

        d.setName(name);
        d.setSurname(surname);
        d.setUsername(username);
        d.setPassword(password);
        d.setEmail(email);
        d.setAddress(address);
        d.setPhoneNumber(phone);


        adminService.update(d);


        response.sendRedirect("/users/viewAllUsers");


    }
}
