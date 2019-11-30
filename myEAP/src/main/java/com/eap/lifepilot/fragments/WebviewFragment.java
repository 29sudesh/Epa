package com.eap.lifepilot.fragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.BaseFragment;
import com.eap.lifepilot.R;
import com.eap.lifepilot.utils.EAPConstants;

public class WebviewFragment extends BaseActivity{

	private View view;
	
	private WebView webView;
	
	private String urlToBeLoaded="",title="";
	
	private ProgressDialog progressDialog;

	private View headerView;
	private TextView txt_header;
	private ImageView img_header;
	private View footerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_webview);

		initComponents();
		prepareViews();
		setListeners();
	}

	@Override
	public void initComponents() {
		webView = (WebView) findViewById(R.id.wv_webview);
		progressDialog = new ProgressDialog(WebviewFragment.this);
		headerView = (View) findViewById(R.id.header);
		footerView = (View) findViewById(R.id.footer);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);
		img_header = headerView.findViewById(R.id.img_header);
		img_header.setImageResource(R.drawable.ic_back);
		img_header.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		try {
			urlToBeLoaded = getIntent().getStringExtra(EAPConstants.INTENT_KEY_WEBVIEW_URL);
			title = getIntent().getStringExtra(EAPConstants.INTENT_KEY_TITLE);
		} catch (Exception e) {
			urlToBeLoaded = "";
		}
	}

	@Override
	public void prepareViews() {
		setFooter(this,footerView);
		txt_header.setText(title);
		WebSettings settings = webView.getSettings();
		settings.setAllowFileAccess(true);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);		
		//settings.setAllowContentAccess(true);
		settings.setBuiltInZoomControls(true);
		settings.setJavaScriptEnabled(true);
		settings.setLoadsImagesAutomatically(true);
		settings.setBlockNetworkImage(false);
		//settings.enableSmoothTransition();
		settings.setLoadWithOverviewMode(true);
		settings.setUseWideViewPort(true);
		if(urlToBeLoaded!="") {
			webView.loadUrl(urlToBeLoaded);
		}
	}

	@Override
	public void setListeners() {
		webView.setWebViewClient(new EAPWebViewClient());
	}
	
	private class EAPWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }
	    
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			
			try {
				
				if(!progressDialog.isShowing()) {
					progressDialog.setMessage(getString(R.string.loading));
					progressDialog.setCancelable(false);
					progressDialog.show();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			
			try {
				if(progressDialog.isShowing()) {
					progressDialog.dismiss();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
