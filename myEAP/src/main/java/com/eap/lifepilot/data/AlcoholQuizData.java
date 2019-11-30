package com.eap.lifepilot.data;

import java.util.ArrayList;

import android.content.Context;

import com.eap.lifepilot.R;
import com.eap.lifepilot.entities.Option;
import com.eap.lifepilot.entities.Quiz;

public class AlcoholQuizData {

	private ArrayList<Quiz> alcoholQuiz;

	public AlcoholQuizData(Context context) {
		alcoholQuiz = new ArrayList<Quiz>();

		Quiz question_1 = new Quiz();
		question_1.setQuestion(context.getString(R.string.alcohol_q1));
		question_1.setOptions(getOption1ForAlcohol(context));

		Quiz question_2 = new Quiz();
		question_2.setQuestion(context.getString(R.string.alcohol_q2));
		question_2.setOptions(getOption2ForAlcohol(context));

		Quiz question_3 = new Quiz();
		question_3.setQuestion(context.getString(R.string.alcohol_q3));
		question_3.setOptions(getOption3ForAlcohol(context));
		
		Quiz question_4 = new Quiz();
		question_4.setQuestion(context.getString(R.string.alcohol_q4));
		question_4.setOptions(getOption4ForAlcohol(context));
		
		Quiz question_5 = new Quiz();
		question_5.setQuestion(context.getString(R.string.alcohol_q5));
		question_5.setOptions(getOption5ForAlcohol(context));
		
		Quiz question_6 = new Quiz();
		question_6.setQuestion(context.getString(R.string.alcohol_q6));
		question_6.setOptions(getOption6ForAlcohol(context));
		
		Quiz question_7 = new Quiz();
		question_7.setQuestion(context.getString(R.string.alcohol_q7));
		question_7.setOptions(getOption7ForAlcohol(context));
		
		Quiz question_8 = new Quiz();
		question_8.setQuestion(context.getString(R.string.alcohol_q8));
		question_8.setOptions(getOption8ForAlcohol(context));
		
		Quiz question_9 = new Quiz();
		question_9.setQuestion(context.getString(R.string.alcohol_q9));
		question_9.setOptions(getOption9ForAlcohol(context));
		
		Quiz question_10 = new Quiz();
		question_10.setQuestion(context.getString(R.string.alcohol_q10));
		question_10.setOptions(getOption10ForAlcohol(context));
		
		alcoholQuiz.add(question_1);
		alcoholQuiz.add(question_2);
		alcoholQuiz.add(question_3);
		alcoholQuiz.add(question_4);
		alcoholQuiz.add(question_5);
		alcoholQuiz.add(question_6);
		alcoholQuiz.add(question_7);
		alcoholQuiz.add(question_8);
		alcoholQuiz.add(question_9);
		alcoholQuiz.add(question_10);
		
	}
	
	// As all questions of alcohol have different options
	private ArrayList<Option> getOption1ForAlcohol(Context context) {
		ArrayList<Option> options = new ArrayList<Option>();
		
		Option option1 = new Option();
		option1.setDescription(context.getString(R.string.alcohol_option_1));
		option1.setWeight(0);
		
		Option option2 = new Option();
		option2.setDescription(context.getString(R.string.alcohol_option_2));
		option2.setWeight(1);
		
		Option option3 = new Option();
		option3.setDescription(context.getString(R.string.alcohol_option_3));
		option3.setWeight(2);
		
		Option option4 = new Option();
		option4.setDescription(context.getString(R.string.alcohol_option_4));
		option4.setWeight(3);
		
		Option option5 = new Option();
		option5.setDescription(context.getString(R.string.alcohol_option_5));
		option5.setWeight(4);
		
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		options.add(option5);
		
		return options;
	}
	
	private ArrayList<Option> getOption2ForAlcohol(Context context) {
		ArrayList<Option> options = new ArrayList<Option>();
		
		Option option1 = new Option();
		option1.setDescription(context.getString(R.string.alcohol_option_6));
		option1.setWeight(0);
		
		Option option2 = new Option();
		option2.setDescription(context.getString(R.string.alcohol_option_7));
		option2.setWeight(1);
		
		Option option3 = new Option();
		option3.setDescription(context.getString(R.string.alcohol_option_8));
		option3.setWeight(2);
		
		Option option4 = new Option();
		option4.setDescription(context.getString(R.string.alcohol_option_9));
		option4.setWeight(3);
		
		Option option5 = new Option();
		option5.setDescription(context.getString(R.string.alcohol_option_10));
		option5.setWeight(4);
		
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		options.add(option5);
		
		return options;
	}
	
	private ArrayList<Option> getOption3ForAlcohol(Context context) {
		ArrayList<Option> options = new ArrayList<Option>();
		
		Option option1 = new Option();
		option1.setDescription(context.getString(R.string.alcohol_option_1));
		option1.setWeight(0);
		
		Option option2 = new Option();
		option2.setDescription(context.getString(R.string.alcohol_option_11));
		option2.setWeight(1);
		
		Option option3 = new Option();
		option3.setDescription(context.getString(R.string.alcohol_option_12));
		option3.setWeight(2);
		
		Option option4 = new Option();
		option4.setDescription(context.getString(R.string.alcohol_option_13));
		option4.setWeight(3);
		
		Option option5 = new Option();
		option5.setDescription(context.getString(R.string.alcohol_option_14));
		option5.setWeight(4);
		
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		options.add(option5);
		
		return options;
	}
	
