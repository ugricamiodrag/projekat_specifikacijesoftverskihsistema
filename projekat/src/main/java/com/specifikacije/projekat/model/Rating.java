package com.specifikacije.projekat.model;

public class Rating {
	private Long id;
	private Double grade;
	private String comment;
	
	public Rating(Long id, Double grade, String comment) {
		super();
		this.id = id;
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
}
