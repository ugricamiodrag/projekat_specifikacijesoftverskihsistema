package com.specifikacije.projekat.service.impl;

import com.specifikacije.projekat.dao.NotificationDAO;
import com.specifikacije.projekat.model.Notification;
import com.specifikacije.projekat.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDAO notificationDAO;

    @Override
    public Notification findOne(Long id) {
        return notificationDAO.findOne(id);
    }

    @Override
    public List<Notification> findAll() {
        return notificationDAO.findAll();
    }

    @Override
    public void save(Notification notification) {
        notificationDAO.save(notification);
    }

    @Override
    public void update(Notification notification) {
        notificationDAO.update(notification);
    }

    @Override
    public void delete(Long id) {
        notificationDAO.delete(id);
    }

    @Override
    public List<Notification> findByAgent(Long id) {
        return notificationDAO.findByAgent(id);
    }
}
