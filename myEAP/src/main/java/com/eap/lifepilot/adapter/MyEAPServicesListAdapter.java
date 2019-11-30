package com.eap.lifepilot.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eap.lifepilot.R;
import com.eap.lifepilot.activities.MyEAPServiceDetailActivity;
import com.eap.lifepilot.entities.MyEAPService;
import com.eap.lifepilot.utils.EAPConstants;

public class MyEAPServicesListAdapter extends BaseAdapter{

	private Activity mActivity;
	private ArrayList<MyEAPService> mComponents;
	
	public MyEAPServicesListAdapter(Activity activity, ArrayList<MyEAPService> components) {
		mComponents = components;
		mActivity = activity;
	}
	@Override
	public int getCount() {
		return mComponents.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final MyEAPService selectedComponent = mComponents.get(position);
		
		LayoutInflater inflater = LayoutInflater.from(mActivity);
		convertView = inflater.inflate(R.layout.list_item_my_services, parent, false);
		
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.rlt_list_item_services = (RelativeLayout) convertView.findViewById(R.id.rlt_list_item_services);
		viewHolder.txt_list_item_service_heading = (TextView) convertView.findViewById(R.id.txt_list_item_service_heading);
		viewHolder.txt_list_item_service_description = (TextView) convertView.findViewById(R.id.txt_list_item_service_description);
		viewHolder.img_list_item_service = (ImageView) convertView.findViewById(R.id.img_list_item_service);
		
		viewHolder.txt_list_item_service_heading.setText(selectedComponent.getHeading());
		viewHolder.txt_list_item_service_description.setText(selectedComponent.getDescription());
		
		viewHolder.img_list_item_service.setImageResource(selectedComponent.getImageResource());
		viewHolder.rlt_list_item_services.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*Intent intent = new Intent(mActivity, WebViewActivity.class);
				intent.putExtra(EAPConstants.INTENT_KEY_WEBVIEW_URL, selectedComponent.getFileName());
				intent.putExtra(EAPConstants.INTENT_KEY_TITLE, selectedComponent.getHeading());
				mActivity.startActivity(intent);*/
				
				Intent intent = new Intent(mActivity, MyEAPServiceDetailActivity.class);
				intent.putExtra(EAPConstants.INTENT_KEY_POSITION, position);
				mActivity.startActivity(intent);
			}
		});
		
		convertView.setTag(viewHolder);
		
		return convertView;
	}

	private class ViewHolder {
		RelativeLayout rlt_list_item_services;
		TextView txt_list_item_service_heading;
		TextView txt_list_item_service_description;
		ImageView img_list_item_service;
	}
}
