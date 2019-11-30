package com.eap.lifepilot.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eap.lifepilot.R;
import com.eap.lifepilot.activities.WebViewActivity;
import com.eap.lifepilot.entities.Component;
import com.eap.lifepilot.utils.EAPConstants;

public class ResourceComponentsAdapter extends BaseAdapter{

	private Activity mActivity;
	private ArrayList<Component> mComponents;
	private String mTitle;
	
	public ResourceComponentsAdapter(Activity activity, ArrayList<Component> components, String title) {
		mComponents = components;
		mActivity = activity;
		mTitle = title;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		final Component selectedComponent = mComponents.get(position);
		
		LayoutInflater inflater = LayoutInflater.from(mActivity);
		convertView = inflater.inflate(R.layout.list_item_resource, parent, false);
		
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.rlt_list_item_resource = (RelativeLayout) convertView.findViewById(R.id.rlt_list_item_resource);
		viewHolder.txt_list_item_resource_name = (TextView) convertView.findViewById(R.id.txt_list_item_service_heading);
		
		viewHolder.txt_list_item_resource_name.setText(selectedComponent.getHeading());
		
		viewHolder.rlt_list_item_resource.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mActivity, WebViewActivity.class);
				intent.putExtra(EAPConstants.INTENT_KEY_WEBVIEW_URL, selectedComponent.getFileName());
				intent.putExtra(EAPConstants.INTENT_KEY_TITLE, mTitle);
				mActivity.startActivity(intent);
			}
		});
		
		convertView.setTag(viewHolder);
		
		return convertView;
	}

	private class ViewHolder {
		RelativeLayout rlt_list_item_resource;
		TextView txt_list_item_resource_name;
	}
}
