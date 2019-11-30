package com.eap.lifepilot.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.adapter.ResourceListAdapter;
import com.eap.lifepilot.data.ResourcesData;
import com.eap.lifepilot.entities.Resource;

public class ResourcesActivity extends BaseActivity{
	private ListView lst_resources;
	private ArrayList<Resource> resources;
	private ResourceListAdapter resourceListAdapter;
	private View headerView;
	private View footerView;
	private TextView txt_header;
	private ImageView img_header;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resources);
		initComponents();
		prepareViews();
		setListeners();
	}
	@Override
	public void initComponents() {
		headerView = (View) findViewById(R.id.header);
		footerView = (View) findViewById(R.id.footer);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		lst_resources = (ListView) findViewById(R.id.lst_resources);
		ResourcesData resourcesData = new ResourcesData(this);
		resources = resourcesData.getResources();
		resourceListAdapter = new ResourceListAdapter(this, resources);
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
		lst_resources.setAdapter(resourceListAdapter);
		txt_header.setText(R.string.resources);
	}

	@Override
	public void setListeners() {
		
	}

}
