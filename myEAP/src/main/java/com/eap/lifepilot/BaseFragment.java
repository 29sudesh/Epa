package com.eap.lifepilot;

import android.support.v4.app.Fragment;

/**
 * BaseFragment is the base fragment for all fragments to provide
 * better architecture
 * 
 * @author Gateway Technolabs Pvt. Ltd.
 * 
 */
public abstract class BaseFragment extends Fragment {

	/**
	 * A method to initialize view, variables etc. in all Fragments
	 */
	public abstract void initComponents();

	/**
	 * A method to preset values in views, variables etc. in all Fragments
	 */
	public abstract void prepareViews();

	/**
	 * A method that should contain all types of listeners in the Fragments
	 */
	public abstract void setListeners();
	
	
}
