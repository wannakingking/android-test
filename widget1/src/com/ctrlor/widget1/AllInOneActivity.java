package com.ctrlor.widget1;

import com.ctrlor.widget1.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.ToggleButton;
import android.widget.CheckedTextView;
//import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.DatePicker;
//import android.widget.ScrollView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.graphics.Color;

public class AllInOneActivity extends Activity {


		// init the widget object
		private CheckBox checkBox1;
		private CheckBox checkBox2;
		//private CheckedTextView checkTV;
		private ToggleButton btToggle;
		private CheckedTextView tvChecked;
		//private RadioGroup radioGroup;
		private RadioButton radio1;
		private RadioButton radio2;
		private Button btPress;
		private Spinner spinnerList;
		private SeekBar seekBar;
		private ProgressBar progressBar1;
		private ProgressBar progressBar2;
		private TimePicker timePicker;
		private DatePicker datePicker;
		private TextView tvOutput;
		
		private boolean bool = false;
		private String strOutput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_in_one);
		
		// init the widget object
		checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
		checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
		btToggle = (ToggleButton)findViewById(R.id.toggleButton1);
		tvChecked= (CheckedTextView)findViewById(R.id.checkedTextView1);
		radio1 = (RadioButton)findViewById(R.id.radio1);
		radio2 = (RadioButton)findViewById(R.id.radio2);
		btPress = (Button)findViewById(R.id.button1);
		spinnerList = (Spinner)findViewById(R.id.spinner1);
		seekBar = (SeekBar)findViewById(R.id.seekBar1);
		progressBar1 = (ProgressBar)findViewById(R.id.progressBar1);
		progressBar2 = (ProgressBar)findViewById(R.id.progressBar2);
		timePicker = (TimePicker)findViewById(R.id.timePicker1);	
		datePicker = (DatePicker)findViewById(R.id.datePicker1);
		tvOutput = (TextView)findViewById(R.id.textView_output);
		
		// repose the press button
		btPress.setOnClickListener( new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				pressButton();
			}
		});
		 
		// test spinner
		testSpinner();
		
		// test date and time picker
		testDateTimePicker();
		
		// test process bar
		testProcessBar();
		
		// 

	}
		// press the button
		private void pressButton() {
			strOutput = "";
			
			// test CheckBox
			if(checkBox1.isChecked()) {
				strOutput = strOutput + "checkBox1: is checked" + "\n";
			}
			if (checkBox2.isChecked()) {
				strOutput = strOutput + "checkBox2: is checked" + "\n";
			}
			else if(!checkBox1.isChecked() && !checkBox2.isChecked()){
				strOutput = strOutput + "no checkBox: is checked" + "\n";
			}
			
			
			// test the toggle button
			if(btToggle.isChecked()) {
				strOutput = strOutput + "Toggle Button: is ON" + "\n";
			}
			else {
				strOutput = strOutput + "Toggle Button: is ON" + "\n";
			}
			
			// test checked textview
			//tvChecked.setChecked(!bool);
			tvChecked.toggle();
			if(tvChecked.isChecked()) {
				tvChecked.setBackgroundColor(Color.BLUE);
				strOutput = strOutput + "Checked TextView: is checked" + "\n";
			}
			else {
				tvChecked.setBackgroundColor(Color.TRANSPARENT);
				strOutput = strOutput + "Checked TextView: is unchecked" + "\n";
			}


			// test the radio button
			if(radio1.isChecked()) {
				strOutput = strOutput + "radio1: is checked" + "\n";
			}
			if(radio2.isChecked()) {
				strOutput = strOutput + "radio2: is checked" + "\n";
			}
			else if(!radio1.isChecked() && !radio2.isChecked()) {
				strOutput = strOutput + "no radio: is checked" + "\n";
			}
			
			tvOutput.setText(strOutput);
			
		}
		// spinner list
		private void testSpinner() {
			// implement from java
			final String[] strJava = {"java-1", "java-2", "java-3", "java-4"};
			// implement from xml
			final String strXml[] = getResources().getStringArray(R.array.xmldata);
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
					android.R.layout.simple_spinner_item, strJava);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnerList.setAdapter(adapter);
		}
			
		// test date and time picker
		private void testDateTimePicker() {
			
			// test time picker
			timePicker.setIs24HourView(true);

			// test date picker
			datePicker.init(1990, 11, 13, new DatePicker.OnDateChangedListener() {
				
				@Override
				public void onDateChanged(DatePicker view, int year, int monthOfYear,
						int dayOfMonth) {
					// TODO Auto-generated method stub
					strOutput = "";
					strOutput = strOutput + "Date picker:year-" + year +", month-" +
							++monthOfYear + ", day-" + dayOfMonth + "\n";
					tvOutput.setText(strOutput);
					
				}
			});
			
		}

		// test process bar
		private void testProcessBar() {
			progressBar1.setMax(100);
			progressBar2.setMax(100);
			final int totalProgressTime = 100;
			final Thread t = new Thread() {
				@Override
				public void run() {
					int jumpTime = 0;
					
					while(true) {
						try {
							sleep(200);
							jumpTime += 1;
							progressBar2.setProgress(jumpTime);
							if(jumpTime >= totalProgressTime) {
								jumpTime = 0;
							}
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			t.start();
			
		}
}