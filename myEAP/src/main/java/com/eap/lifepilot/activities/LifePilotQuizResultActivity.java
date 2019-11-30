package com.eap.lifepilot.activities;

import java.util.ArrayList;
import java.util.Date;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.database.AssessmentScoreTable;
import com.eap.lifepilot.entities.AssessmentScore;
import com.eap.lifepilot.fragments.QuizResultAlertDialogFragment;
import com.eap.lifepilot.fragments.QuizResultAlertDialogFragment.onQuizResultAlertListener;
import com.eap.lifepilot.utils.DetectSwipeGestureListener;
import com.eap.lifepilot.utils.EAPApplicationPreference;
import com.eap.lifepilot.utils.EAPConstants;

/**
 * The result generated after the LifePilot quiz/assessment
 * 
 * @author Gateway Technolabs Pvt. Ltd.
 *
 */

public class LifePilotQuizResultActivity extends BaseActivity{
	private int maxScore;
	private int finalScore = 0;
	private ProgressBar progressbar_quiz_result;
	private Button btn_quiz_result_finish;
	private Button btn_quiz_result_callus;
	private TextView txt_quiz_result;
	private TextView txt_quiz_result_1;
	private String title;
	public String lifePilotAnswers[];
	private ScrollView scrl_quiz_result;
	private ArrayList<String> servicesNames;
	private ArrayList<String> servicesUrls;
	private ArrayList<String> servicesNewPositions;
	private LinearLayout lnr_quiz_result;
	private TextView txt_quiz_result_legal;
	private TextView txt_quiz_result_financial;
	private TextView txt_quiz_result_homeownership;
	private TextView txt_quiz_result_counselling;
	private TextView txt_quiz_result_child_care;
	private TextView txt_quiz_result_elder_care;
	private TextView txt_quiz_result_wellness;
	private LinearLayout viewPagerCountDots;
	private QuizResultAlertDialogFragment quizResultAlertDialogFragment;
	private boolean isComingFromMyAssesmentList;
	private View headerView;
	private TextView txt_header,tv_score;
	private ImageView img_header;
	private View footerView;
	private SwipeLayout swipe;
	// This is the gesture detector compat instance.

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_result_life_pilot);
		
		initComponents();
		prepareViews();
		setListeners();
	}

	@Override	
	public void onBackPressed() {
//		if(isComingFromMyAssesmentList) {
//	overridePendingTransition(R.anim.slide_in_right,
//				R.anim.slide_in_right);
			super.onBackPressed();
//		}
	}
	@Override
	public void initComponents() {
		tv_score = findViewById(R.id.tv_score);
		viewPagerCountDots = findViewById(R.id.viewPagerCountDots);
		swipe = findViewById(R.id.swipe);
		swipe.addSwipeListener(new SwipeLayout.SwipeListener() {
			@Override
			public void onStartOpen(SwipeLayout layout) {

			}

			@Override
			public void onOpen(SwipeLayout layout) {
				onBackPressed();

			}

			@Override
			public void onStartClose(SwipeLayout layout) {

			}

			@Override
			public void onClose(SwipeLayout layout) {

			}

			@Override
			public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

			}

			@Override
			public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

			}
		});
		progressbar_quiz_result = (ProgressBar) findViewById(R.id.progressbar_quiz_result);
		btn_quiz_result_finish = (Button) findViewById(R.id.btn_quiz_result_finish);
		btn_quiz_result_callus = (Button) findViewById(R.id.btn_quiz_result_callus);

		txt_quiz_result = (TextView) findViewById(R.id.txt_quiz_result);
		txt_quiz_result_1 = (TextView) findViewById(R.id.txt_quiz_result_1);
		lnr_quiz_result = (LinearLayout) findViewById(R.id.lnr_quiz_result);
		txt_quiz_result_legal = (TextView) findViewById(R.id.txt_quiz_result_legal);
		txt_quiz_result_financial = (TextView) findViewById(R.id.txt_quiz_result_financial);
		txt_quiz_result_homeownership = (TextView) findViewById(R.id.txt_quiz_result_homeownership);
		txt_quiz_result_counselling = (TextView) findViewById(R.id.txt_quiz_result_counselling);
		txt_quiz_result_child_care = (TextView) findViewById(R.id.txt_quiz_result_child_care);
		txt_quiz_result_elder_care = (TextView) findViewById(R.id.txt_quiz_result_elder_care);
		txt_quiz_result_wellness = (TextView) findViewById(R.id.txt_quiz_result_wellness);
		maxScore = EAPConstants.QUIZ_MAX_SCORE_LIFE_PILOT;
		title = getString(R.string.life_pilot_assessment);
		isComingFromMyAssesmentList = getIntent().getBooleanExtra(EAPConstants.INTENT_KEY_FROM_MY_ASSESSMENTS_LIST_BOOLEAN, false);
		lifePilotAnswers = getIntent().getStringArrayExtra(EAPConstants.INTENT_KEY_LIFE_PILOT_ANSWERS);

		servicesNames = new ArrayList<String>();
		servicesUrls = new ArrayList<String>();
		servicesNewPositions = new ArrayList<String>();
		
		progressbar_quiz_result.setMax(maxScore);

		setServiceNamesAndFinalScore();

		quizResultAlertDialogFragment = new QuizResultAlertDialogFragment();

		headerView = (View) findViewById(R.id.header);
		footerView = (View) findViewById(R.id.footer);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		img_header = headerView.findViewById(R.id.img_header);
		img_header.setImageResource(R.drawable.ic_back);
		img_header.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LifePilotQuizResultActivity.this, HomeActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});

		drawPageSelectionIndicators(12);

	}

	private void drawPageSelectionIndicators(int mPosition) {
		if (viewPagerCountDots != null) {
			viewPagerCountDots.removeAllViews();
		}
		int dotsCount = 13;    //No of tabs or images
		ImageView[] dots = new ImageView[dotsCount];
		for (int i = 0; i < dotsCount; i++) {
			dots[i] = new ImageView(LifePilotQuizResultActivity.this);
			if (i == mPosition)
				dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));
			else
				dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT
			);

			params.setMargins(4, 0, 4, 0);
			viewPagerCountDots.addView(dots[i], params);
		}
	}


	@Override
	public void prepareViews() {
		txt_header.setText(getString(R.string.my_eap_services));
		setFooter(this,footerView);
		if(isComingFromMyAssesmentList) {
			btn_quiz_result_finish.setVisibility(View.GONE);
			viewPagerCountDots.setVisibility(View.GONE);
		}else {
			viewPagerCountDots.setVisibility(View.VISIBLE);
		}
		
		if(finalScore==0) {
			btn_quiz_result_callus.setVisibility(View.VISIBLE);
			btn_quiz_result_finish.setVisibility(View.VISIBLE);

			lnr_quiz_result.setVisibility(View.GONE);
			txt_quiz_result_1.setVisibility(View.GONE);

			txt_quiz_result.setText(R.string.quiz_result_life_pilot_no);
		} else {
			btn_quiz_result_callus.setVisibility(View.GONE);

			lnr_quiz_result.setVisibility(View.VISIBLE);
			txt_quiz_result_1.setVisibility(View.VISIBLE);

			txt_quiz_result.setText(R.string.quiz_result_life_pilot_yes);
		}


		progressbar_quiz_result.setMax(maxScore);
		progressbar_quiz_result.setProgress(finalScore);
		tv_score.setText(""+finalScore);
		
	}

	@Override
	public void setListeners() {
		btn_quiz_result_finish.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				saveQuizInfo();
				finishAndProceedToHome();
//				quizResultAlertDialogFragment.show(getSupportFragmentManager(), "Quiz Result Dialog");
			}
		});
		
		quizResultAlertDialogFragment.setQuizResultAlertListener(new onQuizResultAlertListener() {
			@Override
			public void onYesClicked() {
				saveQuizInfo();
				finishAndProceedToHome();
			}
			
			@Override
			public void onNoClicked() {
				finishAndProceedToHome();
			}
		});
		
		btn_quiz_result_callus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.eap_contact_no)));
				startActivity(intent);
			}
		});
		
		txt_quiz_result_legal.setOnClickListener(serviceClickListener);
		txt_quiz_result_financial.setOnClickListener(serviceClickListener);
		txt_quiz_result_homeownership.setOnClickListener(serviceClickListener);
		txt_quiz_result_counselling.setOnClickListener(serviceClickListener);
		txt_quiz_result_child_care.setOnClickListener(serviceClickListener);
		txt_quiz_result_elder_care.setOnClickListener(serviceClickListener);
		txt_quiz_result_wellness.setOnClickListener(serviceClickListener);
		
	}
	
	/**
	 * Storing the final score in database
	 */
	
	private void saveQuizInfo() {
		AssessmentScore assessmentScore = new AssessmentScore();
		Date date = new Date();
		
		assessmentScore.setQuizType(EAPConstants.QUIZ_LIFE_PILOT);
		assessmentScore.setScore(finalScore + "");
		assessmentScore.setDate(date.getTime() + "");
		assessmentScore.setUsername(EAPApplicationPreference.getString(EAPConstants.PREFS_LOGGED_IN_USERNAME, "", LifePilotQuizResultActivity.this));
		assessmentScore.setLifePilotAnswersWithDelimeters(TextUtils.join(",", lifePilotAnswers));
		
		AssessmentScoreTable assessmentScoreTable = new AssessmentScoreTable(LifePilotQuizResultActivity.this);
		assessmentScoreTable.open();
		assessmentScoreTable.insert(assessmentScore);
		assessmentScoreTable.close();
	}
	
	/**
	 * Navigate to home once quiz is finished
	 */
	private void finishAndProceedToHome() {
		Intent intent = new Intent(LifePilotQuizResultActivity.this, HomeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
	
	/**
	 * Calculating the available services for LifePilot based on selection of answers by the user
	 */
	private void setServiceNamesAndFinalScore() {
		if(lifePilotAnswers[0].equalsIgnoreCase("1") || lifePilotAnswers[1].equalsIgnoreCase("1")) {
			finalScore+=1;
			servicesNames.add(getString(R.string.legal_services));
			servicesNewPositions.add("2");
			servicesUrls.add("file:///android_asset/MyEapServices/Legal.html");
			txt_quiz_result_legal.setVisibility(View.VISIBLE);
		}
		
		if(lifePilotAnswers[2].equalsIgnoreCase("1") || lifePilotAnswers[3].equalsIgnoreCase("1")) {
			finalScore+=1;
			servicesNames.add(getString(R.string.financial_coaching));
			servicesNewPositions.add("3");
			servicesUrls.add("file:///android_asset/MyEapServices/Financial.html");
			txt_quiz_result_financial.setVisibility(View.VISIBLE);
		}
		
		if(lifePilotAnswers[4].equalsIgnoreCase("1")) {
			finalScore+=1;
			servicesNames.add(getString(R.string.homeownership_program));
			servicesNewPositions.add("8");
			servicesUrls.add("");
			txt_quiz_result_homeownership.setVisibility(View.VISIBLE);
		}
		
		if(lifePilotAnswers[5].equalsIgnoreCase("1") || lifePilotAnswers[6].equalsIgnoreCase("1") || lifePilotAnswers[7].equalsIgnoreCase("1") || lifePilotAnswers[8].equalsIgnoreCase("1") || lifePilotAnswers[9].equalsIgnoreCase("1")) {
			finalScore+=1;
			servicesNames.add(getString(R.string.counseling));
			servicesNewPositions.add("0");
			servicesUrls.add("file:///android_asset/MyEapServices/Counseling.html");
			txt_quiz_result_counselling.setVisibility(View.VISIBLE);
		}
		
		if(lifePilotAnswers[10].equalsIgnoreCase("1")) {
			finalScore+=1;
			servicesNames.add(getString(R.string.childcare));
			servicesNewPositions.add("4");
			servicesUrls.add("file:///android_asset/MyEapServices/ChildCare.html");
			txt_quiz_result_child_care.setVisibility(View.VISIBLE);
		}
		
		if(lifePilotAnswers[11].equalsIgnoreCase("1")) {
			finalScore+=1;
			servicesNames.add(getString(R.string.eldercare));
			servicesNewPositions.add("5");
			servicesUrls.add("file:///android_asset/MyEapServices/ElderCare.html");
			txt_quiz_result_elder_care.setVisibility(View.VISIBLE);
		}
		
//		if(lifePilotAnswers[12].equalsIgnoreCase("1") || lifePilotAnswers[13].equalsIgnoreCase("1")) {
//			finalScore+=1;
//			servicesNames.add(getString(R.string.wellness));
//			servicesNewPositions.add("9");
//			servicesUrls.add("file:///android_asset/MyEapServices/Wellness.html");
//			txt_quiz_result_wellness.setVisibility(View.VISIBLE);
//		}
	}
	
	/**
	 * Click listener for the services link, to navigate to detail screen of the clicked/selected service.
	 */
	private View.OnClickListener serviceClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			
			int position = 0;
			
			switch (v.getId()) {
			case R.id.txt_quiz_result_legal:
				position = 2;
				break;
				
			case R.id.txt_quiz_result_financial:
				position = 3;
				break;
				
			case R.id.txt_quiz_result_homeownership:
				position = 8;
				break;
				
			case R.id.txt_quiz_result_counselling:
				position = 0;
				break;
				
			case R.id.txt_quiz_result_child_care:
				position = 4;
				break;
				
			case R.id.txt_quiz_result_elder_care:
				position = 5;
				break;
				
			case R.id.txt_quiz_result_wellness:
				position = 9;
				break;

			default:
				break;
			}
			
			Intent intent = new Intent(LifePilotQuizResultActivity.this, MyEAPServiceDetailActivity.class);
			intent.putExtra(EAPConstants.INTENT_KEY_POSITION, position);
			startActivity(intent);
		}
	};
	
}
