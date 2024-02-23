package com.specifikacije.projekat.model;

import java.util.List;

public class Agency {
	private Long id;
	private String name;
	private AgencyOwner owner;
	private String destination;
	private List<Agent> agents;
	
	public Agency() {
	}
	
	public Agency(Long id, String name, AgencyOwner owner, String destination, List<Agent> agents) {
		super();
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.destination = destination;
		this.agents = agents;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AgencyOwner getOwner() {
		return owner;
	}
	public void setOwner(AgencyOwner owner) {
		this.owner = owner;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public List<Agent> getAgents() {
		return agents;
	}
	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}

	@Override
	public String toString() {
		return "Agency [id=" + id + ", name=" + name + ", owner=" + owner + ", destination=" + destination + ", agents="
				+ agents + "]";
	}
}
