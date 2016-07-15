package com.ctrlor.eventlistener;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;

public class ItselfListenerActivity extends Activity 
	implements OnClickListener{

    EditText mEditText;
    Button mButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_itself_listener);

        // Activity itself listener
        mEditText = (EditText) findViewById(R.id.edittext_itself_listener);
        mButton = (Button) findViewById(R.id.button_itself_listener);
        mButton.setOnClickListener(this); 

    }

    public void onClick(View v) {
        mEditText.setText("the button is pressed!");
    }
}
