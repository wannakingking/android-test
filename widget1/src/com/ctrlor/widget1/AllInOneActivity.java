package com.ctrlor.widget1;

import com.ctrlor.widget1.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class AllInOneActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_in_one);
		
		//init 'tvOutput' Textview
		TextView tvOutput = (TextView)findViewById(R.id.textView_output);
		
		// test CheckBox
		testCheckBox();
		
		// test RadioGroup
		testRadioGroup();
		
		// test spinner
		testSpinner();
		
		// test time and date picker
		testTimeDataPicker();
		
		// test progress bar
		testProgressBar();
		
		// test seek bar
		testSeekBar();
		
	}
		// test CheckBox
		public void testCheckBox() {
			
		}
		
		// test RadioGroup
		public void testRadioGroup() {
			
		}
		
		// test spinner
		public void testSpinner() {
			
		}
		
		// test time and date picker
		public void testTimeDataPicker() {
			
		}
		
		// test progress bar
		public void testProgressBar() {
			
		}
		
		// test seek bar
		public void testSeekBar() {
			
		}
}