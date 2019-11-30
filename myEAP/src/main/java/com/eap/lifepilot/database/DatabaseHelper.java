package com.eap.lifepilot.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "Eap.sqlite";
	public static final int DATABASE_VERSION = 1;
	private static String DB_PATH = "";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		DB_PATH = context.getFilesDir() + context.getPackageName() + "/databases/";
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createTables(db);
	}

	public void dropTables(SQLiteDatabase db) {
		db.execSQL(IDCardTable.DROP_TABLE);
		db.execSQL(AssessmentScoreTable.DROP_TABLE);
	}

	public void createTables(SQLiteDatabase db) {
		db.execSQL(IDCardTable.CREATE_TABLE);
		db.execSQL(AssessmentScoreTable.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		dropTables(db);
		createTables(db);
	}

	public boolean databaseExists() {
		SQLiteDatabase checkDB = null;
		try {
			String myPath = DB_PATH + DATABASE_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
			if (checkDB != null) {
				checkDB.close();
				return true;
			} else {
				// database does not exist
				return false;
			}
		} catch (Exception e) {
			// database does not exist
			return false;
		}
	}
}
