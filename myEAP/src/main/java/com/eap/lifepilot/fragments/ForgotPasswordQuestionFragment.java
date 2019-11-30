package com.eap.lifepilot.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.BaseFragment;
import com.eap.lifepilot.R;
import com.eap.lifepilot.activities.ForgotPasswordActivity;
import com.eap.lifepilot.entities.ForgotPasswordData;
import com.eap.lifepilot.utils.EAPConstants;
import com.eap.lifepilot.webserives.ForgotPasswordWebService;

public class ForgotPasswordQuestionFragment extends BaseFragment{
	
	private View view;
	
	private TextView txt_forgot_question;
	
	private EditText edt_forgot_question_answer;
	
	private Button btn_forgot_next;
	
	private ForgotPasswordActivity forgotPasswordActivity;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_forgot_password_question, container, false);
		
		initComponents();
		prepareViews();
		setListeners();
		
		return view;
	}
	
	@Override
	public void initComponents() {
		edt_forgot_question_answer = (EditText) view.findViewById(R.id.edt_forgot_question_answer);
		
		txt_forgot_question = (TextView) view.findViewById(R.id.txt_forgot_question);
		
		btn_forgot_next = (Button) view.findViewById(R.id.btn_forgot_next);
		
		forgotPasswordActivity = (ForgotPasswordActivity) getActivity();
	}

	@Override
	public void prepareViews() {
		txt_forgot_question.setText(forgotPasswordActivity.question);
	}

	@Override
	public void setListeners() {
		btn_forgot_next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String validationMessage = applyValidations();
				if (validationMessage.equalsIgnoreCase("")) {
					forgotPasswordActivity.answer = edt_forgot_question_answer.getText().toString();
					new AsyncForgotPasswordWebService().execute();
				} else {
					BaseActivity.showValidationDialog(forgotPasswordActivity, validationMessage);
				}
			}
		});
	}
	
	private String applyValidations() {
		String validationErrorString = "";

		if(!edt_forgot_question_answer.getText().toString().matches(EAPConstants.REGEX_PASSPHRASE_ANSWER)) {
			validationErrorString = getString(R.string.validation_reg_answer);
			return validationErrorString;
		}

		return validationErrorString;
	}
	
	private class AsyncForgotPasswordWebService extends AsyncTask<Void, Void, Void> {
		private ProgressDialog progressDialog;
		private ForgotPasswordData forgotPasswordData;
		
		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(forgotPasswordActivity);
			progressDialog.setCancelable(false);
			progressDialog.setMessage(getString(R.string.loading));
			progressDialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			forgotPasswordData = ForgotPasswordWebService.validateAnswer(forgotPasswordActivity, forgotPasswordActivity.username, forgotPasswordActivity.answer);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			try {
				progressDialog.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (forgotPasswordData.isSuccessful()) {
				forgotPasswordActivity.loadFragment(EAPConstants.FORGOT_PASSWORD_SCREEN_PASSWORD);
			} else {
				BaseActivity.showValidationDialog(forgotPasswordActivity, BaseActivity.getExceptionMessageFromCode(forgotPasswordActivity, forgotPasswordData.getException()));
			}
		}
		
	}

	
}
