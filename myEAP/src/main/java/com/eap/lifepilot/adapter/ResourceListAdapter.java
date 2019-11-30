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
import com.eap.lifepilot.activities.ResourceComponentsActivity;
import com.eap.lifepilot.entities.Resource;
import com.eap.lifepilot.utils.EAPConstants;

public class ResourceListAdapter extends BaseAdapter{

	private Activity mActivity;
	private ArrayList<Resource> mResources;
	
	public ResourceListAdapter(Activity activity, ArrayList<Resource> resources) {
		mResources = resources;
		mActivity = activity;
	}
	@Override
	public int getCount() {
		return mResources.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

/*	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Resource selectedResource = mResources.get(position);
		
		LayoutInflater inflater = LayoutInflater.from(mActivity);
		convertView = inflater.inflate(R.layout.list_item_resource, parent, false);
		
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.rlt_list_item_resource = (RelativeLayout) convertView.findViewById(R.id.rlt_list_item_resource);
		viewHolder.txt_list_item_resource_name = (TextView) convertView.findViewById(R.id.txt_list_item_resource_name);
		
		viewHolder.txt_list_item_resource_name.setText(selectedResource.getTitle());
		
		viewHolder.rlt_list_item_resource.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mActivity, ResourceComponentsActivity.class);
				intent.putExtra(EAPConstants.INTENT_KEY_POSITION, position);
				mActivity.startActivity(intent);
//				mActivity.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
			}
		});
		
		convertView.setTag(viewHolder);
		
		return convertView;
	}*/
	
	

	/*private class ViewHolder {
		RelativeLayout rlt_list_item_resource;
		TextView txt_list_item_resource_name;
	}*/
	
	
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Resource selectedResource = mResources.get(position);
		
		LayoutInflater inflater = LayoutInflater.from(mActivity);
		convertView = inflater.inflate(R.layout.list_item_my_services, parent, false);
		
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.rlt_list_item_services = (RelativeLayout) convertView.findViewById(R.id.rlt_list_item_services);
		viewHolder.txt_list_item_service_heading = (TextView) convertView.findViewById(R.id.txt_list_item_service_heading);
		viewHolder.txt_list_item_service_description = (TextView) convertView.findViewById(R.id.txt_list_item_service_description);
		viewHolder.img_list_item_service = (ImageView) convertView.findViewById(R.id.img_list_item_service);
		
		viewHolder.txt_list_item_service_heading.setText(selectedResource.getTitle());
		viewHolder.txt_list_item_service_description.setText("");
//		viewHolder.img_list_item_service.setImageResource(selectedResource.getListImage());
		
		viewHolder.rlt_list_item_services.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mActivity, ResourceComponentsActivity.class);
				intent.putExtra(EAPConstants.INTENT_KEY_POSITION, position);
				mActivity.startActivity(intent);
//				mActivity.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
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
