package com.ctrlor.eventlistener;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class AnonymousListenerActivity extends Activity {

    EditText mEditText;
    Button mButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anonymous_listener);

        // inner class listener

        mEditText = (EditText) findViewById(R.id.edittext_anonymous_listener);
        mButton = (Button) findViewById(R.id.button_anonymous_listener);
        mButton.setOnClickListener( new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEditText.setText("the button is pressed!");
                }
        });
    }
}
