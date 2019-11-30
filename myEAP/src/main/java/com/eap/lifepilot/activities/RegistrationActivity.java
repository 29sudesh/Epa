package com.eap.lifepilot.activities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.entities.RegistrationData;
import com.eap.lifepilot.utils.EAPConstants;
import com.eap.lifepilot.utils.HttpHelper;
import com.eap.lifepilot.webserives.RegistrationWebService;

public class RegistrationActivity extends BaseActivity {
	
	private View headerView;
	private TextView txt_header;
	
	private Spinner spinner_reg_reminder;
	
	private String reminderQuestions[];
	
	private Button btn_reg_submit;
	private Button btn_reg_reset;
	
	private String username;
	private String password1;
	private String password2;
	private String company;
	private String reminder;
	private String passphrase;
	private String firstName;
	private String lastName;
	private String email;
	private String agreed;
	
	private EditText edt_reg_username;
	private EditText edt_reg_password;
	private EditText edt_verify_password;
	private AutoCompleteTextView edt_reg_company;
	private EditText edt_reg_answer;
	private EditText edt_reg_first_name;
	private EditText edt_reg_last_name;
	private EditText edt_reg_email;
	
	
	private LinearLayout lnr_reg_agree;
	private TextView txt_reg_agree;
	private ImageView img_reg_agree;
	
