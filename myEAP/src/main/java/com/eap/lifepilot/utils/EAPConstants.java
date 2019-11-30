package com.eap.lifepilot.utils;

public class EAPConstants {

	// URLs for Web pages
	public static final String URL_CASCADE_TEXT_FILE = "http://cascadecenters.com/Cascade.txt";
	public static final String URL_CASCADE_CENTER = "http://www.cascadecenters.com";
	public static final String URL_FACEBOOK = "https://www.facebook.com/CascadeEAP";
	public static final String URL_TWITTER = "https://twitter.com/CascadeEAP";
	public static final String URL_YOU_TUBE = "https://www.youtube.com/user/cascadeeap";
	public static final String URL_SCHEDULE_APPOINTMENT = "http://www.cascadecenters.com/index.php?tray=content&tid=top10522&cid=102gp2";
	public static final String URL_LIFE_ADVANTAGE = "https://cascade.personaladvantage.com/welcome.jsp";
	public static final String URL_LIFE_MY_EAP_SEVICES = "https://cascade.personaladvantage.com/section.jsp?module=section_005";
	public static final String URL_EPA_MEMBER_SIDE_TOP_MENU = "https://cascade.personaladvantage.com/portal/landing?a=1";
	public static final String URL_TERMS_CONDITIONS = "https://cascade.personaladvantage.com/notice.jsp?name=privacy.jsp";
	
	// Web services URLs
	public static final String WEB_SERVICE_LOGIN = "https://cascade.personaladvantage.com/portal/welcome/json"; // RDP old login endpoint 9-5-19
	public static final String SSO_WEB_SERVICE_LOGIN = "https://cascade-gateway.lifeadvantages.net/saml2/idp/SSOService.php?spentityid=https%3A%2F%2Fsaml-01.personaladvantage.com%2Fsp&cookieTime=1565128267&RelayState=ss%3Amem%3A52b93ee27df33665fea2d63b423ef1193935e084e807852341d1d656a23523d9";

	public static final String WEB_SERVICE_REGISTRATION = "https://cascade.personaladvantage.com/portal/welcome/json/register";
	public static final String WEB_SERVICE_FORGOT_PASSWORD = "https://cascade.personaladvantage.com/portal/welcome/json/recover";
	public static final String WEB_SERVICE_LIFE_ADVANTAGE = "https://cascade.personaladvantage.com/welcome.jsp";
	
	// File Url
	public static final String PATH_CASCADE_TEXT_FILE = "/EAP";
	public static final String CASCADE_TEXT_FILE_NAME = "Cascade";
	
	// Intent Keys used
	public static final String INTENT_KEY_FROM_MY_ASSESSMENTS_LIST_BOOLEAN = "intent_key_from_my_assessments_list_boolean";
	public static final String INTENT_KEY_WEBVIEW_URL = "intent_key_webview_url";
	public static final String INTENT_KEY_DIALOG_CONTENT = "intent_key_dialog_content";
	public static final String INTENT_KEY_QUESTION_NO = "intent_key_question_no";
	public static final String INTENT_KEY_SCORE = "intent_key_score";
	public static final String INTENT_KEY_FILE_URL = "intent_key_file_url";
	public static final String INTENT_KEY_POSITION = "intent_key_position";
	public static final String INTENT_KEY_SELECTED_FRAGMENT = "intent_key_selected_fragment";
	public static final String INTENT_KEY_TITLE = "intent_key_title";
	public static final String INTENT_SELECTED_QUIZ = "intent_selected_quiz";
	public static final String INTENT_KEY_LIFE_PILOT_ANSWERS = "intent_key_life_pilot_answers";
	public static final String INTENT_KEY_USERNAME = "intent_key_username";
	public static final String INTENT_KEY_PASSWORD = "intent_key_password";
	
	// Quiz Types Constants
	public static final int QUIZ_DEPRESSION = 1;
	public static final int QUIZ_RELATIONSHIP = 2;
	public static final int QUIZ_LIFE_PILOT = 3;
	public static final int QUIZ_ALCOHOL = 4;
	
	// Maximum Scores for all the quiz
	public static final int QUIZ_MAX_SCORE_DEPRESSION = 27;
	public static final int QUIZ_MAX_SCORE_RELATIONSHIP = 55;
	public static final int QUIZ_MAX_SCORE_LIFE_PILOT = 6;
	public static final int QUIZ_MAX_SCORE_ALCOHOL = 40;
	
	// Regular Expressions
	public static final String REGEX_USERNAME = "^[\\w\\\\.@ ]{3,32}$";
	public static final String REGEX_PASSWORD = "^.{3,32}$";
	public static final String REGEX_COMPANY = "^.{1,256}$";
	public static final String REGEX_FIRST_NAME = "|^.{1,32}$";
	public static final String REGEX_LAST_NAME = "|^.{1,32}$";
	public static final String REGEX_PASSPHRASE_ANSWER = "^.{1,32}$";
//	public static final String REGEX_EMAIL = "|^[\\w\\\\.%+]+@[az09.\\\\]+\\\\.[az]{2,4}$";
	public static final String REGEX_EMAIL = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	public static final String REGEX_EMAI1 = "|^[\\w\\.%+]+@[az09.\\]+\\.[az]{2,4}$";
	
	// Shared Preference Keys
	public static final String PREFS_IS_REMEMBER_ME = "prefs_is_remember_me";
	public static final String PREFS_USERNAME = "prefs_username";
	public static final String PREFS_LOGGED_IN_USERNAME = "prefs_logged_in_username";
	public static final String PREFS_PASSWORD = "prefs_password";
	
	public static final int FORGOT_PASSWORD_SCREEN_USERNAME = 1;
	public static final int FORGOT_PASSWORD_SCREEN_QUESTION = 2;
	public static final int FORGOT_PASSWORD_SCREEN_PASSWORD = 3;
}
