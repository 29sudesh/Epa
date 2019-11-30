package com.eap.lifepilot.fragments;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.BaseFragment;
import com.eap.lifepilot.R;
import com.eap.lifepilot.activities.LifePilotQuizResultActivity;
import com.eap.lifepilot.activities.QuizActivity;
import com.eap.lifepilot.activities.QuizResultActivity;
import com.eap.lifepilot.entities.Option;
import com.eap.lifepilot.entities.Quiz;
import com.eap.lifepilot.utils.EAPConstants;


public class QuizFragment extends BaseFragment{

	private View view;
	private QuizActivity quizActivity;

	private TextView txt_quiz_question;
	private TextView txt_quiz_question_counter;
	
	private Button btn_quiz_next;
	
	private Quiz selectedQuiz;
	private ArrayList<Option> options;
	
	RadioButton rdbOption_1;
	RadioButton rdbOption_2;
	RadioButton rdbOption_3;
	RadioButton rdbOption_4;
	RadioButton rdbOption_5;
	
	private RadioGroup rdg_quiz_options;
	
	private int questionNo;
	private int currentScore;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_quiz, container, false);
		initComponents();
		prepareViews();
		setListeners();

		return view;
	}
	
	@Override
	public void initComponents() {
		quizActivity = (QuizActivity) getActivity();
		try {
			questionNo = getArguments().getInt(EAPConstants.INTENT_KEY_QUESTION_NO);
			currentScore = getArguments().getInt(EAPConstants.INTENT_KEY_SCORE);
		} catch (Exception e) {
			questionNo = 0;
		}

		selectedQuiz = quizActivity.quizData.get(questionNo);
		options = selectedQuiz.getOptions();
		dotsCount = quizActivity.quizData.size();

		txt_quiz_question = (TextView) view.findViewById(R.id.txt_quiz_question);
		txt_quiz_question_counter = (TextView) view.findViewById(R.id.txt_quiz_question_counter);
		
		btn_quiz_next = (Button) view.findViewById(R.id.btn_quiz_next);

		rdg_quiz_options = (RadioGroup) view.findViewById(R.id.rdg_quiz_options);

		linearLayout=(LinearLayout)view.findViewById(R.id.viewPagerCountDots);

		rdbOption_1 = (RadioButton) view.findViewById(R.id.rdb_quiz_option_one);
		rdbOption_2 = (RadioButton) view.findViewById(R.id.rdb_quiz_option_two);
		rdbOption_3 = (RadioButton) view.findViewById(R.id.rdb_quiz_option_three);
		rdbOption_4 = (RadioButton) view.findViewById(R.id.rdb_quiz_option_four);
		rdbOption_5 = (RadioButton) view.findViewById(R.id.rdb_quiz_option_five);
		


	}


	@SuppressLint("StringFormatMatches")
	@Override
	public void prepareViews() {
		txt_quiz_question.setText(selectedQuiz.getQuestion());
		drawPageSelectionIndicators(questionNo);
		txt_quiz_question_counter.setText(String.format(getString(R.string.question_number_of_total), (questionNo + 1), quizActivity.quizData.size()));
		
		
		rdbOption_1.setText(options.get(0).getDescription());
		rdbOption_2.setText(options.get(1).getDescription());
		
		if(options.size() == 5) {
			rdbOption_3.setText(options.get(2).getDescription());
			rdbOption_3.setVisibility(View.VISIBLE);
			
			rdbOption_4.setText(options.get(3).getDescription());
			rdbOption_4.setVisibility(View.VISIBLE);
			
			rdbOption_5.setText(options.get(4).getDescription());
			rdbOption_5.setVisibility(View.VISIBLE);
		} else if(options.size() == 4) {
			rdbOption_3.setText(options.get(2).getDescription());
			rdbOption_3.setVisibility(View.VISIBLE);
			
			rdbOption_4.setText(options.get(3).getDescription());
			rdbOption_4.setVisibility(View.VISIBLE);
		} else if(options.size() == 3) {
			rdbOption_3.setText(options.get(2).getDescription());
			rdbOption_3.setVisibility(View.VISIBLE);
		}
		
		
	}

	@Override
	public void setListeners() {
		btn_quiz_next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(rdg_quiz_options.getCheckedRadioButtonId()==-1) {
					BaseActivity.showValidationDialog(getActivity(), getString(R.string.please_select_an_option));
					return;
				}
//				int newScore = currentScore + getSelectedAnswerWeight();
//
//				if(questionNo < quizActivity.quizData.size()-1) {
//					conditionsAppliedOnNextButtonPressed(questionNo+1, newScore);
//				} else {
//					finishQuizAndShowResult(newScore);
//				}
			}
		});
		
		rdg_quiz_options.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				conditionsAppliedOnRadioButtonSelectionChanged(checkedId);
				Log.e("check Score : "," cr : "+currentScore+"  ws : "+ getSelectedAnswerWeight());
				int newScore = currentScore + getSelectedAnswerWeight();
				if(questionNo < quizActivity.quizData.size()-1) {
					conditionsAppliedOnNextButtonPressed(questionNo+1, newScore);
				} else {
					finishQuizAndShowResult(newScore);
				}
			}
		});
	}
	
	private int getSelectedAnswerWeight() {
		int radioButtonID = rdg_quiz_options.getCheckedRadioButtonId();
		View radioButton = rdg_quiz_options.findViewById(radioButtonID);
		int index = rdg_quiz_options.indexOfChild(radioButton);
		Log.e("size w : "," in : "+index+"  get : "+options.get(index).getWeight());
		return options.get(index).getWeight();
	}
	
	private void finishQuizAndShowResult(int newScore) {
		if(quizActivity.quizType==EAPConstants.QUIZ_LIFE_PILOT) {
			Intent intent = new Intent(getActivity(), LifePilotQuizResultActivity.class);
			intent.putExtra(EAPConstants.INTENT_KEY_SCORE, newScore);
			intent.putExtra(EAPConstants.INTENT_KEY_LIFE_PILOT_ANSWERS, quizActivity.lifePilotAnswers);
			intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, quizActivity.quizType);
			startActivity(intent);
		} else { 
			Intent intent = new Intent(getActivity(), QuizResultActivity.class);
			intent.putExtra(EAPConstants.INTENT_KEY_SCORE, newScore);
			intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, quizActivity.quizType);
			startActivity(intent);
		}
		
		
		
	}
	
	private void conditionsAppliedOnNextButtonPressed(int nextQuestionNumber, int newScore) {
		switch (quizActivity.quizType) {
		case EAPConstants.QUIZ_DEPRESSION:
			
			break;
			
		case EAPConstants.QUIZ_RELATIONSHIP:
			break;
			
		case EAPConstants.QUIZ_LIFE_PILOT:
	
			
			break;
		case EAPConstants.QUIZ_ALCOHOL:
			if(questionNo==0 && rdbOption_1.isChecked()) {
				nextQuestionNumber = 8;
				break;
			}
			if(questionNo==1) {
				
				if(rdbOption_1.isChecked()==false) {
					quizActivity.QUIZ_ALCOHOL_QUESTION_2_SCORE = 1;
				} else {
					quizActivity.QUIZ_ALCOHOL_QUESTION_2_SCORE = 0;
				}
				break;
			}
			
			if(questionNo==2) {
				if(rdbOption_1.isChecked()==false) {
					quizActivity.QUIZ_ALCOHOL_QUESTION_3_SCORE = 1;
				} else {
					quizActivity.QUIZ_ALCOHOL_QUESTION_3_SCORE = 0;
				}
				if((quizActivity.QUIZ_ALCOHOL_QUESTION_2_SCORE + quizActivity.QUIZ_ALCOHOL_QUESTION_3_SCORE) == 0) {
					nextQuestionNumber = 8;
				}
			}
			break;

		default:
			
			break;
		}
		quizActivity.loadQuizFragment(nextQuestionNumber, newScore);
	}
	
	private void conditionsAppliedOnRadioButtonSelectionChanged(int checkId) {
		
		switch (quizActivity.quizType) {
		case EAPConstants.QUIZ_DEPRESSION:
			if(questionNo==8 && checkId!=R.id.rdb_quiz_option_one) {
				showDepressionDialog();
			}
			break;
			
		case EAPConstants.QUIZ_RELATIONSHIP:
			break;
			
		case EAPConstants.QUIZ_LIFE_PILOT:
			if(checkId==R.id.rdb_quiz_option_one) {
				quizActivity.lifePilotAnswers[questionNo] = "1";
			} else {
				quizActivity.lifePilotAnswers[questionNo] = "0";
			}
			break;
		case EAPConstants.QUIZ_ALCOHOL:
			break;

		default:
			break;
		}
	}
	
	
	
	private void showDepressionDialog() {
		Bundle bundle = new Bundle();
		bundle.putString(EAPConstants.INTENT_KEY_DIALOG_CONTENT, getString(R.string.depression_dialog));
		DepressionAlertDialogFragment alertDialogFragment = new DepressionAlertDialogFragment();
		alertDialogFragment.setArguments(bundle);
		alertDialogFragment.show(getChildFragmentManager(), "Depression Dialog");
	}


	/////////

	private int dotsCount=18;    //No of tabs or images
	private ImageView[] dots;
	LinearLayout linearLayout;

	private void drawPageSelectionIndicators(int mPosition){
		if(linearLayout!=null) {
			linearLayout.removeAllViews();
		}
//		linearLayout=(LinearLayout)findViewById(R.id.viewPagerCountDots);
		dots = new ImageView[dotsCount];
		for (int i = 0; i < dotsCount; i++) {
			dots[i] = new ImageView(getActivity().getApplicationContext());
			if(i==mPosition)
				dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));
			else
				dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT
			);

			params.setMargins(4, 0, 4, 0);
			linearLayout.addView(dots[i], params);
		}
	}

}
