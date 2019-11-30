package com.eap.lifepilot.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.fragments.ForgotPasswordQuestionFragment;
import com.eap.lifepilot.fragments.ForgotPasswordResetFragment;
import com.eap.lifepilot.fragments.ForgotPasswordUsernameFragment;
import com.eap.lifepilot.utils.EAPConstants;

/**
 * An activity that performs forgot password/ recovery passwords steps.
 * 
 * @author Gateway Technolabs Pvt. Ltd.
 *
 */
public class ForgotPasswordActivity extends BaseActivity {

	private View headerView;
	private TextView txt_header;

	private LinearLayout lnr_forgot_password;
	
	public String username = "";
	public String question = "";
	public String answer = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_password);

		initComponents();
		prepareViews();
		setListeners();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_out_up, R.anim.slide_out_down); // custom animation
	}

	@Override
	public void initComponents() {
		headerView = (View) findViewById(R.id.header);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);

		lnr_forgot_password = (LinearLayout) findViewById(R.id.lnr_forgot_password);
	}

	@Override
	public void prepareViews() {
		txt_header.setText(R.string.forgot_password);

		loadFragment(EAPConstants.FORGOT_PASSWORD_SCREEN_USERNAME);
	}

	@Override
	public void setListeners() {

	}
	
	/**
	 * A method to load a fragment based on screen parameter
	 * @param screen index that determines whether to load fragment to ask username, challenge question or set new password.
	 */
	
	public void loadFragment(int screen) {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

		lnr_forgot_password.removeAllViews();
		
		switch (screen) {
			
		case EAPConstants.FORGOT_PASSWORD_SCREEN_USERNAME:
			ForgotPasswordUsernameFragment forgotPasswordUsernameFragment = new ForgotPasswordUsernameFragment();
			fragmentTransaction.replace(R.id.lnr_forgot_password, forgotPasswordUsernameFragment);
			break;
			
		case EAPConstants.FORGOT_PASSWORD_SCREEN_QUESTION:
			ForgotPasswordQuestionFragment forgotPasswordQuestionFragment = new ForgotPasswordQuestionFragment();
			fragmentTransaction.addToBackStack("");
			fragmentTransaction.replace(R.id.lnr_forgot_password, forgotPasswordQuestionFragment);
			break;
			
		case EAPConstants.FORGOT_PASSWORD_SCREEN_PASSWORD:
			ForgotPasswordResetFragment forgotPasswordResetFragment = new ForgotPasswordResetFragment();
			fragmentTransaction.addToBackStack("");
			fragmentTransaction.replace(R.id.lnr_forgot_password, forgotPasswordResetFragment);
			
			break;
			
		}
		
		fragmentTransaction.commit();
	}

}
