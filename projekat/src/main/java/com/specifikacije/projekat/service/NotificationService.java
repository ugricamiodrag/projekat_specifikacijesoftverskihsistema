package com.specifikacije.projekat.service;

import com.specifikacije.projekat.model.Notification;

import java.util.List;

public interface NotificationService {
    public Notification findOne(Long id);

    public List<Notification> findAll();

    public void save(Notification notification);

    public void update(Notification notification);

    public void delete(Long id);

    public List<Notification> findByAgent(Long id);
}
