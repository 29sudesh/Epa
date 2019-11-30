package com.eap.lifepilot.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.adapter.ResourceComponentsAdapter;
import com.eap.lifepilot.data.ResourcesData;
import com.eap.lifepilot.entities.Component;
import com.eap.lifepilot.entities.Resource;
import com.eap.lifepilot.utils.EAPConstants;

public class ResourceComponentsActivity extends BaseActivity {

	private ListView lst_resource_components;

	private ArrayList<Component> components;
	
	private ArrayList<Resource> resources;

	private ResourceComponentsAdapter resourceComponentsAdapter;
	
	private int selectedResource;
	
	private View headerView;

	private TextView txt_header;
	private ImageView img_header;
	private View footerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resource_components);

		initComponents();
		prepareViews();
		setListeners();
	}

	@Override
	public void initComponents() {
		lst_resource_components = (ListView) findViewById(R.id.lst_resource_components);
		headerView = (View) findViewById(R.id.header);
		footerView = (View) findViewById(R.id.footer);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		selectedResource = getIntent().getIntExtra(EAPConstants.INTENT_KEY_POSITION, 0);
		ResourcesData resourcesData = new ResourcesData(this);
		resources = resourcesData.getResources();
		components = resources.get(selectedResource).getComponents();
		
		resourceComponentsAdapter = new ResourceComponentsAdapter(this, components,resources.get(selectedResource).getTitle());

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
		lst_resource_components.setAdapter(resourceComponentsAdapter);
		
		txt_header.setText(resources.get(selectedResource).getTitle());
	}

	@Override
	public void setListeners() {

	}
	
}
