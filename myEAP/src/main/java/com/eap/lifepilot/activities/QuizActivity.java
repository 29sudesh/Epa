package com.eap.lifepilot.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.data.AlcoholQuizData;
import com.eap.lifepilot.data.DepressionQuizData;
import com.eap.lifepilot.data.LifePilotQuizData;
import com.eap.lifepilot.data.RelationshipQuizData;
import com.eap.lifepilot.entities.Quiz;
import com.eap.lifepilot.fragments.NewQuizFragment;
import com.eap.lifepilot.fragments.QuizFragment;
import com.eap.lifepilot.utils.EAPConstants;

public class QuizActivity extends BaseActivity {

	public int quizType;
	private LinearLayout lnr_activity_quiz;

	private View headerView;
	private TextView txt_header;
	private ImageView img_header;
	private View footerView;
	public ArrayList<Quiz> quizData;
	private String title;
	public int QUIZ_ALCOHOL_QUESTION_2_SCORE = 0;
	public int QUIZ_ALCOHOL_QUESTION_3_SCORE = 0;
	
	public String[] lifePilotAnswers = {"1","1","1","1","1","1","1","1","1","1","1","1","1","1"}; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);

		initComponents();
		prepareViews();
		setListeners();
	}

	@Override
	public void initComponents() {
		quizType = getIntent().getIntExtra(EAPConstants.INTENT_SELECTED_QUIZ, EAPConstants.QUIZ_DEPRESSION);
		title = getIntent().getStringExtra(EAPConstants.INTENT_KEY_TITLE);
		lnr_activity_quiz = (LinearLayout) findViewById(R.id.lnr_activity_quiz);
		headerView = (View) findViewById(R.id.header);
		footerView = (View) findViewById(R.id.footer);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		img_header = headerView.findViewById(R.id.img_header);
		img_header.setImageResource(R.drawable.ic_back);
		img_header.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		switch (quizType) {
		case EAPConstants.QUIZ_DEPRESSION:
			quizData = new DepressionQuizData(this).getDepressionQuiz();
			break;
		case EAPConstants.QUIZ_RELATIONSHIP:
			quizData = new RelationshipQuizData(this).getRelationshipQuiz();
			break;
		case EAPConstants.QUIZ_LIFE_PILOT:
			quizData = new LifePilotQuizData(this).getLifePilotQuiz();
			break;
		case EAPConstants.QUIZ_ALCOHOL:
			quizData = new AlcoholQuizData(this).getAlcoholQuiz();
			break;
		default:
			quizData = new DepressionQuizData(this).getDepressionQuiz();
			break;
		}

	}

	@Override
	public void prepareViews() {
		setFooter(this,footerView);
		txt_header.setText(title);
		loadQuizFragment(0,0);
	}

	@Override
	public void setListeners() {

	}

	public void loadQuizFragment(int questionNo, int score) {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		lnr_activity_quiz.removeAllViews();
		Bundle bundle = new Bundle();
		bundle.putInt(EAPConstants.INTENT_KEY_QUESTION_NO, questionNo);
		bundle.putInt(EAPConstants.INTENT_KEY_SCORE, score);
//		QuizFragment quizFragment = new QuizFragment();
		NewQuizFragment quizFragment = new NewQuizFragment();
		quizFragment.setArguments(bundle);
		fragmentTransaction.replace(R.id.lnr_activity_quiz, quizFragment);
		if (questionNo != 0) {
			fragmentTransaction.addToBackStack("");
		}
		fragmentTransaction.commit();
	}

}
