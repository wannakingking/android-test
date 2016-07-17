package com.ctrlor.eventcallback;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Event callback");

        Button button1 = (Button) findViewById(R.id.btn_keydown_event);
        button1.setOnClickListener( new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                startActivity( new Intent(MainActivity.this,
                        KeydownEventActivity.class));
                }
        });

        Button button2 = (Button) findViewById(R.id.btn_touch_event);
        button2.setOnClickListener( new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                startActivity( new Intent(MainActivity.this,
                       TouchEventActivity.class));
                }
        });

	}
}
