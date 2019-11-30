package com.eap.lifepilot.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.MenuListener;
import com.eap.lifepilot.R;
import com.eap.lifepilot.fragments.HomeFragment;
import com.eap.lifepilot.fragments.Menufragment;
import com.eap.lifepilot.fragments.WebviewFragment;
import com.eap.lifepilot.utils.EAPConstants;

import java.io.File;
import java.util.Date;

/**
 * An activity that has a menu (sliding menu) and a detail content pane to load the selected fragments in it.
 * 
 * @author Gateway Technolabs Pvt. Ltd.
 *
 */

public class HomeActivity extends BaseActivity implements MenuListener{
//	private SlidingMenu menu;

	private String ACTIVITY_TAG  =HomeActivity.class.getSimpleName();
	
	private RelativeLayout rlt_header_image_click;


	private Handler showContentHandler;

	private Runnable showContentRunnable;
	
	private LinearLayout lnr_activity_base;
	private String FRAGMENTTAG = HomeFragment.class.getSimpleName();

	private View headerView;
	private TextView txt_header;
	private ImageView  img_footer_home ;
	private View footerView;
	
//	private Menufragment menufragment;
	
	private int selectedFragmentID = 525;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// In Activity's onCreate() for instance
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window w = getWindow();
			w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		}
		setContentView(R.layout.activity_home);
		initComponents();
		setListeners();
		sethomeId();
	}

	public void sethomeId(){
		selectedFragmentID = 525;
		loadSelectedFragment(selectedFragmentID);
	}


	public void openMenudialog(){
		final Dialog dialog = new Dialog(HomeActivity.this);
		dialog.setCancelable(true);
		dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog.setContentView(R.layout.popup_side_menu);

		Window window = dialog.getWindow();
		WindowManager.LayoutParams wlp = window.getAttributes();

		wlp.gravity = Gravity.TOP|Gravity.LEFT|Gravity.START;
		wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		window.setAttributes(wlp);

		dialog.show();

		LinearLayout ll_facebook,ll_youtube,ll_twitter,ll_cascading_cenrter,ll_eap_member,ll_logout;
		ll_facebook = dialog.findViewById(R.id.ll_facebook);
		ll_youtube = dialog.findViewById(R.id.ll_youtube);
		ll_twitter = dialog.findViewById(R.id.ll_twitter);
		ll_cascading_cenrter = dialog.findViewById(R.id.ll_cascade_center);
		ll_eap_member = dialog.findViewById(R.id.ll_eap_member);
		ll_logout = dialog.findViewById(R.id.ll_logout);

		ll_facebook.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				loadFragment(v.getId());
			}
		});

		ll_youtube.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				loadFragment(v.getId());
			}
		});

		ll_twitter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				loadFragment(v.getId());
			}
		});

		ll_cascading_cenrter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				loadFragment(v.getId());
			}
		});

		ll_eap_member.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				loadFragment(v.getId());
			}
		});

		ll_logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				loadFragment(v.getId());
			}
		});
	}

	@Override
	public void initComponents() {
		headerView = (View) findViewById(R.id.header);
		footerView = (View) findViewById(R.id.footer);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		img_footer_home = (ImageView) footerView.findViewById(R.id.img_footer_home);
		rlt_header_image_click = (RelativeLayout) headerView.findViewById(R.id.rlt_header_image_click);
		lnr_activity_base = (LinearLayout) findViewById(R.id.lnr_activity_base);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		selectedFragmentID = getIntent().getIntExtra(EAPConstants.INTENT_KEY_SELECTED_FRAGMENT, R.id.rdb_menu_home);
		setFooter(HomeActivity.this,footerView);


	}

		@SuppressWarnings("deprecation")
		public static void clearCookies(Context context)
		{

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
				Log.d("logout", "Using clearCookies code for API >=" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
				CookieManager.getInstance().removeAllCookies(null);
				CookieManager.getInstance().flush();
			} else
			{
				Log.d("logout", "Using clearCookies code for API <" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
				CookieSyncManager cookieSyncMngr=CookieSyncManager.createInstance(context);
				cookieSyncMngr.startSync();
				CookieManager cookieManager=CookieManager.getInstance();
				cookieManager.removeAllCookie();
				cookieManager.removeSessionCookie();
				cookieSyncMngr.stopSync();
				cookieSyncMngr.sync();
			}
		}


	@Override
	public void prepareViews() {
//		menu = new SlidingMenu(this);
//		menu.setMode(SlidingMenu.LEFT);
//		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
//		menu.setShadowWidthRes(R.dimen.shadow_width);
//		menu.setShadowDrawable(R.drawable.shadow);
//		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//		// menu.setFadeDegree(0.35f);
//		menu.setFadeDegree(0f);
//		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
//		menu.setMenu(R.layout.fragment_slide_menu);
//		menu.setBehindScrollScale(0f);
//		menu.setSlidingEnabled(true);
//
//		setFooter(this,footerView);
//

		loadSelectedFragment(selectedFragmentID);
	}

	@Override
	public void setListeners() {
		rlt_header_image_click.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openMenudialog();
//				menu.toggle(true);
			}
		});
		img_footer_home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				BaseActivity.showValidationDialog(HomeActivity.this,getString(R.string.already_on_home_screen));
			}
		});
	}

	@Override
	public void onMenuItemClick(int selectedId) {
//		loadFragment(selectedId);
//		showContentHandler = new Handler();
//		showContentRunnable = new Runnable() {
//			@Override
//			public void run() {
//				menu.showContent(true);
//				showContentHandler.removeCallbacks(showContentRunnable);
//			}
//		};
//		showContentHandler.postDelayed(showContentRunnable, 200);
//
	}

	@Override
	public void getInstanceOfMenuFragment(Menufragment menufragment) {
//		this.menufragment = menufragment;
	}
	
	/**
	 * A method to load a fragment based on screenId parameter
	 * @param screenId index that determines which fragment to load.
	 */
	
	private void loadFragment(int screenId) {

//		lnr_activity_base.removeAllViews();
		Bundle bundle = new Bundle();
		switch (screenId) {
		case 525:
			txt_header.setText(getResources().getString(R.string.welcome));
			FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
			HomeFragment homeFragment = new HomeFragment();
			FRAGMENTTAG = HomeFragment.class.getSimpleName();
			fragmentTransaction.replace(R.id.lnr_activity_base, homeFragment);
			fragmentTransaction.commitAllowingStateLoss();
			break;
//		case R.id.rdb_menu_life_pilot:
//
//			txt_header.setText(getResources().getString(R.string.life_pilot_assessment));
//
//			LifePilotFragmentForHome lifePilotFragmentForHome = new LifePilotFragmentForHome();
//			fragmentTransaction.replace(R.id.lnr_activity_base, lifePilotFragmentForHome);
//
//			break;
//		case R.id.rdb_menu_contact_us:
//
//			txt_header.setText(getResources().getString(R.string.contact_us));
//
//			ContactUsFragment contactUsFragment = new ContactUsFragment();
//			fragmentTransaction.replace(R.id.lnr_activity_base, contactUsFragment);
//			break;
		case R.id.ll_cascade_center:
			openWebActivity(getString(R.string.cascade_centers_text),EAPConstants.URL_CASCADE_CENTER);
			break;

			case R.id.ll_eap_member:
				openWebActivity(getString(R.string.eap_member_site),EAPConstants.URL_EPA_MEMBER_SIDE_TOP_MENU);
				break;

		case R.id.ll_facebook:
			openWebActivity(getString(R.string.facebook),EAPConstants.URL_FACEBOOK);
			break;

		case R.id.ll_twitter:
			openWebActivity(getString(R.string.twitter),EAPConstants.URL_TWITTER);
			
			break;
			
		case R.id.ll_youtube:
			openWebActivity(getString(R.string.youtube),EAPConstants.URL_YOU_TUBE);
			
			break;
			
		case R.id.ll_logout:
			clearCookies(HomeActivity.this);
			clearCache(HomeActivity.this,30);
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	
	}

	private void openWebActivity(String title, String url) {
		Intent intent = new Intent(HomeActivity.this,WebviewFragment.class);
		intent.putExtra(EAPConstants.INTENT_KEY_WEBVIEW_URL,url);
		intent.putExtra(EAPConstants.INTENT_KEY_TITLE,title);
		startActivity(intent);
	}

	/**
	 * Make the contact us option selected in menu
	 */
	
	public void loadContactUsFragment()
	{
//		menufragment.setContactUsSelected();
	}
	/**
	 * Make an option in the menu as selected
	 * @param id Determines which option in sliding menu to make selected/highlighted
	 */
	
	public void loadSelectedFragment(int id) {
		loadFragment(id);
//		menufragment.setSelectedButtonID(id);
		
	}

	//helper method for clearCache() , recursive
//returns number of deleted files
	private int clearCacheFolder(final File dir, final int numDays) {

		int deletedFiles = 0;
		if (dir!= null && dir.isDirectory()) {
			try {
				for (File child:dir.listFiles()) {

					//first delete subdirectories recursively
					if (child.isDirectory()) {
						deletedFiles += clearCacheFolder(child, numDays);
					}

					//then delete the files and subdirectories in this dir
					//only empty directories can be deleted, so subdirs have been done first
					if (child.lastModified() < new Date().getTime() - numDays * DateUtils.DAY_IN_MILLIS) {
						if (child.delete()) {
							deletedFiles++;
						}
					}
				}
			}
			catch(Exception e) {
				Log.e(ACTIVITY_TAG, String.format("Failed to clean the cache, error %s", e.getMessage()));
			}
		}
		return deletedFiles;
	}

	@Override
	public void onBackPressed() {
		if (!FRAGMENTTAG.toString().equalsIgnoreCase(HomeFragment.class.getSimpleName())){
			sethomeId();
		}
		else {
			super.onBackPressed();
		}

	}

	/*
         * Delete the files older than numDays days from the application cache
         * 0 means all files.
         */
	private  void clearCache(final Context context, final int numDays) {
		Log.i(ACTIVITY_TAG, String.format("Starting cache prune, deleting files older than %d days", numDays));
		int numDeletedFiles = clearCacheFolder(context.getCacheDir(), numDays);
		Log.i(ACTIVITY_TAG, String.format("Cache pruning completed, %d files deleted", numDeletedFiles));
	}
}
