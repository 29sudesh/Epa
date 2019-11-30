package com.eap.lifepilot.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.webkit.CookieManager;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.entities.LoginData;
import com.eap.lifepilot.utils.EAPApplicationPreference;
import com.eap.lifepilot.utils.EAPConstants;
import com.eap.lifepilot.webserives.LoginWebService;

import java.io.File;
import java.util.Date;

/**
 * The first/landing screen of the application that allows user to login into the application.
 * @author Gateway Technolabs Pvt. Ltd.
 *
 */
public class LoginActivity extends BaseActivity {

	private Button btn_login_logIn;
	//private Button btn_login_forgot_password;
	//private Button btn_login_registration;

	//private EditText edt_login_email;
	//private EditText edt_login_password;

	//private CheckBox chk_login_remember_me;
	//private boolean rememberMe;


	private final int REQUEST_CODE_REGISTRATION = 1;
	private WebView login_webView;

    private DragEvent dragEvent = null;


    //@Override
    public boolean onDrag(View view, DragEvent event) {
//        if (mLauncher == null || mDragController == null) {
//            postCleanup();
//            return false;
//        }
        if (event.getAction() == DragEvent.ACTION_DRAG_STARTED) {
            System.out.println("onDrag: "+event.getAction() );
//            if (onDragStart(event)) {
//                return true;
//            } else {
//                postCleanup();
//                return false;
//            }
        }
        int mDragStartTime = 0;
        return true; //mDragController.onDragEvent(mDragStartTime, event);
    }


	/** Called when the activity is first created. */
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		login_webView = (WebView)findViewById(R.id.login_webview);
		login_webView.setWebViewClient(new WebViewClient());
		login_webView.loadUrl(EAPConstants.SSO_WEB_SERVICE_LOGIN);
        String ml = login_webView.getUrl();

        login_webView.getSettings().setJavaScriptEnabled(true);

        System.out.println("current url is: "+ml);
        //DragEvent dragEvent = onDrag;
        //login_webView.onDragEvent(dragEvent);
        login_webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                String liurl = login_webView.getUrl();
                System.out.println("onPageFinished: "+liurl);
                if(liurl.contains("saml-01.personaladvantage.com/")){
                    System.out.println("Logged in");
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                    login_webView.clearCache(true);
                    //login_webView.c
                    finish();
                    // Exit Web view
                }
            }
        });
		initComponents();
		prepareViews();
		setListeners();
	}





	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

        /*
		if(requestCode == REQUEST_CODE_REGISTRATION && resultCode == RESULT_OK) {
			edt_login_email.setText(data.getStringExtra(EAPConstants.INTENT_KEY_USERNAME));
			edt_login_password.setText(data.getStringExtra(EAPConstants.INTENT_KEY_PASSWORD));
			
			new AsyncTaskLogin().execute();
		}
		*/
	}
	@Override
	public void initComponents() {
        /*
		btn_login_logIn = (Button) findViewById(R.id.btn_login_logIn);
		btn_login_forgot_password = (Button) findViewById(R.id.btn_login_forgot_password);
		btn_login_registration = (Button) findViewById(R.id.btn_login_registration);

		edt_login_email = (EditText) findViewById(R.id.edt_login_email);
		edt_login_password = (EditText) findViewById(R.id.edt_login_password);

		chk_login_remember_me = (CheckBox) findViewById(R.id.chk_login_remember_me);
		
		rememberMe = EAPApplicationPreference.getBoolean(EAPConstants.PREFS_IS_REMEMBER_ME, false, LoginActivity.this);
		*/
	}

	@Override
	public void prepareViews() {
//		if (rememberMe) {
//			chk_login_remember_me.setChecked(true);
//
//			String savedUsername = EAPApplicationPreference.getString(EAPConstants.PREFS_USERNAME, "", LoginActivity.this);
//			String savedPassword = EAPApplicationPreference.getString(EAPConstants.PREFS_PASSWORD, "", LoginActivity.this);
//
//			edt_login_email.setText(savedUsername);
//			edt_login_password.setText(savedPassword);
//		} else {
//			chk_login_remember_me.setChecked(false);
//			edt_login_email.setText("");
//			edt_login_password.setText("");
//		}
	}

	@Override
	public void setListeners() {
//		btn_login_logIn.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				String validationMessage = validateUsernameAndPassword();
//				if (validationMessage.equalsIgnoreCase("")) {
//					new AsyncTaskLogin().execute();
//				} else {
//					showValidationDialog(LoginActivity.this, validationMessage);
//				}
//			}
//		});

//		btn_login_forgot_password.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
//				overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
//			}
//		});
//
//		btn_login_registration.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				startActivityForResult(new Intent(LoginActivity.this, RegistrationActivity.class), REQUEST_CODE_REGISTRATION);
//				overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
//			}
//		});

//		chk_login_remember_me.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if (rememberMe) {
//					rememberMe = false;
//
//				} else {
//					rememberMe = true;
//				}
//			}
//		});
	}

	/**
	 * Validations applied for the Login screen
	 * @return error message to show. blank or zero length string if no error
	 */
