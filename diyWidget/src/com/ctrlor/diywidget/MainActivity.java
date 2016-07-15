package com.ctrlor.diywidget;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {

	private ListSelectView mListSelectView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mListSelectView = (ListSelectView) findViewById(R.id.lsvTest);
		mListSelectView.setHeader("header");
		mListSelectView.setContent("content");
	}
}
