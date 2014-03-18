package com.eom.tenthousandhour;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputEfforts extends Activity implements OnClickListener {
	private static final int DISTANCE_TO_MASTERY = 10000;
	Button btn;
	TextView showDistance;
	EditText hours;
	EditText workContents;
	TextView showWorks;
	private DailyEffort dailyEffort;
	private String contentsHeader;
	private TextView daySummary;
	private StringBuffer contents = new StringBuffer();
	
	private Calendar date = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input);
		btn = (Button) findViewById(R.id.getDistanceBtn);
		btn.setOnClickListener(this);
		
		dailyEffort = ((TenKApplication) getApplication()).getRecords().get(date);

		showDistance = (TextView) findViewById(R.id.remainingDistanceText);
		hours = (EditText) findViewById(R.id.inputHour);

		workContents = (EditText) findViewById(R.id.inputContents);
		
		fillDaySummary();
	}

	private void fillDaySummary() {
		daySummary = (TextView)findViewById(R.id.daySummary);
		
		TenKApplication applicationData = (TenKApplication) getApplication();

		Intent intent = getIntent();
		if (intent != null) {
			date = (Calendar) intent.getSerializableExtra("date");
		}

		applicationData.date = date;

		if (null != dailyEffort)
			fillContents();
		
		if(applicationData.getRecords().containsKey(date))
        	//if(now.getTime().getMonth() == selectedMonth)
			daySummary.setText(contents);
        else
        	daySummary.setText("no Data");
	}

	private void fillContents() {
		DateFormat df = DateFormat.getDateInstance();
        contentsHeader = "³¯Â¥: " + df.format(date.getTime()) + '\n';
        contents.append(contentsHeader);
        contents.append("Hours Devoted: " + dailyEffort.hoursDevoted + '\n');
        contents.append("Work Contents: " + dailyEffort.workContents + '\n');
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onClick(View v) {
		String inputHours = hours.getText().toString();
		int hoursWorkedToday = Integer.parseInt(inputHours);
		
		dailyEffort.hoursDevoted = inputHours;
		dailyEffort.workContents = workContents.getText().toString();

		String result = "You are now "
				+ (DISTANCE_TO_MASTERY - hoursWorkedToday)
				+ " hours away from the Mastery!\n";
		showDistance.setText(result);
		
		Intent intent = getIntent();
        
		if (intent != null) {
        	date = (Calendar) intent.getSerializableExtra("date");
        }
		
		DailyEffort drCopied = new DailyEffort();
		
		try {
			drCopied = (DailyEffort) dailyEffort.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		Map<Calendar, DailyEffort> mapData = ((TenKApplication)getApplication()).getRecords();
		
		mapData.put(date, drCopied);
		
		saveInDB(drCopied);
		
		initializeData(dailyEffort);
		
				
//		Intent in = new Intent(this, GVCalendarActivity.class);
//		startActivity(in) ;
	}

	private void initializeData(DailyEffort dailyEffort) {
		dailyEffort.hoursDevoted="";
		dailyEffort.workContents="";
	}

	@SuppressLint({ "SimpleDateFormat", "ShowToast" })
	private void saveInDB(DailyEffort drCopied) {
		WorkDB workDb = new WorkDB(this);
		ContentValues values = new ContentValues();
		
		Date dateDB = date.getTime(); 
		SimpleDateFormat simpleDate = new SimpleDateFormat("yy-MM-dd");
		
		values.clear();
		values.put(WorkDB.C_DATE, simpleDate.format(dateDB));
		values.put(WorkDB.C_HOURS, drCopied.hoursDevoted);
		values.put(WorkDB.C_WORK_CONTENTS, drCopied.workContents);
		
		workDb.insert(values);
		workDb.close();
	}
}
