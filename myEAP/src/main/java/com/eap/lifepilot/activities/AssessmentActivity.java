package com.eap.lifepilot.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.utils.EAPConstants;

/**
 * Activity that shows different assessment options
 * 
 * @author Gateway Technolabs Pvt. Ltd.
 *
 */
public class AssessmentActivity extends BaseActivity {

	private View headerView;
	private View footerView;

	private TextView txt_header;
	
	private Button btn_assessment_depression;
	private Button btn_assessment_relation;
	private Button btn_assessment_life_pilot;
	private Button btn_assessment_alcohol;
	private Button btn_assessment_list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assessment);

		initComponents();
		prepareViews();
		setListeners();
	}

	@Override
	public void initComponents() {
		headerView = (View) findViewById(R.id.header);
		footerView = (View) findViewById(R.id.footer);
		
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		btn_assessment_depression = (Button) findViewById(R.id.btn_assessment_depression);
		btn_assessment_relation = (Button) findViewById(R.id.btn_assessment_relation);
		btn_assessment_life_pilot = (Button) findViewById(R.id.btn_assessment_life_pilot);
		btn_assessment_alcohol = (Button) findViewById(R.id.btn_assessment_alcohol);
		btn_assessment_list = (Button) findViewById(R.id.btn_assessment_list);
	}

	@Override
	public void prepareViews() {
		setFooter(this,footerView);
		txt_header.setText(R.string.assesssments);
	}

	@Override
	public void setListeners() {
//		btn_assessment_depression.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(AssessmentActivity.this, QuizIntroActivity.class);
//				intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, EAPConstants.QUIZ_DEPRESSION);
//				startActivity(intent);
//			}
//		});
//
//		btn_assessment_relation.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(AssessmentActivity.this, QuizIntroActivity.class);
//				intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, EAPConstants.QUIZ_RELATIONSHIP);
//				startActivity(intent);
//			}
//		});
//
//		btn_assessment_life_pilot.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(AssessmentActivity.this, QuizIntroActivity.class);
//				intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, EAPConstants.QUIZ_LIFE_PILOT);
//				startActivity(intent);
//			}
//		});
//
//		btn_assessment_alcohol.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(AssessmentActivity.this, QuizIntroActivity.class);
//				intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, EAPConstants.QUIZ_ALCOHOL);
//				startActivity(intent);
//			}
//		});
//
//		btn_assessment_list.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(AssessmentActivity.this, MyAssessmentsListActivity.class);
//				startActivity(intent);
//			}
//		});
	}

}
