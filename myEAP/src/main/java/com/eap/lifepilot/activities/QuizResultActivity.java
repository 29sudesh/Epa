package com.eap.lifepilot.activities;

import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
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
import android.widget.Toast;

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


public class QuizResultActivity extends BaseActivity {

	private int quizType;
	private int finalScore;
	private int maxScore;
	private ProgressBar progressbar_quiz_result;
	private Button btn_quiz_result_finish;
	private TextView txt_quiz_result;
	private String title;
	private QuizResultAlertDialogFragment quizResultAlertDialogFragment;
	private boolean isComingFromMyAssesmentList;
	private LinearLayout viewPagerCountDots;
	private View headerView;
	private TextView txt_header,tv_score;
	private ImageView img_header;
	private View footerView;
	private SwipeLayout swipe;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_result);
		
		initComponents();
		prepareViews();
		setListeners();
	}

	@Override	
	public void onBackPressed() {
//		if(isComingFromMyAssesmentList) {
//		overridePendingTransition(R.anim.slide_in_right,
//				R.anim.slide_in_right);
			super.onBackPressed();
//		}
	}
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public void initComponents() {
		tv_score = findViewById(R.id.tv_score);
		swipe= findViewById(R.id.swipe);
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
		viewPagerCountDots = findViewById(R.id.viewPagerCountDots);

		isComingFromMyAssesmentList = getIntent().getBooleanExtra(EAPConstants.INTENT_KEY_FROM_MY_ASSESSMENTS_LIST_BOOLEAN, false);
		quizType = getIntent().getIntExtra(EAPConstants.INTENT_SELECTED_QUIZ, EAPConstants.QUIZ_DEPRESSION);
		finalScore = getIntent().getIntExtra(EAPConstants.INTENT_KEY_SCORE, 0);
		
		progressbar_quiz_result = (ProgressBar) findViewById(R.id.progressbar_quiz_result);
		btn_quiz_result_finish = (Button) findViewById(R.id.btn_quiz_result_finish);
		txt_quiz_result = (TextView) findViewById(R.id.txt_quiz_result);

		headerView = (View) findViewById(R.id.header);
		footerView = (View) findViewById(R.id.footer);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		img_header = headerView.findViewById(R.id.img_header);
		img_header.setImageResource(R.drawable.ic_back);
		img_header.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(QuizResultActivity.this, HomeActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});

		quizResultAlertDialogFragment = new QuizResultAlertDialogFragment();

		
		switch (quizType) {
		case EAPConstants.QUIZ_DEPRESSION:
			maxScore = EAPConstants.QUIZ_MAX_SCORE_DEPRESSION;
			title = getString(R.string.my_depression);
			break;
			
		case EAPConstants.QUIZ_RELATIONSHIP:
			maxScore = EAPConstants.QUIZ_MAX_SCORE_RELATIONSHIP;
			title = getString(R.string.my_relation);
			break;

		case EAPConstants.QUIZ_LIFE_PILOT:
			maxScore = EAPConstants.QUIZ_MAX_SCORE_LIFE_PILOT;
			title = getString(R.string.life_pilot);
			break;
			
		case EAPConstants.QUIZ_ALCOHOL:
			maxScore = EAPConstants.QUIZ_MAX_SCORE_ALCOHOL;
			title = getString(R.string.alcohol);
			break;
			
		default:
			maxScore = EAPConstants.QUIZ_MAX_SCORE_DEPRESSION;
			title = getString(R.string.my_depression);
			break;
		}
		progressbar_quiz_result.setMax(maxScore);
		progressbar_quiz_result.setProgress(finalScore);
		tv_score.setText(""+finalScore);

		drawPageSelectionIndicators(12);
	}


	private void drawPageSelectionIndicators(int mPosition) {
		if (viewPagerCountDots != null) {
			viewPagerCountDots.removeAllViews();
		}
		 int dotsCount = 13;    //No of tabs or images
		ImageView[] dots = new ImageView[dotsCount];
		for (int i = 0; i < dotsCount; i++) {
			dots[i] = new ImageView(QuizResultActivity.this);
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
		}
		else {
			viewPagerCountDots.setVisibility(View.VISIBLE);
		}



		progressbar_quiz_result.setProgress(finalScore);

		txt_quiz_result.setMovementMethod(LinkMovementMethod.getInstance());
		setDescriptionForFinalScore();
		
		
	}

	@Override
	public void setListeners() {
		btn_quiz_result_finish.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*Intent intent = new Intent(QuizResultActivity.this, HomeActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);*/
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
	}
	
	private void saveQuizInfo() {
		AssessmentScore assessmentScore = new AssessmentScore();
		Date date = new Date();
		
		assessmentScore.setQuizType(quizType);
		assessmentScore.setUsername(EAPApplicationPreference.getString(EAPConstants.PREFS_LOGGED_IN_USERNAME, "", QuizResultActivity.this));
		assessmentScore.setScore(finalScore + "");
		assessmentScore.setDate(date.getTime() + "");
		
		AssessmentScoreTable assessmentScoreTable = new AssessmentScoreTable(QuizResultActivity.this);
		assessmentScoreTable.open();
		assessmentScoreTable.insert(assessmentScore);
		assessmentScoreTable.close();
	}
	private void setDescriptionForFinalScore() {
		String content = "";
		switch (quizType) {
		case EAPConstants.QUIZ_DEPRESSION:
			
			if(finalScore >=0 && finalScore <=4) {
				content = getString(R.string.quiz_result_depression_0to4);
			}
			else if(finalScore <=9) {
				content = getString(R.string.quiz_result_depression_5to9);
			}
			else if(finalScore <=14) {
				content = getString(R.string.quiz_result_depression_10to14);
			}
			else if(finalScore <=19) {
				content = getString(R.string.quiz_result_depression_11to19);
			}
			else if(finalScore <=27) {
				content = getString(R.string.quiz_result_depression_20to27);
			}
			break;
			
		case EAPConstants.QUIZ_RELATIONSHIP:
			if(finalScore >=0 && finalScore <=11) {
				content = getString(R.string.quiz_result_relationship_0to11);
			}
			else if(finalScore <=32) {
				content = getString(R.string.quiz_result_relationship_12to32);
			}
			else if(finalScore <=44) {
				content = getString(R.string.quiz_result_relationship_33to44);
			}
			break;
			
		case EAPConstants.QUIZ_LIFE_PILOT:
	
			break;
		case EAPConstants.QUIZ_ALCOHOL:
			if(finalScore >=0 && finalScore <=8) {
				content = getString(R.string.quiz_result_alcohol_0to8);
			} else if(finalScore >= 9) {
				content = getString(R.string.quiz_result_alcohol_9to);
			}
			break;

		default:
			break;
		}
		
		SpannableString spannableString = new SpannableString(content);
		int hereIndex = content.lastIndexOf(getString(R.string.here), content.length()-1);
		if(hereIndex!=-1) {
			if(quizType!=EAPConstants.QUIZ_ALCOHOL)
			spannableString.setSpan(new ClickHereClickableSpan(), hereIndex, hereIndex+getString(R.string.here).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
		}
		
		int eapContactIndex = content.lastIndexOf(getString(R.string.eap_contact_no), content.length()-1);
		if(eapContactIndex!=-1) {
			spannableString.setSpan(new EAPContactClickableSpan(), eapContactIndex, eapContactIndex+getString(R.string.eap_contact_no).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		
		txt_quiz_result.setText(spannableString);
	}
	
	private class ClickHereClickableSpan extends ClickableSpan {
		@Override
		public void onClick(View widget) {
			int selectedResource = -1;
			switch (quizType) {
			case EAPConstants.QUIZ_DEPRESSION:
				selectedResource = 1;
				break;
				
			case EAPConstants.QUIZ_RELATIONSHIP:
				selectedResource = 2;
				break;
				
			}
			
			if(selectedResource!=-1) {
				Intent intent = new Intent(QuizResultActivity.this, ResourceComponentsActivity.class);
				intent.putExtra(EAPConstants.INTENT_KEY_POSITION, selectedResource);
				startActivity(intent);
			}
			
		}
	}
	
	private class EAPContactClickableSpan extends ClickableSpan {
		@Override
		public void onClick(View widget) {
			Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.eap_contact_no)));
			startActivity(intent);
		}
	}

	private void finishAndProceedToHome() {
		Intent intent = new Intent(QuizResultActivity.this, HomeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	
	
	/*final CircularProgressBar c2 = (CircularProgressBar) findViewById(R.id.circularprogressbar2);
	c2.animateProgressTo(0, 77, new ProgressAnimationListener() {
		
		@Override
		public void onAnimationStart() {				
		}
		
		@Override
		public void onAnimationProgress(int progress) {
			c2.setTitle(progress + "%");
		}
		
		@Override
		public void onAnimationFinish() {
			c2.setSubTitle("done");
		}
	});*/
	
}
