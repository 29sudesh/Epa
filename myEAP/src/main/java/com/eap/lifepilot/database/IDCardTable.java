package com.eap.lifepilot.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.eap.lifepilot.entities.IDCard;

public class IDCardTable {

	public static final String DATABASE_TABLE = "IDCardTable";

	public static final String KEY_IDCARDTABLE_ID = "IDCardTableID";
	public static final String KEY_NAME = "key_name";
	public static final String KEY_DESCRIPTION = "key_description";
	public static final String KEY_IMAGE_URL = "key_image_url";


	private SQLiteDatabase database;

	public static final String CREATE_TABLE =
			"CREATE TABLE IF NOT EXISTS "
					+ DATABASE_TABLE + " ( "
					+ KEY_IDCARDTABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , "
					+ KEY_NAME + " VARCHAR, "
					+ KEY_DESCRIPTION + " VARCHAR, "
					+ KEY_IMAGE_URL + " VARCHAR )";
	
	public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + DATABASE_TABLE;
	
	
	private final Context context;

	public IDCardTable(Context context) {
		this.context = context;
	}

	public IDCardTable(Context context, SQLiteDatabase database) {
		this.context = context;
		this.database = database;
	}

	public IDCardTable open() throws SQLException {
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		database = databaseHelper.getWritableDatabase();

		return this;
	}

	public void close() {
		database.close();
	}

	public long insert(IDCard idCard) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, idCard.getName());
		cv.put(KEY_DESCRIPTION, idCard.getDescription());
		cv.put(KEY_IMAGE_URL, idCard.getImageLocation());

		long id = database.replace(DATABASE_TABLE, null, cv);

		return id;
	}

	public long delete(IDCard idCard) {
		long count = 0;

		String whereClause = KEY_IDCARDTABLE_ID + " = ? ";
		String[] whereArgs = new String[] { idCard.getId() };

		count = database.delete(DATABASE_TABLE, whereClause, whereArgs);

		return count;
	}


	public ArrayList<String> getURLs(String username) {
		ArrayList<String> imageUrls = new ArrayList<String>();

		String[] columns = null;
		String selection = KEY_NAME + " = ? ";
		String[] selectionArgs =  new String[]{username};
		String groupBy = null;
		String having = null;
		String orderBy = KEY_IMAGE_URL + " DESC";

		Cursor cursor = database.query(DATABASE_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy);

		if (cursor != null && cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				int index_image_url = cursor.getColumnIndex(KEY_DESCRIPTION);
				imageUrls.add(cursor.getString(index_image_url));
				cursor.moveToNext();
			}
		}
		cursor.close();

		return imageUrls;
	}
	
	public ArrayList<String> getUpdatedTime(String username) {
		ArrayList<String> imageUrls = new ArrayList<String>();

		String[] columns = null;
		String selection = KEY_NAME + " = ? ";
		String[] selectionArgs =  new String[]{username};
		String groupBy = null;
		String having = null;
		String orderBy = KEY_IMAGE_URL + " DESC";

		Cursor cursor = database.query(DATABASE_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy);

		if (cursor != null && cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				int index_image_url = cursor.getColumnIndex(KEY_IMAGE_URL);
				imageUrls.add(cursor.getString(index_image_url));
				cursor.moveToNext();
			}
		}
		cursor.close();

		return imageUrls;
	}
	
	public ArrayList<IDCard> getIDCards() {
		ArrayList<IDCard> idCards = new ArrayList<IDCard>();


		Cursor cursor = database.query(DATABASE_TABLE, null, null, null, null, null, null);

		if (cursor != null && cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				int index_ID = cursor.getColumnIndex(KEY_IDCARDTABLE_ID);
				int index_name = cursor.getColumnIndex(KEY_NAME);
				int index_desc = cursor.getColumnIndex(KEY_DESCRIPTION);
				int index_url = cursor.getColumnIndex(KEY_IMAGE_URL);
				
				IDCard idCard = new IDCard();
				idCard.setId(cursor.getString(index_ID));
				idCard.setName(cursor.getString(index_name));
				idCard.setDescription(cursor.getString(index_desc));
				idCard.setImageLocation(cursor.getString(index_url));
				
				idCards.add(idCard);
				cursor.moveToNext();
			}
		}
		cursor.close();

		return idCards;
	}
	
	
}
