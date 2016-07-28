package me.ctrlor.intentdemo;

import java.util.Set;

import android.app.Activity;
import android.os.Bundle;

import android.widget.TextView;

public class IntentReceiverActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_receiver);
		
		setTitle("Intent Receiver");
		
		String strAction = getIntent().getAction();
		Set<String> strCategory = getIntent().getCategories();
		
		TextView tvShow = (TextView) findViewById( R.id.tv_intent_receiver );
		tvShow.setText(
				"action:" + "\t" 	+ strAction 	+ "\n" +
				"category:\t" 	+ strCategory 	+ "\n" 
				);
	}
}
