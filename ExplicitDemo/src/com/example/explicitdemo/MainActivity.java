package com.example.explicitdemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void showOther(View v) {
		Intent other = new Intent(this, OtherActivity.class);
		other.putExtra(OtherActivity.EXTRA_MESSAGE, "Message from Main");
		startActivity(other);
		
//		startActivity(new Intent(this, SecondActivity.class));
	}

}
