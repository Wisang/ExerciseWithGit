package com.lge.database;

import android.provider.BaseColumns;

public class CreateDb implements BaseColumns{
		public static final String DATE = "date";
		public static final String HOURS = "hours";
		public static final String WORKS = "works";
		public static final String _TABLE = "workRecord";
		public static final String _CREATE = "create table " + _TABLE + "("
				+ _ID + " integer primary key autoincrement, " + DATE
				+ " text not null , " + HOURS + " text not null , " + WORKS
				+ " text not null );";
		static final String GET_ALL_ORDER_BY = DATE + " DESC";
}
