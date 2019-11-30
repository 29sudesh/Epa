package com.eap.lifepilot.webserives;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.eap.lifepilot.utils.EAPConstants;
import com.eap.lifepilot.utils.HttpHelper;

public class LifeAdvantageWebService {
	
	private DefaultHttpClient hc;
	
	private Context mContext;
	
	private List<Cookie> cookies;
	
	
	
	public List<Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(List<Cookie> cookies) {
		this.cookies = cookies;
	}

	public LifeAdvantageWebService(Context context) {
		mContext = context;
	}

	public String getLifeAdvantagePageContent(String username, String password) {

		try {
			ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("SignInForm:action", "edit"));
			parameters.add(new BasicNameValuePair("SignInForm:target", "/landing.jsp"));
			parameters.add(new BasicNameValuePair("SignInForm:alias", username));
			parameters.add(new BasicNameValuePair("SignInForm:password", password));
			parameters.add(new BasicNameValuePair("SignInForm:sticky", "true"));

//			String response = HttpHelper.getResponseStringPost(parameters, EAPConstants.WEB_SERVICE_LIFE_ADVANTAGE, context);
			
			HttpResponse httpResponse =  getResponsePost(parameters, EAPConstants.WEB_SERVICE_LIFE_ADVANTAGE);
//			String response = HttpHelper.getResponseStringPost(parameters, EAPConstants.WEB_SERVICE_LIFE_ADVANTAGE, context);

//			org.apache.http.Header[] headers = httpResponse.getHeaders("Set-Cookie");
			
			HttpEntity entity = httpResponse.getEntity();
			
			return EntityUtils.toString(entity);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "";
	}
	
	public  HttpResponse getResponsePost(ArrayList<NameValuePair> pairs, String url) {

		if (HttpHelper.checkNetwork(mContext)) {

			try {
				// debug(pairs, url, context);
				for (NameValuePair pair : pairs) {
					System.out.println("Name : " + pair.getName() + ", Value : " + pair.getValue());
				}


				HttpPost postMethod = new HttpPost(url);
				postMethod.setEntity(new UrlEncodedFormEntity(pairs));
				

				HttpParams params = new BasicHttpParams();
				
				HttpProtocolParams.setContentCharset(params, "UTF-8");

				DefaultHttpClient hc = new DefaultHttpClient();
				HttpResponse response = hc.execute(postMethod);
				
				cookies = hc.getCookieStore().getCookies();
				
				return response;

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			if (mContext instanceof Activity) {
				((Activity) mContext).runOnUiThread(new Runnable() {
					@Override
					public void run() {
						final String connectionNotAvailable ="Internet not available";
						Toast.makeText(mContext, connectionNotAvailable, Toast.LENGTH_SHORT).show();
					}
				});
			}
		}

		return null;
	}
}
