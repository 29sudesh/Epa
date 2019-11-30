package com.eap.lifepilot.entities;

import java.util.ArrayList;

public class Quiz {

	private String question;
	private int answer = -1;
	private ArrayList<Option> options;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public ArrayList<Option> getOptions() {
		return options;
	}
	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public int getAnswer() {
		return answer;
	}
}
