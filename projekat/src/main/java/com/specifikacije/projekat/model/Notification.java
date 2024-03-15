package com.specifikacije.projekat.model;

public class Notification {
    private Long id;
    private Agent agent;
    private ScheduledTour tour;
    private boolean isRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public ScheduledTour getTour() {
        return tour;
    }

    public void setTour(ScheduledTour tour) {
        this.tour = tour;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Notification(Long id, Agent agent, ScheduledTour tour, boolean isRead) {
        this.id = id;
        this.agent = agent;
        this.tour = tour;
        this.isRead = isRead;
    }

    public Notification(Agent agent, ScheduledTour tour, boolean isRead) {
        this.agent = agent;
        this.tour = tour;
        this.isRead = isRead;
    }
}
