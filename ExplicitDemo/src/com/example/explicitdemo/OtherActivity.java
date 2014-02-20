package com.example.explicitdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OtherActivity extends Activity {

	static String EXTRA_MESSAGE = "initial message";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		TextView tv = (TextView)findViewById(R.id.msg);
		tv.setText(getIntent().getStringExtra(EXTRA_MESSAGE));
	}
}
