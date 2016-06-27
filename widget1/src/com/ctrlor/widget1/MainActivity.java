package com.ctrlor.widget1;

import com.ctrlor.widget1.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import android.graphics.Color;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	// display a textview and button
	// ************************************************
	//TextView show = (TextView)findViewById(R.id.show_text);
	//Button press = (Button)findViewById(R.id.Click_Button);
	// ***********************************************

	// testing colorful "textview", define object need to be used.
	// ************************************************
	private LinearLayout myLayout;
	private LinearLayout.LayoutParams layoutP;
	private int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
	private TextView black_TV, blue_TV, cyan_TV, dkgray_TV,
				gray_TV, green_TV, ltgray_TV, magenta_TV, red_TV,
				transparent_TV, white_TV, yellow_TV;
	// *************************************************
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// display a textview and button
		// ******************************************
		
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

		//two text view
		// *******************************************
		String str1 = "Welecome to the Android world! this is textview 1";
		TextView mTextView1 = (TextView)findViewById(R.id.text_view01);
		mTextView1.setText(str1);
		//********************************************

		// testing colorful TextView
		// *******************************************
		
		// init a 'Linearlayout' object
		myLayout = new LinearLayout(this);
		
		// set the orientation is VERTICAL
		myLayout.setOrientation(LinearLayout.VERTICAL);
		
		// set the layout background
		myLayout.setBackgroundResource(R.drawable.background);
		
		// load the 'myLayout'
		setContentView(myLayout);
		
		// init the LinearLayout Params, used to add TextView
		layoutP = new LinearLayout.LayoutParams(WC,WC);
		
		// construct the TextView 
		constructTextView();
		
		// add TextView to the Layout
		addTextView();
		
		// set the TextView's color
		setTextViewColor();
		
		// set the content of the TextView
		setTextViewContent();

		// *******************************************
		
		
		
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
	
	// testing colorful TextView
	// ********************************
	
	// construct TextView
	public void constructTextView() {
		black_TV 	= new TextView(this);
		blue_TV 	= new TextView(this);
		cyan_TV		= new TextView(this);
		dkgray_TV	= new TextView(this);
		gray_TV		= new TextView(this);
		green_TV	= new TextView(this);
		ltgray_TV	= new TextView(this);
		magenta_TV	= new TextView(this);
		red_TV		= new TextView(this);
		white_TV	= new TextView(this);
		yellow_TV 	= new TextView(this);
		transparent_TV =new TextView(this);
	}
	
	// add TextView to the layout
	public void addTextView() {
		myLayout.addView(black_TV, layoutP);
		myLayout.addView(blue_TV, layoutP);
		myLayout.addView(cyan_TV, layoutP);
		myLayout.addView(dkgray_TV, layoutP);
		myLayout.addView(gray_TV, layoutP);
		myLayout.addView(green_TV, layoutP);
		myLayout.addView(ltgray_TV, layoutP);
		myLayout.addView(magenta_TV, layoutP);
		myLayout.addView(red_TV, layoutP);
		myLayout.addView(white_TV, layoutP);
		myLayout.addView(yellow_TV, layoutP);
		myLayout.addView(transparent_TV, layoutP);
	}
	
	// set the content of the TextView
	public void setTextViewContent() {
		black_TV.setText("black");
		blue_TV.setText("blue");
		cyan_TV.setText("cyan");
		dkgray_TV.setText("dkgray");
		gray_TV.setText("gray");
		green_TV.setText("green");
		ltgray_TV.setText("ltgray");
		magenta_TV.setText("magenta");
		red_TV.setText("red");
		white_TV.setText("white");
		yellow_TV.setText("yellow");
		transparent_TV.setText("transparent");
	}
	
	// set the color of the TextView
	public void setTextViewColor() {
		black_TV.setTextColor(Color.BLACK);
		blue_TV.setTextColor(Color.BLUE);
		cyan_TV.setTextColor(Color.CYAN);
		dkgray_TV.setTextColor(Color.DKGRAY);
		gray_TV.setTextColor(Color.GRAY);
		green_TV.setTextColor(Color.GREEN);
		ltgray_TV.setTextColor(Color.LTGRAY);
		magenta_TV.setTextColor(Color.MAGENTA);
		red_TV.setTextColor(Color.RED);
		white_TV.setTextColor(Color.WHITE);
		yellow_TV.setTextColor(Color.YELLOW);
		transparent_TV.setTextColor(Color.TRANSPARENT);
	}
		


	// ********************************
}
