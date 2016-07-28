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
		
		Button button1 = (Button) findViewById( R.id.btn_send_intent_call );
		button1.setOnClickListener( new Button.OnClickListener() {
			
			@Override
			public void onClick( View v ) {
				try {
					Intent mIntent = new Intent( Intent.ACTION_DIAL );
					//mIntent.addCategory( MainActivity.CATEGORY_CTRLOR_TEST );
					startActivity( mIntent );
				} catch ( Exception e ) {
					e.printStackTrace();
				}
			}
		});

		Button button2 = (Button) findViewById( R.id.btn_send_intent_test );
		button2.setOnClickListener( new Button.OnClickListener() {
			
			@Override
			public void onClick( View v ) {
				try {
					Intent mIntent = new Intent(); 
					mIntent.setAction( MainActivity.ACTION_CTRLOR_TEST );
					mIntent.addCategory( MainActivity.CATEGORY_CTRLOR_TEST );
					startActivity( mIntent );
				} catch( Exception e ) {
					e.printStackTrace();
				}
			}
		});
	}
}
