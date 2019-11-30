package com.eap.lifepilot.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.eap.lifepilot.BaseDialogFragment;
import com.eap.lifepilot.R;

public class QuizResultAlertDialogFragment extends BaseDialogFragment{

	private View view;
	private TextView txt_alert_dialog_yes;
	private TextView txt_alert_dialog_no;
	
	private onQuizResultAlertListener quizResultAlertListener;
	
	public interface onQuizResultAlertListener{
		public void onYesClicked();
		public void onNoClicked();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_quiz_result_alert_dialog, container, false);
		
		initComponents();
		prepareViews();
		setListeners();
		return view;
	}
	@Override
	public void initComponents() {
		txt_alert_dialog_yes = (TextView) view.findViewById(R.id.txt_alert_dialog_yes);
		txt_alert_dialog_no = (TextView) view.findViewById(R.id.txt_alert_dialog_no);
		
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getDialog().setCanceledOnTouchOutside(false);
		
	}

	@Override
	public void prepareViews() {
	}

	@Override
	public void setListeners() {
		txt_alert_dialog_no.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getDialog().dismiss();
				if(quizResultAlertListener!=null) {
					quizResultAlertListener.onNoClicked();
				}
			}
		});
		
		txt_alert_dialog_yes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getDialog().dismiss();
				if(quizResultAlertListener!=null) {
					quizResultAlertListener.onYesClicked();
				}
			}
		});
	}
	
	public void setQuizResultAlertListener(onQuizResultAlertListener quizResultAlertListener) {
		this.quizResultAlertListener = quizResultAlertListener;
	}

}
