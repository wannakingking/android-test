package com.ctrlor.widget1;

import com.ctrlor.widget1.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// display a textview and button
//		TextView show = (TextView)findViewById(R.id.show_text);
		 Button press = (Button)findViewById(R.id.Click_Button);
		
		press.setOnClickListener(new Button.OnClickListener() {
			//@Override
			TextView show = (TextView)findViewById(R.id.show_text);
			public void onClick(View v) {
				show.setText("you click me");
			}
		});
		
		//two text view
		String str1 = "Welecome to the Android world! this is textview 1";
		TextView mTextView1 = (TextView)findViewById(R.id.text_view01);
		mTextView1.setText(str1);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
