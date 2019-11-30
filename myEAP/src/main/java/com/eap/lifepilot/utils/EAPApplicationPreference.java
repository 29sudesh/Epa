package com.eap.lifepilot.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class EAPApplicationPreference {

	private static final String FILE_NAME = "EAPApplicationPreference";

	public static String getString(String key, String default_value, Context context) {

		SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		return preferences.getString(key, default_value);
	}

	public static int getInt(String key, int default_value, Context context) {

		SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		return preferences.getInt(key, default_value);
	}

	public static boolean getBoolean(String key, boolean default_value, Context context) {

		SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		return preferences.getBoolean(key, default_value);
	}

	public static void set(String key, String value, Context context) {

		if (value != null)
			value = value.trim();

		SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		Editor edit = preferences.edit();
		edit.putString(key, value);
		edit.commit();
	}

	public static void set(String key, int value, Context context) {

		SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		Editor edit = preferences.edit();
		edit.putInt(key, value);
		edit.commit();
	}

	public static void set(String key, boolean value, Context context) {

		SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		Editor edit = preferences.edit();
		edit.putBoolean(key, value);
		edit.commit();
	}

	public static void remove(String key, Context context) {

		SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		Editor edit = preferences.edit();
		edit.remove(key);
		edit.commit();
	}

	public static String getString(String key, String default_value, String filename, Context context) {

		SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
		return preferences.getString(key, default_value);
	}

	public static int getInt(String key, int default_value, String filename, Context context) {

		SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
		return preferences.getInt(key, default_value);
	}

	public static boolean getBoolean(String key, boolean default_value, String filename, Context context) {

		SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
		return preferences.getBoolean(key, default_value);
	}

	public static void set(String key, String value, String filename, Context context) {

		if (value != null)
			value = value.trim();

		SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
		Editor edit = preferences.edit();
		edit.putString(key, value);
		edit.commit();
	}

	public static void set(String key, int value, String filename, Context context) {

		SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
		Editor edit = preferences.edit();
		edit.putInt(key, value);
		edit.commit();
	}

	public static void set(String key, boolean value, String filename, Context context) {

		SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
		Editor edit = preferences.edit();
		edit.putBoolean(key, value);
		edit.commit();
	}
	
	public static void remove(String key, String filename, Context context) {

		SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
		Editor edit = preferences.edit();
		edit.remove(key);
		edit.commit();
	}
}
