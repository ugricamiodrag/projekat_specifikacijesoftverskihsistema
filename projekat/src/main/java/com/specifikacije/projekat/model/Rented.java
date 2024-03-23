package com.specifikacije.projekat.model;

import java.time.LocalDate;

public class Rented {

    private Long id;
    private User user;
    private RealEstate estate;
    private LocalDate startDate;
    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RealEstate getEstate() {
        return estate;
    }

    public void setEstate(RealEstate estate) {
        this.estate = estate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Rented(User user, RealEstate estate, LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.estate = estate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Rented(Long id, User user, RealEstate estate, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.user = user;
        this.estate = estate;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
