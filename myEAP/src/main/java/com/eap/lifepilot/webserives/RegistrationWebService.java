package com.eap.lifepilot.webserives;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

import com.eap.lifepilot.entities.RegistrationData;
import com.eap.lifepilot.utils.EAPConstants;
import com.eap.lifepilot.utils.HttpHelper;

public class RegistrationWebService {

	public static RegistrationData getRegistrationDetails(Context context, String username, String password1, String password2, String company, String reminder, String passphrase, String firstName, String lastName, String email, String agreed) {
		final String RESPONSE_PARAM_PASSPORT = "passport";
		final String RESPONSE_PARAM_EXCEPTION = "exception";

		RegistrationData registrationData = new RegistrationData();

		ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("RegisterForm:alias", username));
		parameters.add(new BasicNameValuePair("RegisterForm:password1", password1));
		parameters.add(new BasicNameValuePair("RegisterForm:password2", password2));
		parameters.add(new BasicNameValuePair("RegisterForm:company", company));
		parameters.add(new BasicNameValuePair("RegisterForm:reminder", reminder));
		parameters.add(new BasicNameValuePair("RegisterForm:passphrase", passphrase));
		parameters.add(new BasicNameValuePair("RegisterForm:first@name", firstName));
		parameters.add(new BasicNameValuePair("RegisterForm:last@name", lastName));
		parameters.add(new BasicNameValuePair("RegisterForm:e@mail", email));
		parameters.add(new BasicNameValuePair("RegisterForm:agreed", agreed));
		

		String response = HttpHelper.getResponseStringPost(parameters, EAPConstants.WEB_SERVICE_REGISTRATION, context);

		if (response == null || response.equals("")) {
			registrationData.setException("0");
			registrationData.setSuccessful(false);
			return registrationData;
		}

		try {
			JSONObject jsonObject = new JSONObject(response);

			if (jsonObject.has(RESPONSE_PARAM_PASSPORT)) {
				registrationData.setSuccessful(true);
				registrationData.setPassport(jsonObject.getString(RESPONSE_PARAM_PASSPORT));
				
				return registrationData;
			}

			if (jsonObject.has(RESPONSE_PARAM_EXCEPTION)) {
				registrationData.setSuccessful(false);
				registrationData.setException(jsonObject.getString(RESPONSE_PARAM_EXCEPTION));
				
				return registrationData;
			}

		} catch (Exception e) {
			registrationData.setException("-1");
			registrationData.setSuccessful(false);
			
			return registrationData;
		}
		
		return registrationData;

	}
}
