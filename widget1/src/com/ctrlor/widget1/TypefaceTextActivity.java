package com.ctrlor.widget1;

import com.ctrlor.widget1.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.graphics.Color;
import android.graphics.Typeface;

public class TypefaceTextActivity extends Activity {
	// TypeFace TextView
	
	// define wrap_content
	final int WRAP_CONTENT = LinearLayout.LayoutParams.WRAP_CONTENT;
	
	// TypeFace TextView
	private TextView bold_TV, bold_italic_TV, default_TV, 
				default_bold_TV, italic_TV, monospace_TV, 
				normal_TV, sans_serif_TV, serif_TV;
	
	// LinearLayout
	private LinearLayout myLayout;
	
	// LinearLayout Params
	private LinearLayout.LayoutParams layoutP;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// new LinearLayout
		myLayout = new LinearLayout(this);
		
		// set the Orientation
		myLayout.setOrientation(LinearLayout.VERTICAL);
		
		// set background
		myLayout.setBackgroundResource(R.drawable.background);
		
		// show myLayout
		setContentView(myLayout);
		
		// init LinearLayout Params
		layoutP = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
		
		// construct TextView
		constructTextView();
		
		// set Text Size
		setTextSizeOf();
		
		// set TextView Text
		setTextViewContent();
		
		// set font style
		setFontStyle();
		
		// set font color
		setFontColor();
		
		// add TextView to LinearLayout
		addTextView();
	}
		
	// construct TextView
	public void constructTextView() {
		bold_TV 		= new TextView(this);
		bold_italic_TV 	= new TextView(this);
		default_TV 		= new TextView(this);
		default_bold_TV = new TextView(this);
		italic_TV 		= new TextView(this);
		monospace_TV 	= new TextView(this);
		normal_TV 		= new TextView(this);
		sans_serif_TV 	= new TextView(this);
		serif_TV 		= new TextView(this);
	}
	
	// set Text Size
	public void setTextSizeOf() {
		bold_TV.setTextSize(24.0f);
		bold_italic_TV.setTextSize(24.0f);
		default_TV.setTextSize(24.0f);
		default_bold_TV.setTextSize(24.0f);
		italic_TV.setTextSize(24.0f);
		monospace_TV.setTextSize(24.0f);
		normal_TV.setTextSize(24.0f);
		sans_serif_TV.setTextSize(24.0f);
		serif_TV.setTextSize(24.0f);
	}
	
	// set TextView Text
	public void setTextViewContent() {
		bold_TV.setText("bold");
		bold_italic_TV.setText("bold_italic");
		default_TV.setText("default");
		default_bold_TV.setText("default_bold");
		italic_TV.setText("italic");
		monospace_TV.setText("monospace");
		normal_TV.setText("normal");
		sans_serif_TV.setText("sans_serif");
		serif_TV.setText("serif");
	}
	// set font style
	public void setFontStyle() {
		bold_TV.setTypeface(null, Typeface.BOLD);
		bold_italic_TV.setTypeface(null,Typeface.BOLD_ITALIC);
		default_TV.setTypeface(Typeface.DEFAULT);
		default_bold_TV.setTypeface(Typeface.DEFAULT_BOLD);
		italic_TV.setTypeface(null,Typeface.ITALIC);
		monospace_TV.setTypeface(Typeface.MONOSPACE);
		normal_TV.setTypeface(null, Typeface.NORMAL);
		sans_serif_TV.setTypeface(Typeface.SANS_SERIF);
		serif_TV.setTypeface(Typeface.SERIF);
	}
	
	// set font color
	public void setFontColor() {
		bold_TV.setTextColor(Color.BLACK);
		bold_italic_TV.setTextColor(Color.CYAN);
		default_TV.setTextColor(Color.GREEN);
		default_bold_TV.setTextColor(Color.MAGENTA);
		italic_TV.setTextColor(Color.RED);
		monospace_TV.setTextColor(Color.WHITE);
		normal_TV.setTextColor(Color.YELLOW);
		sans_serif_TV.setTextColor(Color.GRAY);
		serif_TV.setTextColor(Color.LTGRAY);
	}
	
	// add TextView to LinearLayout
	public void addTextView() {
		myLayout.addView(bold_TV, layoutP);
		myLayout.addView(bold_italic_TV, layoutP);
		myLayout.addView(default_TV, layoutP);
		myLayout.addView(default_bold_TV, layoutP);
		myLayout.addView(italic_TV, layoutP);
		myLayout.addView(monospace_TV, layoutP);
		myLayout.addView(normal_TV, layoutP);
		myLayout.addView(sans_serif_TV, layoutP);
		myLayout.addView(serif_TV, layoutP);
	}
		
}