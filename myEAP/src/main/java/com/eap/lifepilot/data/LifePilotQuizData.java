package com.eap.lifepilot.data;

import java.util.ArrayList;

import android.content.Context;

import com.eap.lifepilot.R;
import com.eap.lifepilot.entities.Option;
import com.eap.lifepilot.entities.Quiz;

public class LifePilotQuizData {

	private ArrayList<Quiz> life_pilotQuiz;

	public LifePilotQuizData(Context context) {
		
		life_pilotQuiz = new ArrayList<Quiz>();
		
		Quiz question_1 = new Quiz();
		question_1.setQuestion(context.getString(R.string.life_pilot_q1));
		question_1.setOptions(getOptionForLifePilot(context));
		
		Quiz question_2 = new Quiz();
		question_2.setQuestion(context.getString(R.string.life_pilot_q2));
		question_2.setOptions(getOptionForLifePilot(context));
		
		Quiz question_3 = new Quiz();
		question_3.setQuestion(context.getString(R.string.life_pilot_q3));
		question_3.setOptions(getOptionForLifePilot(context));
		
		Quiz question_4 = new Quiz();
		question_4.setQuestion(context.getString(R.string.life_pilot_q4));
		question_4.setOptions(getOptionForLifePilot(context));
		
		Quiz question_5 = new Quiz();
		question_5.setQuestion(context.getString(R.string.life_pilot_q5));
		question_5.setOptions(getOptionForLifePilot(context));
		
		Quiz question_6 = new Quiz();
		question_6.setQuestion(context.getString(R.string.life_pilot_q6));
		question_6.setOptions(getOptionForLifePilot(context));
		
		Quiz question_7 = new Quiz();
		question_7.setQuestion(context.getString(R.string.life_pilot_q7));
		question_7.setOptions(getOptionForLifePilot(context));
		
		Quiz question_8 = new Quiz();
		question_8.setQuestion(context.getString(R.string.life_pilot_q8));
		question_8.setOptions(getOptionForLifePilot(context));
		
		Quiz question_9 = new Quiz();
		question_9.setQuestion(context.getString(R.string.life_pilot_q9));
		question_9.setOptions(getOptionForLifePilot(context));
		
		Quiz question_10 = new Quiz();
		question_10.setQuestion(context.getString(R.string.life_pilot_q10));
		question_10.setOptions(getOptionForLifePilot(context));
		
		Quiz question_11 = new Quiz();
		question_11.setQuestion(context.getString(R.string.life_pilot_q11));
		question_11.setOptions(getOptionForLifePilot(context));
		
		Quiz question_12 = new Quiz();
		question_12.setQuestion(context.getString(R.string.life_pilot_q12));
		question_12.setOptions(getOptionForLifePilot(context));
		
//		Quiz question_13 = new Quiz();
//		question_13.setQuestion(context.getString(R.string.life_pilot_q13));
//		question_13.setOptions(getOptionForLifePilot(context));
//
//		Quiz question_14 = new Quiz();
//		question_14.setQuestion(context.getString(R.string.life_pilot_q14));
//		question_14.setOptions(getOptionForLifePilot(context));
		
		
		life_pilotQuiz.add(question_1);
		life_pilotQuiz.add(question_2);
		life_pilotQuiz.add(question_3);
		life_pilotQuiz.add(question_4);
		life_pilotQuiz.add(question_5);
		life_pilotQuiz.add(question_6);
		life_pilotQuiz.add(question_7);
		life_pilotQuiz.add(question_8);
		life_pilotQuiz.add(question_9);
		life_pilotQuiz.add(question_10);
		life_pilotQuiz.add(question_11);
		life_pilotQuiz.add(question_12);
//		life_pilotQuiz.add(question_13);
//		life_pilotQuiz.add(question_14);
		
		
	}
	
	// As all questions of life_pilot have same options
	private ArrayList<Option> getOptionForLifePilot(Context context) {
		ArrayList<Option> options = new ArrayList<Option>();
		
		Option option1 = new Option();
		option1.setDescription(context.getString(R.string.life_pilot_option_1));
		option1.setWeight(0);
		
		Option option2 = new Option();
		option2.setDescription(context.getString(R.string.life_pilot_option_2));
		option2.setWeight(1);
		
		options.add(option1);
		options.add(option2);
		
		return options;
	}
	public ArrayList<Quiz> getLifePilotQuiz() {
		return life_pilotQuiz;
	}
	
}
