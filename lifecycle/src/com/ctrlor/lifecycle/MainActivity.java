package com.ctrlor.lifecycle;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.util.Log;

public class MainActivity extends Activity {

    final String TAG = "--ctrlor--";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        
        Log.d(TAG, "-----onCreate-----");
	    Button mbtStart = (Button) findViewById(R.id.btn_start_new_activity);
	    mbtStart.setOnClickListener( new Button.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                // Start second activity
	                Intent mIntent = new Intent(
	                      MainActivity.this, SecondActivity.class);
	                startActivity(mIntent);
	            }
		});
	
	    Button mbtQuit = (Button) findViewById(R.id.btn_quit);
	    mbtQuit.setOnClickListener( new Button.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                // Quit
	                MainActivity.this.finish();
	            }
	    });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "-----onStart-----");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "-----onRestart-----");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "-----onResume-----");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "-----onStop-----");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "-----onDestory-----");
    }

}
