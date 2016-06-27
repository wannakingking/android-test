package com.ctrlor.widget1;

import com.ctrlor.widget1.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import android.graphics.Color;
import android.widget.LinearLayout;

public class ColorfulTextViewActivity extends Activity {
	// testing colorful "textview", define object need to be used.
	// ************************************************
	private LinearLayout myLayout;
//	private boolean bmyLayout = false;
	private LinearLayout.LayoutParams layoutP;
	private int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
	private TextView black_TV, blue_TV, cyan_TV, dkgray_TV,
				gray_TV, green_TV, ltgray_TV, magenta_TV, red_TV,
				transparent_TV, white_TV, yellow_TV;
	private Button bReturn;
	// *************************************************	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		buildColorfulTV();
			
	}
	
	
	// testing colorful TextView
	// ********************************
	
	public void buildColorfulTV() {

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

		// set a button to return
		bReturn = new Button(this);
		myLayout.addView(bReturn, layoutP);
		bReturn.setText("return");
		bReturn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//myLayout.setVisibility(View.INVISIBLE);
				//setContentView(R.layout.activity_main);
			}
		});
		
		// *******************************************
	}
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