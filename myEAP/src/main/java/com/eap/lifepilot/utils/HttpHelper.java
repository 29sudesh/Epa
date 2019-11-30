package com.eap.lifepilot.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

public class HttpHelper {


	public static boolean checkNetwork(Context context) {
		ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if ((conMgr.getActiveNetworkInfo() != null) && conMgr.getActiveNetworkInfo().isAvailable()
				&& conMgr.getActiveNetworkInfo().isConnected()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static InputStream getResponseStreamPost(ArrayList<NameValuePair> pairs, String url, final Context context) {

		HttpResponse response = getResponsePost(pairs, url, context);

		if (response != null) {

			try {

				HttpEntity entity = response.getEntity();

				return entity.getContent();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static HttpResponse getResponsePost(ArrayList<NameValuePair> pairs, String url, final Context context) {

		if (checkNetwork(context)) {

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
				
				return response;

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			if (context instanceof Activity) {
				((Activity) context).runOnUiThread(new Runnable() {
					@Override
					public void run() {
						final String connectionNotAvailable ="Internet not available";
						Toast.makeText(context, connectionNotAvailable, Toast.LENGTH_SHORT).show();
					}
				});
			}
		}

		return null;
	}


	public static String getResponseStringGet(ArrayList<NameValuePair> pairs, String url, final Context context) {

		HttpResponse response = getResponseGet(pairs, url, context);

		if (response != null) {

			try {

				HttpEntity entity = response.getEntity();

				String strResponse = EntityUtils.toString(entity);
				return strResponse;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static String getResponseStringPost(ArrayList<NameValuePair> pairs, String url, final Context context) {

		HttpResponse response = getResponsePost(pairs, url, context);

		if (response != null) {

			try {

				HttpEntity entity = response.getEntity();

				String strResponse = EntityUtils.toString(entity);
				return strResponse;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static HttpResponse getResponseGet(ArrayList<NameValuePair> pairs, String url, final Context context) {

		if (checkNetwork(context)) {

			try {
				// debug(pairs, url);

				url += "?";

				for (NameValuePair nameValuePair : pairs) {
					url += nameValuePair.getName() + "=" + URLEncoder.encode(nameValuePair.getValue(), "UTF-8") + "&";
				}

				if (url.length() > 0) {
					url = url.substring(0, url.length() - 1);
				}

				// Log.d(TAG, "GET URL : "+url);

				HttpGet get = new HttpGet(url);

				HttpParams params = new BasicHttpParams();
				HttpProtocolParams.setContentCharset(params, "UTF-8");

				DefaultHttpClient hc = new DefaultHttpClient();
				HttpResponse response = hc.execute(get);

				// Log.d(TAG, "GET RESPONSE : "+response);
				return response;

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			if (context instanceof Activity) {
				((Activity) context).runOnUiThread(new Runnable() {
					@Override
					public void run() {
						final String connectionNotAvailable = "Internet not available";
						Toast.makeText(context, connectionNotAvailable, Toast.LENGTH_SHORT).show();
					}
				});
			}
		}

		return null;
	}
	
	public static boolean downloadFile(Context context, String URL, String fileName) {
		  try {
		   URL url = new URL(URL);
		   HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		   urlConnection.setRequestMethod("GET");
		   // urlConnection.setDoOutput(true);

		   urlConnection.connect();
		   File internalStorage = context.getFilesDir();

		   File tmpFile = new File(internalStorage + "");
		   if (!tmpFile.exists()) {
		    tmpFile.mkdir();
		   }

		   File file = new File(tmpFile, fileName + ".txt");
		   FileOutputStream fileOutput = new FileOutputStream(file, false);
		   InputStream inputStream = urlConnection.getInputStream();

		   byte[] buffer = new byte[1024];
		   int bufferLength = 0;

		   while ((bufferLength = inputStream.read(buffer)) > 0) {
		    fileOutput.write(buffer, 0, bufferLength);
		   }
		   fileOutput.close();
		   
		   return true;

		  } catch (MalformedURLException e) {
		   e.printStackTrace();
		  } catch (IOException e) {
		   e.printStackTrace();
		  } catch (Exception e) {
		   Log.d("Error Downloading File", e.getMessage());
		  }
		  
		  return false;
		 }
	
}
