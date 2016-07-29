package me.ctrlor.intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	final static String ACTION_CTRLOR_TEST = 
			"ctrlor.intent.action.TEST";
	final static String CATEGORY_CTRLOR_TEST = 
			"ctrlor.intent.category.TEST";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setTitle("Intent sender");
		
		// A system intent: ACTION_DIAL
		Button button1 = (Button) findViewById( R.id.btn_send_intent_call );
		button1.setOnClickListener( new Button.OnClickListener() {
			
			@Override
			public void onClick( View v ) {
				try {
					final String strSender = "Button1 in the Main Activity";
					Intent mIntent = new Intent( Intent.ACTION_DIAL );
					mIntent.putExtra( "sender",  strSender );
					startActivity( mIntent );
				} catch ( Exception e ) {
					e.printStackTrace();
				}
			}
		});

		// User-defined intent: ACTION_CTRLOR_TEST
		Button button2 = (Button) findViewById( R.id.btn_send_intent_test );
		button2.setOnClickListener( new Button.OnClickListener() {
			
			@Override
			public void onClick( View v ) {
				try {
					final String strSender = "Button2 in the Main Activity";

					Intent mIntent = new Intent(); 
					mIntent.setAction( MainActivity.ACTION_CTRLOR_TEST );
					mIntent.addCategory( MainActivity.CATEGORY_CTRLOR_TEST );
					mIntent.putExtra( "sender", strSender  );

					startActivity( mIntent );
				} catch( Exception e ) {
					e.printStackTrace();
				}
			}
		});

		// Send a broadcast
		Button button3 = (Button) findViewById( R.id.btn_send_intent_broadcast_test );
		button3.setOnClickListener( new Button.OnClickListener() {
			
			@Override
			public void onClick( View v ) {
				try {
					final String strSender = "Button3 in the Main Activity";
					Intent mIntent = new Intent();
					mIntent.setAction( "ctrlor.intent.action.BROADCAST_RECEIVER_TEST" );
					mIntent.putExtra( "sender",  strSender );

					sendBroadcast( mIntent );
				} catch ( Exception e ) {
					e.printStackTrace();
				}
			}
		});

	}
}