	private ArrayList<Option> getOption4ForAlcohol(Context context) {
		ArrayList<Option> options = new ArrayList<Option>();
		
		Option option1 = new Option();
		option1.setDescription(context.getString(R.string.alcohol_option_1));
		option1.setWeight(0);
		
		Option option2 = new Option();
		option2.setDescription(context.getString(R.string.alcohol_option_11));
		option2.setWeight(1);
		
		Option option3 = new Option();
		option3.setDescription(context.getString(R.string.alcohol_option_12));
		option3.setWeight(2);
		
		Option option4 = new Option();
		option4.setDescription(context.getString(R.string.alcohol_option_13));
		option4.setWeight(3);
		
		Option option5 = new Option();
		option5.setDescription(context.getString(R.string.alcohol_option_14));
		option5.setWeight(4);
		
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		options.add(option5);
		
		return options;
	}
	
	private ArrayList<Option> getOption5ForAlcohol(Context context) {
		ArrayList<Option> options = new ArrayList<Option>();
		
		Option option1 = new Option();
		option1.setDescription(context.getString(R.string.alcohol_option_1));
		option1.setWeight(0);
		
		Option option2 = new Option();
		option2.setDescription(context.getString(R.string.alcohol_option_11));
		option2.setWeight(1);
		
		Option option3 = new Option();
		option3.setDescription(context.getString(R.string.alcohol_option_12));
		option3.setWeight(2);
		
		Option option4 = new Option();
		option4.setDescription(context.getString(R.string.alcohol_option_13));
		option4.setWeight(3);
		
		Option option5 = new Option();
		option5.setDescription(context.getString(R.string.alcohol_option_14));
		option5.setWeight(4);
		
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		options.add(option5);
		
		return options;
	}
	
	private ArrayList<Option> getOption6ForAlcohol(Context context) {
		ArrayList<Option> options = new ArrayList<Option>();
		
		Option option1 = new Option();
		option1.setDescription(context.getString(R.string.alcohol_option_1));
		option1.setWeight(0);
		
		Option option2 = new Option();
		option2.setDescription(context.getString(R.string.alcohol_option_11));
		option2.setWeight(1);
		
		Option option3 = new Option();
		option3.setDescription(context.getString(R.string.alcohol_option_12));
		option3.setWeight(2);
		
		Option option4 = new Option();
		option4.setDescription(context.getString(R.string.alcohol_option_13));
		option4.setWeight(3);
		
		Option option5 = new Option();
		option5.setDescription(context.getString(R.string.alcohol_option_14));
		option5.setWeight(4);
		
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		options.add(option5);
		
		return options;
	}
	
	private ArrayList<Option> getOption7ForAlcohol(Context context) {
		ArrayList<Option> options = new ArrayList<Option>();
		
		Option option1 = new Option();
		option1.setDescription(context.getString(R.string.alcohol_option_1));
		option1.setWeight(0);
		
		Option option2 = new Option();
		option2.setDescription(context.getString(R.string.alcohol_option_11));
		option2.setWeight(1);
		
		Option option3 = new Option();
		option3.setDescription(context.getString(R.string.alcohol_option_12));
		option3.setWeight(2);
		
		Option option4 = new Option();
		option4.setDescription(context.getString(R.string.alcohol_option_13));
		option4.setWeight(3);
		
		Option option5 = new Option();
		option5.setDescription(context.getString(R.string.alcohol_option_14));
		option5.setWeight(4);
		
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		options.add(option5);
		
		return options;
	}
	
	private ArrayList<Option> getOption8ForAlcohol(Context context) {
		ArrayList<Option> options = new ArrayList<Option>();
		
		Option option1 = new Option();
		option1.setDescription(context.getString(R.string.alcohol_option_1));
		option1.setWeight(0);
		
		Option option2 = new Option();
		option2.setDescription(context.getString(R.string.alcohol_option_11));
		option2.setWeight(1);
		
		Option option3 = new Option();
		option3.setDescription(context.getString(R.string.alcohol_option_12));
		option3.setWeight(2);
		
		Option option4 = new Option();
		option4.setDescription(context.getString(R.string.alcohol_option_13));
		option4.setWeight(3);
		
		Option option5 = new Option();
		option5.setDescription(context.getString(R.string.alcohol_option_14));
		option5.setWeight(4);
		
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		options.add(option5);
		
		return options;
	}
	
	private ArrayList<Option> getOption9ForAlcohol(Context context) {
		ArrayList<Option> options = new ArrayList<Option>();
		
		Option option1 = new Option();
		option1.setDescription(context.getString(R.string.alcohol_option_15));
		option1.setWeight(0);
		
		Option option2 = new Option();
		option2.setDescription(context.getString(R.string.alcohol_option_16));
		option2.setWeight(2);
		
		Option option3 = new Option();
		option3.setDescription(context.getString(R.string.alcohol_option_17));
		option3.setWeight(4);
		
		options.add(option1);
		options.add(option2);
		options.add(option3);
		
		return options;
	}
	
	private ArrayList<Option> getOption10ForAlcohol(Context context) {
		ArrayList<Option> options = new ArrayList<Option>();
		
		Option option1 = new Option();
		option1.setDescription(context.getString(R.string.alcohol_option_15));
		option1.setWeight(0);
		
		Option option2 = new Option();
		option2.setDescription(context.getString(R.string.alcohol_option_16));
		option2.setWeight(2);
		
		Option option3 = new Option();
		option3.setDescription(context.getString(R.string.alcohol_option_17));
		option3.setWeight(4);
		
		options.add(option1);
		options.add(option2);
		options.add(option3);
		
		return options;
	}
	
	public ArrayList<Quiz> getAlcoholQuiz() {
		return alcoholQuiz;
	}
	
}
