package me.ctrlor.intentdemo;

import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class IntentReceiverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_receiver);
		
		setTitle("Intent Receiver");
		
		// Display some elements of the incoming intent.
		Intent mIntent = getIntent();
		String strAction = mIntent.getAction();
		Set<String> strCategory = mIntent.getCategories();
		String strSender = mIntent.getStringExtra( "sender" );
		
		TextView tvShow = (TextView) findViewById( R.id.tv_intent_receiver );
		tvShow.setText( 
				"action:" 		+ strAction 	+ "\n" +
				"category:"  	+ strCategory 	+ "\n" +
				"intent sender:"+ strSender		);
	}
}
