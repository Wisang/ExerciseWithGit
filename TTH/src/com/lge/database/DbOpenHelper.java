package com.lge.database;

import com.lge.database.CreateDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper {
	private static final String DATABASE_NAME = "efforts.db";
	private static final int DATABASE_VERSION = 2;

//	private DatabaseHelper mDBHelper;
	private Context mCtx;
	
	private class DatabaseHelper extends SQLiteOpenHelper {
		
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		public DatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CreateDb._CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			 db.execSQL("DROP TABLE IF EXISTS "+CreateDb._TABLE);
	            onCreate(db);
		}
	}
	
	private final DatabaseHelper mDBHelper;
	
	public DbOpenHelper(Context context) {
//		this.mCtx = context;
		mDBHelper = new DatabaseHelper(context);
	}
	
//	public DbOpenHelper open() throws SQLException{
//        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
//        mDB = mDBHelper.getWritableDatabase();
//        return this;
//    }
	
	public void insert(ContentValues values) {
		SQLiteDatabase mDB = mDBHelper.getWritableDatabase();
		mDB.insertOrThrow(CreateDb._TABLE, null, values);
		mDB.close();
	}

	public Cursor getData() {
		SQLiteDatabase mDB = mDBHelper.getReadableDatabase();
		return mDB.query(CreateDb._TABLE, null, null, null, null, null, CreateDb.GET_ALL_ORDER_BY);
	}
 
    public void close(){
    	mDBHelper.close();
    }
}
