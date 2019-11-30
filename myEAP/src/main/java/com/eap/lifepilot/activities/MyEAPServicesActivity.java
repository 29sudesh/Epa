package com.eap.lifepilot.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.adapter.MyEAPServicesListAdapter;
import com.eap.lifepilot.data.MyEAPServicesData;
import com.eap.lifepilot.entities.MyEAPService;


/**
 * An activity that shows the list of EAP services (replaced with a new webview activity later on for user based service list).
 * @author Gateway Technolabs Pvt. Ltd.
 *
 */
public class MyEAPServicesActivity extends BaseActivity {

	private ListView lst_eap_service_components;

	private ArrayList<MyEAPService> components;
	
	private MyEAPServicesListAdapter myEAPServicesListAdapter;
	
	private View headerView;
	private View footerView;

	private TextView txt_header;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eap_services);

		initComponents();
		prepareViews();
		setListeners();
	}

	@Override
	public void initComponents() {
		lst_eap_service_components = (ListView) findViewById(R.id.lst_eap_services);
		headerView = (View) findViewById(R.id.header);
		footerView = (View) findViewById(R.id.footer);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		
		MyEAPServicesData myEAPServicesData = new MyEAPServicesData(this);
		components = myEAPServicesData.getMyEAPServices();
		
		myEAPServicesListAdapter = new MyEAPServicesListAdapter(this, components);
	}

	@Override
	public void prepareViews() {
		setFooter(this,footerView);
		
		lst_eap_service_components.setAdapter(myEAPServicesListAdapter);
		txt_header.setText(R.string.my_eap_services);
	}

	@Override
	public void setListeners() {

	}
	
}
