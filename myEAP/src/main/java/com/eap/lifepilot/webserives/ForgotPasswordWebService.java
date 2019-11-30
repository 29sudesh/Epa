package com.eap.lifepilot.webserives;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

import com.eap.lifepilot.entities.ForgotPasswordData;
import com.eap.lifepilot.utils.EAPConstants;
import com.eap.lifepilot.utils.HttpHelper;

public class ForgotPasswordWebService {

	public static ForgotPasswordData getChallengeQuestion(Context context, String username) {
		final String RESPONSE_PARAM_QUESTION = "question";
		final String RESPONSE_PARAM_EXCEPTION = "exception";

		ForgotPasswordData forgotPasswordData = new ForgotPasswordData();

		ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("RecoverForm:alias", username));

		String response = HttpHelper.getResponseStringPost(parameters, EAPConstants.WEB_SERVICE_FORGOT_PASSWORD, context);

		if (response == null || response.equals("")) {
			forgotPasswordData.setException("0");
			forgotPasswordData.setSuccessful(false);
			return forgotPasswordData;
		}

		try {
			JSONObject jsonObject = new JSONObject(response);

			if (jsonObject.has(RESPONSE_PARAM_QUESTION)) {
				forgotPasswordData.setSuccessful(true);
				forgotPasswordData.setQuestion(jsonObject.getString(RESPONSE_PARAM_QUESTION));
				
				return forgotPasswordData;
			}

			if (jsonObject.has(RESPONSE_PARAM_EXCEPTION)) {
				forgotPasswordData.setSuccessful(false);
				forgotPasswordData.setException(jsonObject.getString(RESPONSE_PARAM_EXCEPTION));
				
				return forgotPasswordData;
			}

		} catch (Exception e) {
			forgotPasswordData.setException("-1");
			forgotPasswordData.setSuccessful(false);
			
			return forgotPasswordData;
		}
		
		return forgotPasswordData;

	}
	
	public static ForgotPasswordData validateAnswer(Context context, String username, String answer) {
		final String RESPONSE_PARAM_QUESTION = "question";
		final String RESPONSE_PARAM_EXCEPTION = "exception";

		ForgotPasswordData forgotPasswordData = new ForgotPasswordData();

		ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("RecoverForm:alias", username));
		parameters.add(new BasicNameValuePair("RecoverForm:answer", answer));

		String response = HttpHelper.getResponseStringPost(parameters, EAPConstants.WEB_SERVICE_FORGOT_PASSWORD, context);

		if (response == null || response.equals("")) {
			forgotPasswordData.setException("0");
			forgotPasswordData.setSuccessful(false);
			return forgotPasswordData;
		}

		try {
			JSONObject jsonObject = new JSONObject(response);

			if (jsonObject.has(RESPONSE_PARAM_QUESTION)) {
				forgotPasswordData.setSuccessful(true);
				forgotPasswordData.setQuestion(jsonObject.getString(RESPONSE_PARAM_QUESTION));
				
				return forgotPasswordData;
			}

			if (jsonObject.has(RESPONSE_PARAM_EXCEPTION)) {
				forgotPasswordData.setSuccessful(false);
				forgotPasswordData.setException(jsonObject.getString(RESPONSE_PARAM_EXCEPTION));
				
				return forgotPasswordData;
			}

		} catch (Exception e) {
			forgotPasswordData.setException("-1");
			forgotPasswordData.setSuccessful(false);
			
			return forgotPasswordData;
		}
		
		return forgotPasswordData;

	}
	
	
	public static ForgotPasswordData resetPassword(Context context, String username, String answer, String password1, String password2) {
		final String RESPONSE_PARAM_PASSPORT = "passport";
		final String RESPONSE_PARAM_EXCEPTION = "exception";

		ForgotPasswordData forgotPasswordData = new ForgotPasswordData();

		ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("RecoverForm:alias", username));
		parameters.add(new BasicNameValuePair("RecoverForm:answer", answer));
		parameters.add(new BasicNameValuePair("RecoverForm:password1", password1));
		parameters.add(new BasicNameValuePair("RecoverForm:password2", password2));

		String response = HttpHelper.getResponseStringPost(parameters, EAPConstants.WEB_SERVICE_FORGOT_PASSWORD, context);

		if (response == null || response.equals("")) {
			forgotPasswordData.setException("0");
			forgotPasswordData.setSuccessful(false);
			return forgotPasswordData;
		}

		try {
			JSONObject jsonObject = new JSONObject(response);

			if (jsonObject.has(RESPONSE_PARAM_PASSPORT)) {
				forgotPasswordData.setSuccessful(true);
				forgotPasswordData.setQuestion(jsonObject.getString(RESPONSE_PARAM_PASSPORT));
				
				return forgotPasswordData;
			}

			if (jsonObject.has(RESPONSE_PARAM_EXCEPTION)) {
				forgotPasswordData.setSuccessful(false);
				forgotPasswordData.setException(jsonObject.getString(RESPONSE_PARAM_EXCEPTION));
				
				return forgotPasswordData;
			}

		} catch (Exception e) {
			forgotPasswordData.setException("-1");
			forgotPasswordData.setSuccessful(false);
			
			return forgotPasswordData;
		}
		
		return forgotPasswordData;

	}
}
