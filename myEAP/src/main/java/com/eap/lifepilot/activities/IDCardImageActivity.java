package com.eap.lifepilot.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.custom_views.CustomImageViewForZoom;
import com.eap.lifepilot.utils.EAPConstants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * A detail activity for ID card screen.
 * 
 * @author Gateway Technolabs Pvt. Ltd.
 *
 */
 
public class IDCardImageActivity extends BaseActivity{

	private CustomImageViewForZoom img_id_card_image;

	private String imagePath;
	private String title;
	
	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	
	private View headerView;
	private TextView txt_header;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_id_card_image);
		
		initComponents();
		prepareViews();
		setListeners();
	}

	@Override
	public void initComponents() {
		headerView = (View) findViewById(R.id.header);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		
		img_id_card_image = (CustomImageViewForZoom) findViewById(R.id.img_id_card_image);
		
		imageLoader = ImageLoader.getInstance();
		  options = new DisplayImageOptions.Builder()
		    .showImageOnLoading(R.drawable.ic_launcher)
		    .showImageForEmptyUri(R.drawable.ic_launcher)
		    .showImageOnFail(R.drawable.ic_launcher)
		    .cacheInMemory(true)
		    .cacheOnDisk(true)
		    .considerExifParams(true)
		    .build();
		  
		  imagePath = getIntent().getStringExtra(EAPConstants.INTENT_KEY_FILE_URL);
		  title = getIntent().getStringExtra(EAPConstants.INTENT_KEY_TITLE);
	}

	@Override
	public void prepareViews() {
		txt_header.setText(title);
		imageLoader.displayImage("file:///" + imagePath, img_id_card_image, options);
	}

	@Override
	public void setListeners() {
		
	}
}
