package com.eap.lifepilot.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.adapter.MyAssessmentsListAdapter;
import com.eap.lifepilot.database.AssessmentScoreTable;
import com.eap.lifepilot.entities.AssessmentScore;
import com.eap.lifepilot.utils.EAPApplicationPreference;
import com.eap.lifepilot.utils.EAPConstants;

/**
 * An activity that shows the list of assessment scores saved by the user.
 * @author Gateway Technolabs Pvt. Ltd.
 *
 */
public class MyAssessmentsListActivity extends BaseActivity{

	private View headerView;
	private TextView txt_header;
	
	private ListView lst_my_assessment_list;
	private MyAssessmentsListAdapter myAssessmentsListAdapter;
	
	private ArrayList<AssessmentScore> assessmentScores;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_assessments_list);
		initComponents();
		prepareViews();
		setListeners();
	}

	@Override
	public void initComponents() {
		headerView = (View) findViewById(R.id.header);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		
		lst_my_assessment_list = (ListView) findViewById(R.id.lst_my_assessment_list);
	}

	@Override
	public void prepareViews() {
		txt_header.setText(R.string.my_assessments);
		
		AssessmentScoreTable assessmentScoreTable = new AssessmentScoreTable(this);
		assessmentScoreTable.open();
		assessmentScores = assessmentScoreTable.getAssessmentScores(EAPApplicationPreference.getString(EAPConstants.PREFS_LOGGED_IN_USERNAME, "", MyAssessmentsListActivity.this));
		assessmentScoreTable.close();
		
		myAssessmentsListAdapter = new MyAssessmentsListAdapter(this,assessmentScores);
		
		lst_my_assessment_list.setAdapter(myAssessmentsListAdapter);
	}

	@Override
	public void setListeners() {
		
	}
}
