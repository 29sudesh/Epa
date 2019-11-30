package com.eap.lifepilot.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.BaseFragment;
import com.eap.lifepilot.R;
import com.eap.lifepilot.activities.ForgotPasswordActivity;
import com.eap.lifepilot.entities.ForgotPasswordData;
import com.eap.lifepilot.utils.EAPConstants;
import com.eap.lifepilot.webserives.ForgotPasswordWebService;

public class ForgotPasswordResetFragment extends BaseFragment{
	
	private View view;
	
	private EditText edt_forgot_new_password;
	private EditText edt_forgot_confirm_new_password;
	
	private Button btn_forgot_submit;
	
	private ForgotPasswordActivity forgotPasswordActivity;
	
	private String password1;
	private String password2;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_forgot_password_reset, container, false);
		
		initComponents();
		prepareViews();
		setListeners();
		
		return view;
	}
	
	@Override
	public void initComponents() {
		edt_forgot_new_password = (EditText) view.findViewById(R.id.edt_forgot_new_password);
		edt_forgot_confirm_new_password = (EditText) view.findViewById(R.id.edt_forgot_confirm_new_password);
		
		btn_forgot_submit = (Button) view.findViewById(R.id.btn_forgot_submit);
		
		forgotPasswordActivity = (ForgotPasswordActivity) getActivity();
	}

	@Override
	public void prepareViews() {
		
	}

	@Override
	public void setListeners() {
		btn_forgot_submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				password1 = edt_forgot_new_password.getText().toString();
				password2 = edt_forgot_confirm_new_password.getText().toString();
				
				String validationMessage = applyValidations();
				if (validationMessage.equalsIgnoreCase("")) {
					new AsyncForgotPasswordWebService().execute();
				} else {
					BaseActivity.showValidationDialog(forgotPasswordActivity, validationMessage);
				}
			}
		});
	}
	
	private String applyValidations() {
		String validationErrorString = "";

		if (!password1.matches(EAPConstants.REGEX_PASSWORD)) {
			validationErrorString = getString(R.string.validation_login_password);
			return validationErrorString;
		}
		
		if(!password1.equals(password2)) {
			validationErrorString = getString(R.string.validation_reg_confirm_password);
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
			forgotPasswordData = ForgotPasswordWebService.resetPassword(forgotPasswordActivity, forgotPasswordActivity.username, forgotPasswordActivity.answer, password1, password2);
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
				showSuccessFullResetDialog();
			} else {
				BaseActivity.showValidationDialog(forgotPasswordActivity, BaseActivity.getExceptionMessageFromCode(forgotPasswordActivity, forgotPasswordData.getException()));
			}
		}
		
	}
	
	private void showSuccessFullResetDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(forgotPasswordActivity);
		
		builder.setIcon(android.R.drawable.ic_dialog_alert).setCancelable(false).setMessage(R.string.successfully_reset).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				forgotPasswordActivity.finish();
			}
		});
		
		builder.show();
	}

	
}
