package com.eap.lifepilot.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.utils.EAPConstants;

public class QuizIntroActivity extends BaseActivity{

	private int selectedQuiz;
	private TextView txt_quiz_intro_expanded_direction;
	private TextView txt_quiz_intro_direction;
	private TextView txt_quiz_intro_instruction;
	
	private Button btn_quiz_intro_take_quiz;
	
	private String title;

	private View headerView;
	private TextView txt_header;
	private ImageView img_header;
	private View footerView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_intro);

		initComponents();
		prepareViews();
		setListeners();
	}
	
	@Override
	public void initComponents() {
		selectedQuiz = getIntent().getIntExtra(EAPConstants.INTENT_SELECTED_QUIZ, EAPConstants.QUIZ_LIFE_PILOT);
		txt_quiz_intro_direction = (TextView) findViewById(R.id.txt_quiz_intro_direction);
		txt_quiz_intro_instruction = (TextView) findViewById(R.id.txt_quiz_intro_instruction);
		txt_quiz_intro_expanded_direction = (TextView) findViewById(R.id.txt_quiz_intro_expanded_direction);
		btn_quiz_intro_take_quiz = (Button) findViewById(R.id.btn_quiz_intro_take_quiz);
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
	}

	@Override
	public void prepareViews() {
		setFooter(this,footerView);
		switch (selectedQuiz) {
		case EAPConstants.QUIZ_DEPRESSION:
			title = getString(R.string.my_depression);
			txt_quiz_intro_direction.setText(Html.fromHtml(getString(R.string.depression_directions)));
			txt_quiz_intro_instruction.setText(Html.fromHtml(getString(R.string.depression_instruction)));
			txt_quiz_intro_expanded_direction.setText(Html.fromHtml(getString(R.string.depression_expanded_directions)));
			break;
			
		case EAPConstants.QUIZ_RELATIONSHIP:
			title = getString(R.string.my_relation);
			
			txt_quiz_intro_direction.setText(Html.fromHtml(getString(R.string.relationship_directions)));
			txt_quiz_intro_instruction.setText(Html.fromHtml(getString(R.string.relationship_instruction)));
			txt_quiz_intro_expanded_direction.setText(Html.fromHtml(getString(R.string.relationship_expanded_directions)));
			break;
			
		case EAPConstants.QUIZ_LIFE_PILOT:
			title = getString(R.string.my_eap_services);
			
			txt_quiz_intro_direction.setText(Html.fromHtml(getString(R.string.life_pilot_directions)));
			txt_quiz_intro_instruction.setText(Html.fromHtml(getString(R.string.life_pilot_instruction)));
			txt_quiz_intro_expanded_direction.setText(Html.fromHtml(getString(R.string.life_pilot_expanded_directions)));
			break;
			
		case EAPConstants.QUIZ_ALCOHOL:
			title = getString(R.string.alcohol);
			
			txt_quiz_intro_direction.setText(Html.fromHtml(getString(R.string.alcohol_directions)));
			txt_quiz_intro_instruction.setText(Html.fromHtml(getString(R.string.alcohol_instruction)));
			txt_quiz_intro_expanded_direction.setText(Html.fromHtml(getString(R.string.alcohol_expanded_directions)));
			break;

		default:
			break;
		}
		
		txt_header.setText(title);
	}

	@Override
	public void setListeners() {
		btn_quiz_intro_take_quiz.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(QuizIntroActivity.this, QuizActivity.class);
				intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, selectedQuiz);
				intent.putExtra(EAPConstants.INTENT_KEY_TITLE, title);
				startActivity(intent);
			}
		});
	}

	
}
