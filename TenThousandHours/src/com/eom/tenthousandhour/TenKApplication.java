package com.eom.tenthousandhour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Application;
import android.database.Cursor;
import android.util.Log;

public class TenKApplication extends Application{
	
	Calendar date;
<<<<<<< HEAD
	//DailyEffort dailyEffort;
=======
	DailyEffort dailyEffort;
>>>>>>> 64a40516ccce0d06793b7b308cc3a49bee8a9d00
		
	int selectedMonth;
	
	Map<Calendar, DailyEffort> dailyEfforts;
	
<<<<<<< HEAD
	//WorkDB data;
	
=======
>>>>>>> 64a40516ccce0d06793b7b308cc3a49bee8a9d00
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
<<<<<<< HEAD
		//dailyEffort = new DailyEffort();
		dailyEfforts = new HashMap<Calendar, DailyEffort>();
		Calendar now = Calendar.getInstance();
		selectedMonth = now.get(Calendar.MONTH);
		
		//data = new WorkDB(this);
=======
		dailyEffort = new DailyEffort();
		dailyEfforts = new HashMap<Calendar, DailyEffort>();
		Calendar now = Calendar.getInstance();
//		selectedMonth = now.getTime().getMonth();
		selectedMonth = now.get(Calendar.MONTH);
		
>>>>>>> 64a40516ccce0d06793b7b308cc3a49bee8a9d00
		getDataFromDBToMap();
	}

	@SuppressLint("SimpleDateFormat")
	private void getDataFromDBToMap() {
<<<<<<< HEAD
		WorkDB workDb = new WorkDB(this);
		
		try {				
			Cursor cursor = workDb.getData();
=======
		WorkContents data = new WorkContents(this);

		try {				
			Cursor cursor = data.getData();
>>>>>>> 64a40516ccce0d06793b7b308cc3a49bee8a9d00
			
			try {

				while(cursor.moveToNext()) {
					Calendar date = new GregorianCalendar();
					DailyEffort dailyEffort = new DailyEffort();

					String dateStr;
					
<<<<<<< HEAD
					dateStr = cursor.getString(cursor.getColumnIndex(WorkDB.C_DATE)); 
=======
					dateStr = cursor.getString(cursor.getColumnIndex(WorkContents.C_DATE)); 
>>>>>>> 64a40516ccce0d06793b7b308cc3a49bee8a9d00
								         
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
			       
			    
			        
<<<<<<< HEAD
			        dailyEffort.hoursDevoted = cursor.getString(cursor.getColumnIndex(WorkDB.C_HOURS));
			        dailyEffort.workContents = cursor.getString(cursor.getColumnIndex(WorkDB.C_WORK_CONTENTS));
=======
			        dailyEffort.hoursDevoted = cursor.getString(cursor.getColumnIndex(WorkContents.C_HOURS));
			        dailyEffort.workContents = cursor.getString(cursor.getColumnIndex(WorkContents.C_WORK_CONTENTS));
>>>>>>> 64a40516ccce0d06793b7b308cc3a49bee8a9d00
					
					dailyEfforts.put(date, dailyEffort);

					
				}
			} finally {
				cursor.close();
			}
			
		} finally {		
<<<<<<< HEAD
			workDb.close();
=======
			data.close();
>>>>>>> 64a40516ccce0d06793b7b308cc3a49bee8a9d00
		}
	}
	
	public Map<Calendar, DailyEffort> getRecords() {
		return dailyEfforts;
	}
<<<<<<< HEAD
	
=======
>>>>>>> 64a40516ccce0d06793b7b308cc3a49bee8a9d00
}
