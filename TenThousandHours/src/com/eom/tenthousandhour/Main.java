package com.eom.tenthousandhour;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity implements OnClickListener{
	private static final int DISTANCE_TO_MASTERY = 10000;
	Button btn;
	TextView tv;
	EditText hours;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		btn = (Button)findViewById(R.id.getDistanceBtn);
		btn.setOnClickListener(this);
		
		tv = (TextView)findViewById(R.id.remainingDistanceText);
		hours = (EditText)findViewById(R.id.inputHour);
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
		
		String result = "You are now " + (DISTANCE_TO_MASTERY-hoursWorkedToday) + " hours away from the Mastery!"; 
		tv.setText(result);
	}
}
