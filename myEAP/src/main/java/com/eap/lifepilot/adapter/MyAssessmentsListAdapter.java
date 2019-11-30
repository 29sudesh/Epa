package com.eap.lifepilot.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eap.lifepilot.R;
import com.eap.lifepilot.activities.LifePilotQuizResultActivity;
import com.eap.lifepilot.activities.QuizResultActivity;
import com.eap.lifepilot.entities.AssessmentScore;
import com.eap.lifepilot.utils.EAPConstants;

public class MyAssessmentsListAdapter extends BaseAdapter{

	private Activity mActivity;
	private ArrayList<AssessmentScore> mAssessmentScores;
	
	public MyAssessmentsListAdapter(Activity activity, ArrayList<AssessmentScore> mAssessmentScore) {
		mAssessmentScores = mAssessmentScore;
		mActivity = activity;
	}
	@Override
	public int getCount() {
		return mAssessmentScores.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final AssessmentScore selectedAssessmentScore = mAssessmentScores.get(position);
		
		LayoutInflater inflater = LayoutInflater.from(mActivity);
		convertView = inflater.inflate(R.layout.list_item_my_assessment_list, parent, false);
		
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.rlt_list_item_my_assessment_list = (RelativeLayout) convertView.findViewById(R.id.rlt_list_item_my_assessment_list);
		
		viewHolder.txt_list_item_my_aseesment_score = (TextView) convertView.findViewById(R.id.txt_list_item_my_aseesment_score);
		viewHolder.txt_list_item_my_aseesment_quiz_type = (TextView) convertView.findViewById(R.id.txt_list_item_my_aseesment_quiz_type);
		viewHolder.txt_list_item_my_aseesment_quiz_date = (TextView) convertView.findViewById(R.id.txt_list_item_my_aseesment_quiz_date);
		
		
		viewHolder.txt_list_item_my_aseesment_score.setText(selectedAssessmentScore.getScore());
		viewHolder.txt_list_item_my_aseesment_quiz_type.setText(getQuizTitleFromQuizType(selectedAssessmentScore.getQuizType()));
		viewHolder.txt_list_item_my_aseesment_quiz_date.setText(getFormattedDate(selectedAssessmentScore.getDate()));
		
		viewHolder.rlt_list_item_my_assessment_list.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(selectedAssessmentScore.getQuizType()==EAPConstants.QUIZ_LIFE_PILOT) {
					Intent intent = new Intent(mActivity, LifePilotQuizResultActivity.class);
					intent.putExtra(EAPConstants.INTENT_KEY_LIFE_PILOT_ANSWERS, selectedAssessmentScore.getLifePilotAnswersWithDelimeters().split(","));
					intent.putExtra(EAPConstants.INTENT_KEY_FROM_MY_ASSESSMENTS_LIST_BOOLEAN,true);
					mActivity.startActivity(intent);
				} else {
					Intent intent = new Intent(mActivity, QuizResultActivity.class);
					intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, selectedAssessmentScore.getQuizType());
					intent.putExtra(EAPConstants.INTENT_KEY_SCORE, Integer.parseInt(selectedAssessmentScore.getScore()));
					intent.putExtra(EAPConstants.INTENT_KEY_FROM_MY_ASSESSMENTS_LIST_BOOLEAN,true);
					mActivity.startActivity(intent);
				}
			}
		});
		
		convertView.setTag(viewHolder);
		
		return convertView;
	}

	private class ViewHolder {
		RelativeLayout rlt_list_item_my_assessment_list;
		TextView txt_list_item_my_aseesment_score;
		TextView txt_list_item_my_aseesment_quiz_type;
		TextView txt_list_item_my_aseesment_quiz_date;
	}
	
	private String getQuizTitleFromQuizType(int quizType) {
		String title = "";
		
		switch (quizType) {
		case EAPConstants.QUIZ_DEPRESSION:
			title = mActivity.getString(R.string.depression);
			break;
			
		case EAPConstants.QUIZ_RELATIONSHIP:
			title = mActivity.getString(R.string.relationship);
			break;
			
		case EAPConstants.QUIZ_ALCOHOL:
			title = mActivity.getString(R.string.alcohol);
			break;
			
		case EAPConstants.QUIZ_LIFE_PILOT:
			title = mActivity.getString(R.string.life_pilot);
			break;

		default:
			break;
		}
		
		return title;
	}
	
	private String getFormattedDate(String storedDate) {
		String formattedDate = "";
		long dateInMillis = Long.valueOf(storedDate);
		
		Date date = new Date(dateInMillis);
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss", Locale.US);
		
		formattedDate = dateFormatter.format(date) + " " + timeFormatter.format(date);
		return formattedDate;
	}
}
