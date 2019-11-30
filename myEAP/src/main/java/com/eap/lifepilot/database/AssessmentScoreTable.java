package com.eap.lifepilot.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.eap.lifepilot.entities.AssessmentScore;

public class AssessmentScoreTable {

	public static final String DATABASE_TABLE = "AssessmentScoreTable";

	public static final String KEY_ASSESSMENTSCORE_ID = "AssessmentScoreID";
	public static final String KEY_QUIZ_TYPE = "key_quiz_type";
	public static final String KEY_SCORE = "key_score";
	public static final String KEY_LIFE_PILOT_ANSWERS = "key_life_pilot_answers";
	public static final String KEY_DATE = "key_date";
	public static final String KEY_USERNAME = "key_username";


	private SQLiteDatabase database;

	public static final String CREATE_TABLE =
			"CREATE TABLE IF NOT EXISTS "
					+ DATABASE_TABLE + " ( "
					+ KEY_ASSESSMENTSCORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , "
					+ KEY_SCORE + " VARCHAR, "
					+ KEY_LIFE_PILOT_ANSWERS + " VARCHAR, "
					+ KEY_USERNAME + " VARCHAR, "
					+ KEY_QUIZ_TYPE + " INTEGER, "
					+ KEY_DATE + " VARCHAR )";
	
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + DATABASE_TABLE;
	
	
	private final Context context;

	public AssessmentScoreTable(Context context) {
		this.context = context;
	}

	public AssessmentScoreTable(Context context, SQLiteDatabase database) {
		this.context = context;
		this.database = database;
	}

	public AssessmentScoreTable open() throws SQLException {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		database = databaseHelper.getWritableDatabase();

		return this;
	}

	public void close() {
		database.close();
	}

	public long insert(AssessmentScore assessmentScore) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_SCORE, assessmentScore.getScore());
		cv.put(KEY_LIFE_PILOT_ANSWERS, assessmentScore.getLifePilotAnswersWithDelimeters());
		cv.put(KEY_DATE, assessmentScore.getDate());
		cv.put(KEY_QUIZ_TYPE, assessmentScore.getQuizType());
		cv.put(KEY_USERNAME, assessmentScore.getUsername());
		

		long id = database.replace(DATABASE_TABLE, null, cv);

		return id;
	}

	public long delete(AssessmentScore assessmentScore) {
		long count = 0;

		String whereClause = KEY_ASSESSMENTSCORE_ID + " = ? ";
		String[] whereArgs = new String[] { assessmentScore.getId() };

		count = database.delete(DATABASE_TABLE, whereClause, whereArgs);

		return count;
	}


	
	public ArrayList<AssessmentScore> getAssessmentScores(String username) {
		ArrayList<AssessmentScore> assessmentScores = new ArrayList<AssessmentScore>();

		String whereClause = KEY_USERNAME + " = ? ";
		String[] whereArgs = new String[] { username };
		
		String orderBy = KEY_ASSESSMENTSCORE_ID + " DESC";
		
		Cursor cursor = database.query(DATABASE_TABLE, null, whereClause, whereArgs, null, null, orderBy);

		if (cursor != null && cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				int index_ID = cursor.getColumnIndex(KEY_ASSESSMENTSCORE_ID);
				int index_score = cursor.getColumnIndex(KEY_SCORE);
				int index_quiz_type = cursor.getColumnIndex(KEY_QUIZ_TYPE);
				int index_date = cursor.getColumnIndex(KEY_DATE);
				int index_life_pilot = cursor.getColumnIndex(KEY_LIFE_PILOT_ANSWERS);
				
				AssessmentScore assessmentScore = new AssessmentScore();
				assessmentScore.setId(cursor.getString(index_ID));
				assessmentScore.setScore(cursor.getString(index_score));
				assessmentScore.setQuizType(cursor.getInt(index_quiz_type));
				assessmentScore.setDate(cursor.getString(index_date));
				assessmentScore.setLifePilotAnswersWithDelimeters(cursor.getString(index_life_pilot));
				
				assessmentScores.add(assessmentScore);
				cursor.moveToNext();
			}
		}
		cursor.close();

		return assessmentScores;
	}
	
	
}
