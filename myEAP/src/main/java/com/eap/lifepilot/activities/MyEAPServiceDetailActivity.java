package com.eap.lifepilot.activities;

import org.xml.sax.XMLReader;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Html.TagHandler;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.eap.lifepilot.BaseActivity;
import com.eap.lifepilot.R;
import com.eap.lifepilot.data.MyEAPServicesData;
import com.eap.lifepilot.entities.MyEAPService;
import com.eap.lifepilot.utils.EAPConstants;

/**
 * Detail screen of a EAP service.
 * 
 * @author Gateway Technolabs Pvt. Ltd.
 *
 */
public class MyEAPServiceDetailActivity extends BaseActivity {

	private ImageView img_my_service_detail;

//	private TextView txt_my_service_detail_description;

	private WebView webView;

	private View headerView;
	private TextView txt_header;
	private ImageView img_header;
	private View footerView;

	private MyEAPService selectedMyEAPService;

	private int selectedPos;
	private String urlToBeLoaded = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_services_detail);

		initComponents();
		prepareViews();
		setListeners();
	}

	@Override
	public void initComponents() {
		selectedPos = getIntent().getIntExtra(EAPConstants.INTENT_KEY_POSITION, 0);
		webView = (WebView) findViewById(R.id.wv_webview);
		headerView = (View) findViewById(R.id.header);
		txt_header = (TextView) headerView.findViewById(R.id.txt_header);

		img_my_service_detail = (ImageView) findViewById(R.id.img_my_service_detail);
//		txt_my_service_detail_description = (TextView) findViewById(R.id.txt_my_service_detail_description);

		MyEAPServicesData myEAPServicesData = new MyEAPServicesData(this);
		selectedMyEAPService = myEAPServicesData.getMyEAPServices().get(selectedPos);
		urlToBeLoaded = selectedMyEAPService.getFileName();

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

	}

	@Override
	public void prepareViews() {
		txt_header.setText(selectedMyEAPService.getHeading());
//		txt_my_service_detail_description.setText(Html.fromHtml(selectedMyEAPService.getDetail(),null,new MyTagHandler()));

		img_my_service_detail.setImageResource(selectedMyEAPService.getDetailImageResource());
		
//		txt_my_service_detail_description.setMovementMethod(LinkMovementMethod.getInstance());

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
					progressDialog = new ProgressDialog(MyEAPServiceDetailActivity.this);
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


	/**
	 * Custom Tag handler to make textview understand <OL> and <UL> <LI> tags and format the text accordingly
	 * @author Gateway Technolabs Pvt. Ltd.
	 *
	 */
	public class MyTagHandler implements TagHandler {
		boolean first = true;
		String parent = null;
		int index = 1;

		@Override
		public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

			if (tag.equals("ul"))
				parent = "ul";
			else if (tag.equals("ol"))
				parent = "ol";
			if (tag.equals("li")) {
				if (parent.equals("ul")) {
					if (first) {
						output.append("\n\tZ");
						first = false;
					} else {
						first = true;
					}
				} else {
					if (first) {
						output.append("\n\t" + index + ". ");
						first = false;
						index++;
					} else {
						first = true;
					}
				}
			}
		}
	}
}
