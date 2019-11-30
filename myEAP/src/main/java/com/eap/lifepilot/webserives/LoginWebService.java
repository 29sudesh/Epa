package com.eap.lifepilot.webserives;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

import com.eap.lifepilot.entities.LoginData;
import com.eap.lifepilot.utils.EAPConstants;
import com.eap.lifepilot.utils.HttpHelper;

public class LoginWebService {

	public static LoginData getLoginDetails(Context context, String username, String password) {
		final String RESPONSE_PARAM_PASSPORT = "passport";
		final String RESPONSE_PARAM_EXCEPTION = "exception";

		LoginData loginData = new LoginData();

		ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("SignInForm:alias", username));
		parameters.add(new BasicNameValuePair("SignInForm:password", password));

		String response = HttpHelper.getResponseStringPost(parameters, EAPConstants.SSO_WEB_SERVICE_LOGIN, context);

		if (response == null || response.equals("")) {
			loginData.setException("0");
			loginData.setSuccessful(false);
			return loginData;
		}

		try {
			JSONObject jsonObject = new JSONObject(response);

			if (jsonObject.has(RESPONSE_PARAM_PASSPORT)) {
				loginData.setSuccessful(true);
				loginData.setPassport(jsonObject.getString(RESPONSE_PARAM_PASSPORT));

				return loginData;
			}

			if (jsonObject.has(RESPONSE_PARAM_EXCEPTION)) {
				loginData.setSuccessful(false);
				loginData.setException(jsonObject.getString(RESPONSE_PARAM_EXCEPTION));

				return loginData;
			}

		} catch (Exception e) {
			loginData.setException("-1");
			loginData.setSuccessful(false);
			return loginData;
		}

		return loginData;
	}
}
