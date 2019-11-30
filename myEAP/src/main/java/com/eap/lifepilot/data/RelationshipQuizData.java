package com.eap.lifepilot.data;

import java.util.ArrayList;

import android.content.Context;

import com.eap.lifepilot.R;
import com.eap.lifepilot.entities.Option;
import com.eap.lifepilot.entities.Quiz;

public class RelationshipQuizData {

	private ArrayList<Quiz> relationshipQuiz;

	public RelationshipQuizData(Context context) {
		
		relationshipQuiz = new ArrayList<Quiz>();
		
		Quiz question_1 = new Quiz();
		question_1.setQuestion(context.getString(R.string.relationship_q1));
		question_1.setOptions(getOptionForRelationship(context));
		
		Quiz question_2 = new Quiz();
		question_2.setQuestion(context.getString(R.string.relationship_q2));
		question_2.setOptions(getOptionForRelationship(context));
		
		Quiz question_3 = new Quiz();
		question_3.setQuestion(context.getString(R.string.relationship_q3));
		question_3.setOptions(getOptionForRelationship(context));
		
		Quiz question_4 = new Quiz();
		question_4.setQuestion(context.getString(R.string.relationship_q4));
		question_4.setOptions(getOptionForRelationship(context));
		
		Quiz question_5 = new Quiz();
		question_5.setQuestion(context.getString(R.string.relationship_q5));
		question_5.setOptions(getOptionForRelationship(context));
		
		Quiz question_6 = new Quiz();
		question_6.setQuestion(context.getString(R.string.relationship_q6));
		question_6.setOptions(getOptionForRelationship(context));
		
		Quiz question_7 = new Quiz();
		question_7.setQuestion(context.getString(R.string.relationship_q7));
		question_7.setOptions(getOptionForRelationship(context));
		
		Quiz question_8 = new Quiz();
		question_8.setQuestion(context.getString(R.string.relationship_q8));
		question_8.setOptions(getOptionForRelationship(context));
		
		Quiz question_9 = new Quiz();
		question_9.setQuestion(context.getString(R.string.relationship_q9));
		question_9.setOptions(getOptionForRelationship(context));
		
		Quiz question_10 = new Quiz();
		question_10.setQuestion(context.getString(R.string.relationship_q10));
		question_10.setOptions(getOptionForRelationship(context));
		
		Quiz question_11 = new Quiz();
		question_11.setQuestion(context.getString(R.string.relationship_q11));
		question_11.setOptions(getOptionForRelationship(context));
		
		relationshipQuiz.add(question_1);
		relationshipQuiz.add(question_2);
		relationshipQuiz.add(question_3);
		relationshipQuiz.add(question_4);
		relationshipQuiz.add(question_5);
		relationshipQuiz.add(question_6);
		relationshipQuiz.add(question_7);
		relationshipQuiz.add(question_8);
		relationshipQuiz.add(question_9);
		relationshipQuiz.add(question_10);
		relationshipQuiz.add(question_11);
		
		
	}
	
	// As all questions of relationship have same options
	private ArrayList<Option> getOptionForRelationship(Context context) {
		ArrayList<Option> options = new ArrayList<Option>();
		
		Option option1 = new Option();
		option1.setDescription(context.getString(R.string.relationship_option_1));
		option1.setWeight(1);
		
		Option option2 = new Option();
		option2.setDescription(context.getString(R.string.relationship_option_2));
		option2.setWeight(2);
		
		Option option3 = new Option();
		option3.setDescription(context.getString(R.string.relationship_option_3));
		option3.setWeight(3);
		
		Option option4 = new Option();
		option4.setDescription(context.getString(R.string.relationship_option_4));
		option4.setWeight(4);
		
		Option option5 = new Option();
		option5.setDescription(context.getString(R.string.relationship_option_5));
		option5.setWeight(5);
		
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		options.add(option5);
		
		return options;
	}
	public ArrayList<Quiz> getRelationshipQuiz() {
		return relationshipQuiz;
	}
	
}
