package com.specifikacije.projekat.controller;

import com.specifikacije.projekat.bean.SecondConfiguration;
import com.specifikacije.projekat.model.Agent;
import com.specifikacije.projekat.model.Notification;
import com.specifikacije.projekat.service.*;
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

@Controller
@RequestMapping("/notifications")
public class NotificationsController implements ApplicationContextAware {


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

    @Autowired
    private NotificationService notificationService;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @GetMapping(value = "/unread")
    public String readNotifications(HttpSession session){
        Object obj = session.getAttribute(LoginLogoutController.KORISNIK_KEY);
        if(obj instanceof Agent){
            return "notifications";
        }
        return "404NotFound";
    }

    @PostMapping(value="/readOne")
    public String readOne(@RequestParam Long id, Model model){
        Notification notification = notificationService.findOne(id);
        notification.setRead(true);
        model.addAttribute("notification", notification);
        return "oneNotification";

    }
}
