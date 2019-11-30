package com.eap.lifepilot.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.utils.EAPConstants;

public class WebViewActivity extends BaseActivity {
	private WebView webView;
	private String urlToBeLoaded;
	private String title;
	private View headerView;
	private TextView txt_header;
	private ImageView img_header;
	private View footerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);

		initComponents();
		prepareViews();
		setListeners();
	}

	@Override
	public void initComponents() {
		webView = (WebView) findViewById(R.id.wv_webview);

		headerView = (View) findViewById(R.id.header);
		footerView = (View) findViewById(R.id.footer);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);

		urlToBeLoaded = getIntent().getStringExtra(EAPConstants.INTENT_KEY_WEBVIEW_URL);
		title = getIntent().getStringExtra(EAPConstants.INTENT_KEY_TITLE);

		img_header = headerView.findViewById(R.id.img_header);
		img_header.setImageResource(R.drawable.ic_back);
		img_header.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public void prepareViews() {
		txt_header.setText(title);
		setFooter(this,footerView);
		WebSettings settings = webView.getSettings();
		settings.setBuiltInZoomControls(true);
		webView.setWebViewClient(new CustomWebClient());
		webView.loadUrl(urlToBeLoaded);
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
				if(progressDialog == null){
					progressDialog = new ProgressDialog(WebViewActivity.this);
				}
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
				if(progressDialog.isShowing())
					progressDialog.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
