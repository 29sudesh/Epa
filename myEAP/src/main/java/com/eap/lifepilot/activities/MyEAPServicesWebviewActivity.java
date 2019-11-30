package com.eap.lifepilot.activities;

import java.util.List;

import org.apache.http.cookie.Cookie;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.utils.EAPApplicationPreference;
import com.eap.lifepilot.utils.EAPConstants;
import com.eap.lifepilot.webserives.LifeAdvantageWebService;

/**
 * An activity that shows a webview which loads eap service list activity page which is based on logged in user.
 * @author Gateway Technolabs Pvt. Ltd.
 *
 */
public class MyEAPServicesWebviewActivity extends BaseActivity {

	private WebView webView;

	private View headerView;
	private View footerView;

	private TextView txt_header;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_personal_advantage);

		initComponents();
		prepareViews();
		setListeners();
	}
	
	@Override
	public void onBackPressed() {
		
		if(webView.canGoBack()) {
			webView.goBack();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public void initComponents() {
		CookieSyncManager.createInstance(this);
		CookieSyncManager.getInstance().startSync();

		webView = (WebView) findViewById(R.id.wv_webview);

		headerView = (View) findViewById(R.id.header);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		
		footerView = (View) findViewById(R.id.footer);
	}

	@Override
	public void prepareViews() {
		txt_header.setText(R.string.my_eap_services);

		setFooter(this,footerView);
		
		WebSettings settings = webView.getSettings();
		settings.setAllowFileAccess(true);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		// settings.setAllowContentAccess(true);
		settings.setBuiltInZoomControls(true);
		settings.setJavaScriptEnabled(true);
		settings.setLoadsImagesAutomatically(true);
		settings.setBlockNetworkImage(false);
		// settings.enableSmoothTransition();
		settings.setLoadWithOverviewMode(true);
		settings.setUseWideViewPort(true);
		settings.setAllowUniversalAccessFromFileURLs(true);

		webView.setWebViewClient(new CustomWebClient());

		new AsyncLifeAdvantagePageContent().execute();
	}

	@Override
	public void setListeners() {

	}

	private class CustomWebClient extends WebViewClient {

		private ProgressDialog progressDialog;

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);

			try {
				progressDialog = new ProgressDialog(MyEAPServicesWebviewActivity.this);
				progressDialog.setMessage(getString(R.string.loading));
				progressDialog.setCancelable(false);
				progressDialog.show();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);

			try {
				progressDialog.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class AsyncLifeAdvantagePageContent extends AsyncTask<Void, Void, Void> {

		private ProgressDialog progressDialog;
		private String content;

		private Context mContext;

		private LifeAdvantageWebService lifeAdvantageWebService;
		
		private List<Cookie> cookies;

		@Override
		protected void onPreExecute() {
			mContext = MyEAPServicesWebviewActivity.this;

			lifeAdvantageWebService = new LifeAdvantageWebService(mContext);
			progressDialog = new ProgressDialog(mContext);
			progressDialog.setCancelable(false);
			progressDialog.setMessage(getString(R.string.loading));
			progressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
				content = lifeAdvantageWebService.getLifeAdvantagePageContent(EAPApplicationPreference.getString(EAPConstants.PREFS_USERNAME, "", mContext), EAPApplicationPreference.getString(EAPConstants.PREFS_PASSWORD, "", mContext));
				cookies = lifeAdvantageWebService.getCookies();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			try {
				progressDialog.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (! cookies.isEmpty()){
				 
	            CookieSyncManager.createInstance(mContext);
	            CookieManager cookieManager = CookieManager.getInstance();
	 
	                        //sync all the cookies in the httpclient with the webview by generating cookie string
	            for (Cookie cookie : cookies){
	                 
	               Cookie sessionInfo = cookie;
	     
	                    String cookieString = sessionInfo.getName() + "=" + sessionInfo.getValue() + "; domain=" + sessionInfo.getDomain();
	                    cookieManager.setCookie("https://cascade.personaladvantage.com", cookieString);
	                    CookieSyncManager.getInstance().sync();
	            }
	        }
			
			/*if (content != null && !content.equalsIgnoreCase("")) {
				webView.loadData(content, "text/html", "UTF-8");
			}*/

			webView.loadUrl(EAPConstants.URL_LIFE_MY_EAP_SEVICES);
		}

	}

	
}
