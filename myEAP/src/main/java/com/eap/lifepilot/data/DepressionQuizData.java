package com.eap.lifepilot.data;

import java.util.ArrayList;

import android.content.Context;

import com.eap.lifepilot.R;
import com.eap.lifepilot.entities.Option;
import com.eap.lifepilot.entities.Quiz;

public class DepressionQuizData {

	private ArrayList<Quiz> depressionQuiz;

	public DepressionQuizData(Context context) {
		depressionQuiz = new ArrayList<Quiz>();
		
		Quiz question_1 = new Quiz();
		question_1.setQuestion(context.getString(R.string.depression_q1));
		question_1.setOptions(getOptionForDepression(context));
		
		Quiz question_2 = new Quiz();
		question_2.setQuestion(context.getString(R.string.depression_q2));
		question_2.setOptions(getOptionForDepression(context));
		
		Quiz question_3 = new Quiz();
		question_3.setQuestion(context.getString(R.string.depression_q3));
		question_3.setOptions(getOptionForDepression(context));
		
		Quiz question_4 = new Quiz();
		question_4.setQuestion(context.getString(R.string.depression_q4));
		question_4.setOptions(getOptionForDepression(context));
		
		Quiz question_5 = new Quiz();
		question_5.setQuestion(context.getString(R.string.depression_q5));
		question_5.setOptions(getOptionForDepression(context));
		
		Quiz question_6 = new Quiz();
		question_6.setQuestion(context.getString(R.string.depression_q6));
		question_6.setOptions(getOptionForDepression(context));
		
		Quiz question_7 = new Quiz();
		question_7.setQuestion(context.getString(R.string.depression_q7));
		question_7.setOptions(getOptionForDepression(context));
		
		Quiz question_8 = new Quiz();
		question_8.setQuestion(context.getString(R.string.depression_q8));
		question_8.setOptions(getOptionForDepression(context));
		
		Quiz question_9 = new Quiz();
		question_9.setQuestion(context.getString(R.string.depression_q9));
		question_9.setOptions(getOptionForDepression(context));
		
		depressionQuiz.add(question_1);
		depressionQuiz.add(question_2);
		depressionQuiz.add(question_3);
		depressionQuiz.add(question_4);
		depressionQuiz.add(question_5);
		depressionQuiz.add(question_6);
		depressionQuiz.add(question_7);
		depressionQuiz.add(question_8);
		depressionQuiz.add(question_9);
		
		
	}
	
	// As all questions of depression have same options
	private ArrayList<Option> getOptionForDepression(Context context) {
		ArrayList<Option> options = new ArrayList<Option>();
		
		Option option1 = new Option();
		option1.setDescription(context.getString(R.string.depression_option_1));
		option1.setWeight(0);
		
		Option option2 = new Option();
		option2.setDescription(context.getString(R.string.depression_option_2));
		option2.setWeight(1);
		
		Option option3 = new Option();
		option3.setDescription(context.getString(R.string.depression_option_3));
		option3.setWeight(2);
		
		Option option4 = new Option();
		option4.setDescription(context.getString(R.string.depression_option_4));
		option4.setWeight(3);
		
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		
		return options;
	}
	public ArrayList<Quiz> getDepressionQuiz() {
		return depressionQuiz;
	}
	
}
