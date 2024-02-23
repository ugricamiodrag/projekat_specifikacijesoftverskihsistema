package com.specifikacije.projekat.model;

public class Rating {
	private Long id;
	private User user;
	private Agent agent;
	private Double grade;
	private String comment;
	


	public Rating(User user, Agent agent, Double grade, String comment) {
		super();
		this.user = user;
		this.agent = agent;
		this.grade = grade;
		this.comment = comment;
	}

	public Rating(Long id, User user, Agent agent, Double grade, String comment) {
		super();
		this.id = id;
		this.user = user;
		this.agent = agent;
		this.grade = grade;
		this.comment = comment;
	}

	public Rating() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getGrade() {
		return grade;
	}
	public void setGrade(Double grade) {
		this.grade = grade;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", grade=" + grade + ", comment=" + comment + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
}
