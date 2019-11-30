package com.eap.lifepilot.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.BaseFragment;
import com.eap.lifepilot.R;
import com.eap.lifepilot.activities.WebViewActivity;
import com.eap.lifepilot.utils.EAPConstants;

public class ContactUsFragment extends BaseActivity{

	private View view;
	
	private Button btn_contactus_scheduleappointment;
	private Button btn_contactus_callus;
	private Button btn_contactus_sendemail;
	
	private EditText edt_contactus_subject;
	private EditText edt_contactus_body;

	private View headerView;
	private TextView txt_header;
	private ImageView img_header,img_footer_contact_us;
	private View footerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_contact_us);

		initComponents();
		prepareViews();
		setListeners();

	}

	@Override
	public void initComponents() {
		btn_contactus_scheduleappointment = (Button) findViewById(R.id.btn_contactus_scheduleappointment);
		btn_contactus_callus = (Button) findViewById(R.id.btn_contactus_callus);
		btn_contactus_sendemail = (Button) findViewById(R.id.btn_contactus_sendemail);
		edt_contactus_subject = (EditText) findViewById(R.id.edt_contactus_subject);
		edt_contactus_body = (EditText) findViewById(R.id.edt_contactus_body);
		headerView = (View) findViewById(R.id.header);
		footerView = (View) findViewById(R.id.footer);
		img_footer_contact_us = findViewById(R.id.img_footer_contact_us);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		img_header = headerView.findViewById(R.id.img_header);
		img_header.setImageResource(R.drawable.ic_back);
		img_header.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public void prepareViews() {
		setFooter(this,footerView);
		txt_header.setText(getString(R.string.contact_us));
//		img_footer_contact_us.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				BaseActivity.showValidationDialog(ContactUsFragment.this, getString(R.string.you_already_on_this_screen));
//			}
//		});
	}

	@Override
	public void setListeners() {
		btn_contactus_callus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.eap_contact_no)));
				startActivity(intent);
			}
		});
		
		btn_contactus_scheduleappointment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ContactUsFragment.this, WebViewActivity.class);
				intent.putExtra(EAPConstants.INTENT_KEY_WEBVIEW_URL, EAPConstants.URL_SCHEDULE_APPOINTMENT);
				intent.putExtra(EAPConstants.INTENT_KEY_TITLE, getString(R.string.schedule_appointment));
				startActivity(intent);
			}
		});
		
		btn_contactus_sendemail.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
//				if(edt_contactus_subject.getText().toString().isEmpty()) {
//					BaseActivity.showValidationDialog(ContactUsFragment.this, getString(R.string.email_invalid_subject));
//				} else {
//					Intent emailIntent = new Intent(Intent.ACTION_SEND);
//					emailIntent.setData(Uri.parse("mailto:"));
//					emailIntent.setType("text/plain");
//
//					emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{getString(R.string.contact_us_prefilled_email)});
//					emailIntent.putExtra(Intent.EXTRA_SUBJECT, edt_contactus_subject.getText().toString());
//					emailIntent.putExtra(Intent.EXTRA_TEXT   , edt_contactus_body.getText().toString());
//					/*Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
//				            "mailto","abc@xyz.com", null));
//				emailIntent.putExtra(Intent.EXTRA_SUBJECT, edt_contactus_subject.getText().toString());
//				emailIntent.putExtra(Intent.EXTRA_TEXT   , edt_contactus_body.getText().toString());
//				startActivity(Intent.createChooser(emailIntent, "Send email..."));*/
//
//					startActivity(emailIntent);

				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
						"mailto",getString(R.string.contact_us_prefilled_email), null));
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, edt_contactus_subject.getText().toString());
				emailIntent.putExtra(Intent.EXTRA_TEXT, edt_contactus_body.getText().toString());
				startActivity(Intent.createChooser(emailIntent, "Send email..."));
//				}
				
			}
		});
	}

}