	private boolean isAgreed = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		initComponents();
		prepareViews();
		setListeners();
	}

	/*public void onRegistration(View v) {
		startActivity(new Intent(this, HomeActivity.class));
		Toast.makeText(this, "Registration Success", Toast.LENGTH_SHORT).show();
		finish();
	}*/

	@Override
	public void initComponents() {
		headerView = (View) findViewById(R.id.header);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		
		spinner_reg_reminder = (Spinner) findViewById(R.id.spinner_reg_reminder);
		
		btn_reg_submit = (Button) findViewById(R.id.btn_reg_submit);
		btn_reg_reset = (Button) findViewById(R.id.btn_reg_reset);
		
		edt_reg_username = (EditText) findViewById(R.id.edt_reg_username);
		edt_reg_password = (EditText) findViewById(R.id.edt_reg_password);
		edt_verify_password = (EditText) findViewById(R.id.edt_verify_password);
		edt_reg_company = (AutoCompleteTextView) findViewById(R.id.edt_reg_company);
		edt_reg_answer = (EditText) findViewById(R.id.edt_reg_answer);
		edt_reg_first_name = (EditText) findViewById(R.id.edt_reg_first_name);
		edt_reg_last_name = (EditText) findViewById(R.id.edt_reg_last_name);
		edt_reg_email = (EditText) findViewById(R.id.edt_reg_email);
		
		lnr_reg_agree = (LinearLayout) findViewById(R.id.lnr_reg_agree);
		txt_reg_agree = (TextView) findViewById(R.id.txt_reg_agree);
		img_reg_agree = (ImageView) findViewById(R.id.img_reg_agree);
		
		reminderQuestions = getResources().getStringArray(R.array.arrays_reminder_questions);
	}

	@Override
	public void prepareViews() {
		txt_header.setText(R.string.registration);
		
		ArrayAdapter<String> reminderAdapter = new ArrayAdapter<String>(this,R.layout.spinner_list_item, reminderQuestions);
		spinner_reg_reminder.setAdapter(reminderAdapter);
		
		SpannableString spannableString = new SpannableString(getString(R.string.agree_terms_conditions));
		spannableString.setSpan(new PrivacyPolicyClickableSpan(), 15, getString(R.string.agree_terms_conditions).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		txt_reg_agree.setText(spannableString);
		txt_reg_agree.setMovementMethod(LinkMovementMethod.getInstance());
		
		new AsynReadFromFile().execute();
	}

	@Override
	public void setListeners() {
		lnr_reg_agree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isAgreed) {
					isAgreed = false;
					img_reg_agree.setImageResource(R.drawable.icon_checkbox);
				} else {
					isAgreed = true;
					img_reg_agree.setImageResource(R.drawable.icon_checkbox_selected);
				}
			}
		});
		btn_reg_submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				username = edt_reg_username.getText().toString();
				password1 = edt_reg_password.getText().toString();
				password2 = edt_verify_password.getText().toString();
				company = edt_reg_company.getText().toString();
				reminder = reminderQuestions[spinner_reg_reminder.getSelectedItemPosition()];
				passphrase = edt_reg_answer.getText().toString();
				firstName = edt_reg_first_name.getText().toString();
				lastName = edt_reg_last_name.getText().toString();
				email = edt_reg_email.getText().toString();
				agreed = isAgreed + "";
				
				String validationMessage = applyValidations();
				if (validationMessage.equalsIgnoreCase("")) {
					new AsyncRegistrationWebService().execute();
				} else {
					showValidationDialog(RegistrationActivity.this, validationMessage);
				}
			}
		});
		
		btn_reg_reset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				edt_reg_username.setText("");
				edt_reg_password.setText("");
				edt_verify_password.setText("");
				edt_reg_company.setText("");
				edt_reg_answer.setText("");
				edt_reg_first_name.setText("");
				edt_reg_last_name.setText("");
				edt_reg_email.setText("");
				
				spinner_reg_reminder.setSelection(0);
				
				isAgreed = false;
				img_reg_agree.setImageResource(R.drawable.icon_checkbox);
				
				edt_reg_username.requestFocus();
				
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_out_up, R.anim.slide_out_down);
	}
	
	private String applyValidations() {
		String validationErrorString = "";
		if (!username.matches(EAPConstants.REGEX_USERNAME)) {
			validationErrorString = getString(R.string.validation_login_username);
			return validationErrorString;
		}

		if (!password1.matches(EAPConstants.REGEX_PASSWORD)) {
			validationErrorString = getString(R.string.validation_login_password);
			return validationErrorString;
		}
		
		if(!password1.equals(password2)) {
			validationErrorString = getString(R.string.validation_reg_confirm_password);
			return validationErrorString;
		}
		
		if(!company.matches(EAPConstants.REGEX_COMPANY)) {
			validationErrorString = getString(R.string.validation_reg_company);
			return validationErrorString;
		}
		
		if(!passphrase.matches(EAPConstants.REGEX_PASSPHRASE_ANSWER)) {
			validationErrorString = getString(R.string.validation_reg_answer);
			return validationErrorString;
		}
		
		if(!firstName.matches(EAPConstants.REGEX_FIRST_NAME)) {
			validationErrorString = getString(R.string.validation_reg_first_name);
			return validationErrorString;
		}
		
		if(!lastName.matches(EAPConstants.REGEX_LAST_NAME)) {
			validationErrorString = getString(R.string.validation_reg_last_name);
			return validationErrorString;
		}
		
		if(email.length() > 0 && !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()) {
			validationErrorString = getString(R.string.validation_reg_email);
			return validationErrorString;
		}
		
		if(!isAgreed) {
			validationErrorString = getString(R.string.validation_reg_agreed);
			return validationErrorString;
		}

		return validationErrorString;
	}
	
	private class AsyncRegistrationWebService extends AsyncTask<Void, Void, Void> {
		private ProgressDialog progressDialog;
		private RegistrationData registrationData;
		
		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(RegistrationActivity.this);
			progressDialog.setCancelable(false);
			progressDialog.setMessage(getString(R.string.loading));
			progressDialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			registrationData = RegistrationWebService.getRegistrationDetails(RegistrationActivity.this, username, password1, password2, company, reminder, passphrase, firstName, lastName, email, agreed);
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
			
			if (registrationData.isSuccessful()) {
				showSuccessFullResetDialog();
			} else {
				showValidationDialog(RegistrationActivity.this, getExceptionMessageFromCode(RegistrationActivity.this, registrationData.getException()));
			}
		}
		
	}
	
	private class PrivacyPolicyClickableSpan extends ClickableSpan {
		@Override
		public void onClick(View widget) {
			Intent intent = new Intent(RegistrationActivity.this, WebViewActivity.class);
			intent.putExtra(EAPConstants.INTENT_KEY_WEBVIEW_URL, EAPConstants.URL_TERMS_CONDITIONS);
			intent.putExtra(EAPConstants.INTENT_KEY_TITLE, getString(R.string.privacy_policy));
			startActivity(intent);
		}
	}
	
	private ArrayList<String> readFromfile() {
		ArrayList<String> returnStringArrayList = new ArrayList<String>();
		InputStream fIn = null;
		InputStreamReader isr = null;
		BufferedReader input = null;
		try {
//			fIn = getResources().getAssets().open(fileName, Context.MODE_WORLD_READABLE);
			fIn = openFileInput(EAPConstants.CASCADE_TEXT_FILE_NAME + ".txt");
			isr = new InputStreamReader(fIn);
			input = new BufferedReader(isr);
			String line = "";
			while ((line = input.readLine()) != null) {
				returnStringArrayList.add(line);
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (isr != null)
					isr.close();
				if (fIn != null)
					fIn.close();
				if (input != null)
					input.close();
			} catch (Exception e2) {
				e2.getMessage();
			}
		}
		return returnStringArrayList;
	}
	
	private class AsynReadFromFile extends AsyncTask<Void, Void, Void> {

		private ProgressDialog progressDialog;
		
		private ArrayList<String> companyNames = null;
		
		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(RegistrationActivity.this);
			progressDialog.setCancelable(false);
			progressDialog.setMessage(getString(R.string.loading));
			progressDialog.show();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			if(HttpHelper.downloadFile(RegistrationActivity.this, EAPConstants.URL_CASCADE_TEXT_FILE, EAPConstants.CASCADE_TEXT_FILE_NAME)) {
				companyNames = readFromfile();
			}
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
			
			if(companyNames!=null) {
				ArrayAdapter<String> companyAdapter = new ArrayAdapter<String>(RegistrationActivity.this, android.R.layout.simple_list_item_1, companyNames);
				edt_reg_company.setAdapter(companyAdapter);
			}
		}
		
	}
	
	private void showSuccessFullResetDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
		
		builder.setIcon(android.R.drawable.ic_dialog_alert).setCancelable(false).setMessage(R.string.successfully_register).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent data = new Intent();
				data.putExtra(EAPConstants.INTENT_KEY_USERNAME, username);
				data.putExtra(EAPConstants.INTENT_KEY_PASSWORD, password1);
				setResult(RESULT_OK, data);

				dialog.dismiss();
				finish();
			}
		});
		
		builder.show();
	}
}
