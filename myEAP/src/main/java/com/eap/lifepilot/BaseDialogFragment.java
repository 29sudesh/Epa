package com.eap.lifepilot;

import android.support.v4.app.DialogFragment;

/**
 * BaseDialogFragment is the base fragment for all dialog fragments to provide
 * better architecture
 * 
 * @author Gateway Technolabs Pvt. Ltd.
 * 
 */
public abstract class BaseDialogFragment extends DialogFragment {

	/**
	 * A method to initialize view, variables etc. in all dialog fragments
	 */
	public abstract void initComponents();

	/**
	 * A method to preset values in views, variables etc. in all dialog fragments
	 */
	public abstract void prepareViews();

	/**
	 * A method that should contain all types of listeners in the dialog fragments
	 */
	public abstract void setListeners();
	
}
