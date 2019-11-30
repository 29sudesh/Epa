package com.eap.lifepilot.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.eap.lifepilot.BaseFragment;
import com.eap.lifepilot.R;

public class Menufragment extends BaseFragment {
	private MenuClickInterFace mClick;
	private View view;
//	private TextView txtHeader;

	private RadioButton rdb_menu_contact_us;
	private RadioGroup rdg_menu;

	public interface MenuClickInterFace {
		void onMenuItemClick(int selectedId);
		void getInstanceOfMenuFragment(Menufragment menufragment);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_slide_menu_list, container, false);

		initComponents();
		prepareViews();
		setListeners();

		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mClick = (MenuClickInterFace) activity;
		mClick.getInstanceOfMenuFragment(this);
	}

	@Override
	public void initComponents() {
//		txtHeader = (TextView) view.findViewById(R.id.txt_header);

		rdg_menu = (RadioGroup) view.findViewById(R.id.rdg_menu);
//		rdb_menu_contact_us = (RadioButton) view.findViewById(R.id.rdb_menu_contact_us);
	}

	@Override
	public void prepareViews() {

	}

	@Override
	public void setListeners() {
		rdg_menu.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				mClick.onMenuItemClick(checkedId);
			}
		});

	}
	
	public void setContactUsSelected() {
		rdb_menu_contact_us.setChecked(true);
	}
	
	public void setSelectedButtonID(int id) {
		rdg_menu.check(id);
	}

}