//	private String validateUsernameAndPassword() {
//		String validationErrorString = "";
//		if (!edt_login_email.getText().toString().matches(EAPConstants.REGEX_USERNAME)) {
//			validationErrorString = getString(R.string.validation_login_username);
//			return validationErrorString;
//		}
//
//		if (!edt_login_password.getText().toString().matches(EAPConstants.REGEX_PASSWORD)) {
//			validationErrorString = getString(R.string.validation_login_password);
//			return validationErrorString;
//		}
//
//		return validationErrorString;
//	}

	/**
	 * A background task that process login request and navigates user to home screen or shows equivalent error message.
	 * @author Gateway Technolabs Pvt. Ltd.
	 *
	 */
	private class AsyncTaskLogin extends AsyncTask<Void, Void, Void> {

		private ProgressDialog progressDialog;

		private LoginData loginData;

		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(LoginActivity.this);
			progressDialog.setCancelable(false);
			progressDialog.setMessage(getString(R.string.loading));
			progressDialog.show();

		}

		@Override
		protected Void doInBackground(Void... params) {
//			loginData = LoginWebService.getLoginDetails(LoginActivity.this, edt_login_email.getText().toString(), edt_login_password.getText().toString());
            loginData = LoginWebService.getLoginDetails(LoginActivity.this, "nsm1234" , "nsm1234");
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


			if (loginData.isSuccessful()) {
				setRememberMeSettings();

				startActivity(new Intent(LoginActivity.this, HomeActivity.class));
				finish();
			} else {
				showValidationDialog(LoginActivity.this, getExceptionMessageFromCode(LoginActivity.this, loginData.getException()));
			}

		}

	}

	/**
	 * Store login username and password if user has selected remember me option
	 */
	private void setRememberMeSettings() {
//		EAPApplicationPreference.set(EAPConstants.PREFS_IS_REMEMBER_ME, rememberMe, LoginActivity.this);
//
//		EAPApplicationPreference.set(EAPConstants.PREFS_LOGGED_IN_USERNAME, edt_login_email.getText().toString(), LoginActivity.this);
//
//		if (rememberMe) {
//			EAPApplicationPreference.set(EAPConstants.PREFS_USERNAME, edt_login_email.getText().toString(), LoginActivity.this);
//			EAPApplicationPreference.set(EAPConstants.PREFS_PASSWORD, edt_login_password.getText().toString(), LoginActivity.this);
//		} else {
//			EAPApplicationPreference.set(EAPConstants.PREFS_USERNAME, "", LoginActivity.this);
//			EAPApplicationPreference.set(EAPConstants.PREFS_PASSWORD, "", LoginActivity.this);
//		}
	}

	public void onBackPressed() {
		if(login_webView.canGoBack()){
			login_webView.goBack();
		} else {
			super.onBackPressed();
		}
	}
}
