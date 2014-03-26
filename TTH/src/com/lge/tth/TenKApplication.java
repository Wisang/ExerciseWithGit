package com.lge.tth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.lge.database.CreateDb;
import com.lge.database.DbOpenHelper;

import android.annotation.SuppressLint;
import android.app.Application;
import android.database.Cursor;
import android.util.Log;

public class TenKApplication extends Application{
	
	Calendar date;
	DailyEffort dailyEffort;
		
	int selectedMonth;
	
	Map<Calendar, DailyEffort> dailyEfforts;
	
	//WorkDB data;
	
	int getSelectedMonth()
	{
		return selectedMonth;
	}
	
	void setSelectedMonth(int month)
	{
		selectedMonth = month;
	}
	
	@Override
	public void onCreate() {
		//dailyEffort = new DailyEffort();
		dailyEfforts = new HashMap<Calendar, DailyEffort>();
		Calendar now = Calendar.getInstance();
		selectedMonth = now.get(Calendar.MONTH);
		
		getDataFromDBToMap();
	}

	@SuppressLint("SimpleDateFormat")
	private void getDataFromDBToMap() {
		DbOpenHelper workDb = new DbOpenHelper(this);
		
		try {				
			Cursor cursor = workDb.getData();
			
			try {

				while(cursor.moveToNext()) {
					Calendar date = new GregorianCalendar();
					DailyEffort dailyEffort = new DailyEffort();

					String dateStr;
					
					dateStr = cursor.getString(cursor.getColumnIndex(CreateDb.DATE)); 
								         
			        try
			        {
			            SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd");
			            date.setTime(formatter.parse(dateStr));
			        }
			        catch(ParseException e)
			        {
			            e.printStackTrace();
			        }
					
			        Log.i("date", dateStr); 
			        
			        dailyEffort.hoursDevoted = cursor.getString(cursor.getColumnIndex(CreateDb.HOURS));
			        dailyEffort.workContents = cursor.getString(cursor.getColumnIndex(CreateDb.WORKS));
					
					dailyEfforts.put(date, dailyEffort);

					
				}
			} finally {
				cursor.close();
			}
			
		} finally {		
			workDb.close();
		}
	}
	
	public Map<Calendar, DailyEffort> getRecords() {
		return dailyEfforts;
	}
}
