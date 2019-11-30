package com.eap.lifepilot.entities;

public class AssessmentScore {

	private String id;
	private String score;
	private String date;
	private String username;
	private String lifePilotAnswersWithDelimeters = "";
	
	private int quizType;

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getQuizType() {
		return quizType;
	}
	public void setQuizType(int quizType) {
		this.quizType = quizType;
	}
	public String getLifePilotAnswersWithDelimeters() {
		return lifePilotAnswersWithDelimeters;
	}
	public void setLifePilotAnswersWithDelimeters(String lifePilotAnswersWithDelimeters) {
		this.lifePilotAnswersWithDelimeters = lifePilotAnswersWithDelimeters;
	}
}
