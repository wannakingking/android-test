package com.ctrlor.widget1;

import com.ctrlor.widget1.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.graphics.drawable.Drawable;

import android.support.v4.content.ContextCompat;

public class MainActivity extends Activity {

	// display a textview and button
	// ************************************************
	//TextView show = (TextView)findViewById(R.id.show_text);
	//Button press = (Button)findViewById(R.id.Click_Button);
	// ***********************************************

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		// init items description and divide line.
		String[] strFuncItem= getResources().getStringArray(R.array.FuncItem);
		String strDLine = getResources().getString(R.string.divide_line);
		
		
		// show NO.1 tip
		TextView tip1 = (TextView)findViewById(R.id.funcItem_tip1);
		tip1.setText(strFuncItem[0] + "\n" + strDLine);
		
		// display a textview and button
		// ************************************************
		Button press = (Button)findViewById(R.id.Click_Button);
		press.setOnClickListener(new Button.OnClickListener() {
			//@Override
			TextView show = (TextView)findViewById(R.id.show_text);
			public void onClick(View v) {
				show.setText("you click me");
			}
		});
		
		// *******************************************

		// show NO.2 tip
		TextView tip2 = (TextView)findViewById(R.id.funcItem_tip2);
		tip2.setText("\n" + strFuncItem[1] + "\n" + strDLine);
		
		//two text view
		// *******************************************
		String str1 = "Welecome to the Android world! this is textview 1";
		TextView mTextView1 = (TextView)findViewById(R.id.text_view01);
		TextView mTextView2 = (TextView)findViewById(R.id.text_view02);
		mTextView1.setText(str1);
		
		// get the 'blue' color
		Drawable mycolorD = ContextCompat.getDrawable(getBaseContext(), R.color.blue);
		
		// set background color
		mTextView1.setBackground(mycolorD);
		
		// set fore color
		mTextView1.setTextColor(android.graphics.Color.GREEN);
		mTextView2.setTextColor(Color.RED);
		//********************************************

		
		// show NO.3 tip
		TextView tip3 = (TextView)findViewById(R.id.funcItem_tip3);
		tip3.setText("\n" + strFuncItem[2] + "\n" + strDLine);
		
		// show colorful textview
		// ***********************************************************
		
		Button bShowTV = (Button)findViewById(R.id.show_TV);
		bShowTV.setText("show colorful textview");
		bShowTV.setOnClickListener( new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent0 = new Intent(MainActivity.this, ColorfulTextViewActivity.class);
				startActivity(intent0);
			}
		});
		
		// ************************************************************
		
		
		// show TypefaceText Activity
		// ***************************************************************
/*		LinearLayout mainLayout = (LinearLayout)findViewById(R.layout.activity_main);
		Button btShowTF =  Button(this); */
		Button btShowTF = (Button)findViewById(R.id.button_showTypeface);
		btShowTF.setText(R.string.button_showTypeface);
		/*
		btShowTF.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
				*/
		btShowTF.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent0 = new Intent(MainActivity.this, TypefaceTextActivity.class);
				startActivity(intent0);
			}
		});
	//	mainLayout.addView(btShowTF);
		
		
		
		
		//setContentView(R.layout.activity_main);
//		setContentView(btShowTF);
		// *****************************************************************
		
		// show EditText 
		// ***********************************************************
		
		Button bShowEX = (Button)findViewById(R.id.button_showEditText);
		bShowEX.setText("show EditText");
		bShowEX.setOnClickListener( new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent0 = new Intent(MainActivity.this, EditTextActivity.class);
				startActivity(intent0);
			}
		});

		// show little widgets 'allInOne' 
		// ***********************************************************
		
		Button bShowAll = (Button)findViewById(R.id.button_showAllInOne);
		bShowAll.setText("show all little widgets");
		bShowAll.setOnClickListener( new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent0 = new Intent(MainActivity.this, AllInOneActivity.class);
				startActivity(intent0);
			}
		});
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
