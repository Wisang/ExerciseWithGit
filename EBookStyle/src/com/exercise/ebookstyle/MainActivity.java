package com.exercise.ebookstyle;

import java.util.Date;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

	Button btn;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.activity_main);

		btn = (Button) findViewById(R.id.button);
		btn.setOnClickListener(this);
		updateTime();
	}

	@Override
	public void onClick(View view) {
		updateTime();
	}

	private void updateTime() {
		btn.setText(new Date().toString());
	}

}
