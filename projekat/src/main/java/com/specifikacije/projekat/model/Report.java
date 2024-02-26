package com.specifikacije.projekat.model;

import java.time.LocalDateTime;

public class Report {
	private Long id;
	private Administrator admin;
	private LocalDateTime date;
	private double income;
	


	public Report(Long id, Administrator admin, LocalDateTime date, double income) {
		super();
		this.id = id;
		this.admin = admin;
		this.date = date;
		this.income = income;
	}


	public Report(Administrator admin, LocalDateTime date, double income) {
		super();
		this.admin = admin;
		this.date = date;
		this.income = income;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Administrator getAdmin() {
		return admin;
	}


	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}


	public double getIncome() {
		return income;
	}


	public void setIncome(double income) {
		this.income = income;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
