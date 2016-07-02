package com.ctrlor.widget1;

import com.ctrlor.widget1.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class EditTextActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("EditText Activity");
		setContentView(R.layout.activity_edit_view);
		
		// define button listener
		Button bnGetContent = (Button)findViewById(R.id.button_getContent);
		bnGetContent.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText et = (EditText)findViewById(R.id.et_editText);
				CharSequence et_value = et.getText();
				setTitle("EditText's value is:" + et_value);

			}
		});
		
	}
}