package com.eap.lifepilot.fragments;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.eap.lifepilot.BaseDialogFragment;
import com.eap.lifepilot.R;
import com.eap.lifepilot.utils.EAPConstants;

public class DepressionAlertDialogFragment extends BaseDialogFragment{

	private View view;
	private TextView txt_alert_dialog;
	private TextView txt_alert_dialog_dismiss;
	
	private String content = "";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_depression_alert_dialog, container, false);
		
		initComponents();
		prepareViews();
		setListeners();
		return view;
	}
	@Override
	public void initComponents() {
		txt_alert_dialog = (TextView) view.findViewById(R.id.txt_alert_dialog);
		txt_alert_dialog_dismiss = (TextView) view.findViewById(R.id.txt_alert_dialog_dismiss);
		
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getDialog().setCanceledOnTouchOutside(false);
		
		content = getArguments().getString(EAPConstants.INTENT_KEY_DIALOG_CONTENT);
	}

	@Override
	public void prepareViews() {
		txt_alert_dialog.setMovementMethod(LinkMovementMethod.getInstance());
		txt_alert_dialog.setText(Html.fromHtml(content));
	}

	@Override
	public void setListeners() {
		txt_alert_dialog_dismiss.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getDialog().dismiss();
			}
		});
	}

}
