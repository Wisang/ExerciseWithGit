package com.lge.tth;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.lge.database.CreateDb;
import com.lge.database.DbOpenHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputData extends Activity implements OnClickListener{
	EditText inputHour;
	EditText inputWork;
	
	Button saveDataButton;
	TextView distanceToGo;
	TextView daySummary;
	
	private static final String TAG = "inputDataActivity";
    private DbOpenHelper mDb;
    private Cursor mCursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input_data);
		inflateGUI();
		
		mDb = new DbOpenHelper(this);
		Cursor cursor = mDb.getData();
		cursor.moveToFirst();  
		
		while(cursor.moveToNext() && "2014.3.19".equals(cursor.getString(1)))
			daySummary.setText(cursor.getString(3));
	}

	@Override
	public void onClick(View v) {
		StringBuffer contents = fillContents();
//		daySummary.setText(contents);
		
//        mDb.open();
         
        ContentValues values = new ContentValues();
        values.put(CreateDb.DATE, "2014.3.19");
        values.put(CreateDb.HOURS, "7");
        values.put(CreateDb.WORKS, inputWork.getText().toString());

        mDb.insert(values);
		mDb.close();
	}

	private StringBuffer fillContents() {
		StringBuffer contents = new StringBuffer();
		DateFormat df = DateFormat.getDateInstance();
        contents.append(df.format(Calendar.getInstance().getTime()) + '\n');
        contents.append("Hours Devoted today: " + inputHour.getText() + '\n');
        contents.append("Work Contents today: " + inputWork.getText() + '\n');
		return contents;
	}
	
	private void inflateGUI() {
		inputHour = (EditText)findViewById(R.id.inputHour);
		inputWork = (EditText)findViewById(R.id.inputContents);
		distanceToGo = (TextView)findViewById(R.id.remainingDistanceText);
		daySummary = (TextView)findViewById(R.id.daySummary);
		saveDataButton = (Button)findViewById(R.id.getDistanceBtn);
		saveDataButton.setOnClickListener(this);
	}
}
