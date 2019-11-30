package com.eap.lifepilot.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eap.lifepilot.BaseFragment;
import com.eap.lifepilot.R;
import com.eap.lifepilot.activities.QuizIntroActivity;
import com.eap.lifepilot.activities.ResourcesActivity;
import com.eap.lifepilot.utils.EAPConstants;

public class HomeFragment extends BaseFragment{
	private View view;
	private Button btn_eap_benefit_match;
	private Button btn_contact_us;
	private Button btn_resources;
	private Button btn_my_eap;
//	private TextView txt_home_life_pilot_shortcut;
	FragmentTransaction fragmentTransaction;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_home, container, false);

		initComponents();
		prepareViews();
		setListeners();
		fragmentTransaction = getFragmentManager().beginTransaction();

		return view;
	}
	
	@Override
	public void initComponents() {
//		rlt_home_resources_info = (RelativeLayout) view.findViewById(R.id.rlt_home_resources_info);
//		rlt_home_my_eap_services = (RelativeLayout) view.findViewById(R.id.rlt_home_my_eap_services);

		btn_eap_benefit_match = (Button) view.findViewById(R.id.btn_eap_benefit_match);
		btn_contact_us = (Button) view.findViewById(R.id.btn_contact_us);
		btn_resources = (Button) view.findViewById(R.id.btn_resources);
		btn_my_eap = (Button) view.findViewById(R.id.btn_my_eap);

		/*
		rlt_home_contact_us = (RelativeLayout) view.findViewById(R.id.rlt_home_contact_us);
		rlt_home_my_eap_services = (RelativeLayout) view.findViewById(R.id.rlt_home_my_eap_services);
		rlt_home_assessments = (RelativeLayout) view.findViewById(R.id.rlt_home_assessments);
		rlt_home_my_id_card = (RelativeLayout) view.findViewById(R.id.rlt_home_my_id_card);
		rlt_home_cascade_advantage = (RelativeLayout) view.findViewById(R.id.rlt_home_cascade_advantage);
		
		txt_home_life_pilot_shortcut = (TextView) view.findViewById(R.id.txt_home_life_pilot_shortcut);
		 */
	}

	@Override
	public void prepareViews() {
		
	}

	@Override
	public void setListeners() {

		btn_eap_benefit_match.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(), MyEAPServicesWebviewActivity.class);
//				startActivity(intent);
                Intent intent = new Intent(getActivity().getApplicationContext(), QuizIntroActivity.class);
                intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, EAPConstants.QUIZ_LIFE_PILOT);
				intent.putExtra(EAPConstants.INTENT_KEY_TITLE, getString(R.string.my_eap_services));
                startActivity(intent);

//				Intent intent = new Intent(getActivity(), QuizActivity.class);
//				intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, EAPConstants.QUIZ_LIFE_PILOT);
//				intent.putExtra(EAPConstants.INTENT_KEY_TITLE, getString(R.string.life_pilot_assessment));
//				startActivity(intent);
			}
		});

		btn_contact_us.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),ContactUsFragment.class);
				startActivity(intent);
//				ContactUsFragment contactUsFragment = new ContactUsFragment();
//				fragmentTransaction.replace(R.id.lnr_activity_base, contactUsFragment);
//				fragmentTransaction.addToBackStack(null);
//				fragmentTransaction.commit();
//				fragmentTransaction.replace(R.id.lnr_activity_base, contactUsFragment);
			}
		});

		btn_resources.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), ResourcesActivity.class);
				startActivity(intent);
			}
		});

		btn_my_eap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openWebActivity(getString(R.string.my_eap_services_text),EAPConstants.URL_LIFE_MY_EAP_SEVICES);

//				fragmentTransaction.replace(R.id.lnr_activity_base, cascadeWebviewFragment);

//				Intent intent = new Intent(getActivity(), WebViewActivity.class);
//				intent.putExtra(EAPConstants.INTENT_KEY_WEBVIEW_URL, EAPConstants.URL_SCHEDULE_APPOINTMENT);
//				intent.putExtra(EAPConstants.INTENT_KEY_TITLE, getString(R.string.schedule_appointment));
//				startActivity(intent);

			}
		});

//		txt_home_life_pilot_shortcut.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				/*HomeActivity parentActivity = (HomeActivity) getActivity();
//				parentActivity.loadSelectedFragment(R.id.rdb_menu_life_pilot);*/
//
//				Intent intent = new Intent(getActivity(), QuizActivity.class);
//				intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, EAPConstants.QUIZ_LIFE_PILOT);
//				intent.putExtra(EAPConstants.INTENT_KEY_TITLE, getString(R.string.life_pilot_assessment));
//				startActivity(intent);
//			}
//		});
		
//		rlt_home_contact_us.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				HomeActivity parentActivity = (HomeActivity) getActivity();
//				parentActivity.loadContactUsFragment();
//			}
//		});
//
//		rlt_home_resources_info.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(), ResourcesActivity.class);
//				startActivity(intent);
//			}
//		});
		
//		rlt_home_my_eap_services.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
////				Intent intent = new Intent(getActivity(), MyEAPServicesWebviewActivity.class);
////				startActivity(intent);
//				Intent intent = new Intent(getActivity(), QuizActivity.class);
//				intent.putExtra(EAPConstants.INTENT_SELECTED_QUIZ, EAPConstants.QUIZ_LIFE_PILOT);
//				intent.putExtra(EAPConstants.INTENT_KEY_TITLE, getString(R.string.life_pilot_assessment));
//				startActivity(intent);
//			}
//		});
		
//		rlt_home_assessments.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(), AssessmentActivity.class);
//				startActivity(intent);
//			}
//		});
//
//		rlt_home_my_id_card.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(), MyIDCardListActivity.class);
//				startActivity(intent);
//			}
//		});
//
//		rlt_home_cascade_advantage.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(getActivity(), PersonalAdvantageActivity.class);
//				startActivity(intent);
//			}
//		});
	}

	private void openWebActivity(String title, String url) {
		Intent intent = new Intent(getActivity(),WebviewFragment.class);
		intent.putExtra(EAPConstants.INTENT_KEY_WEBVIEW_URL,url);
		intent.putExtra(EAPConstants.INTENT_KEY_TITLE,title);
		startActivity(intent);
	}
}
