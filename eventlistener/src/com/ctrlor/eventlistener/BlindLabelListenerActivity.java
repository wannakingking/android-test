package com.ctrlor.eventlistener;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

public class BlindLabelListenerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blind_label_listener);
    }

    public void clickHandler(View v) {
        EditText mEditText = (EditText) findViewById(R.id.edittext_blind_label_listener);
        mEditText.setText("the button is pressed!");
    }
}


