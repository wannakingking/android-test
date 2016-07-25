package com.ctrlor.bundleex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		TextView tvUsername = (TextView) findViewById(
				R.id.tv_username_result);
		TextView tvPassword = (TextView) findViewById(
				R.id.tv_password_result);
		TextView tvGender = (TextView) findViewById(
				R.id.tv_gender_result);
		
		Intent mIntent = getIntent();
		Person mPerson = (Person) mIntent.getSerializableExtra(
				"person");
		
		tvUsername.setText("username:" + mPerson.getName());
		tvPassword.setText("Password:" + mPerson.getPassword());
		tvGender.setText("Gender:" + mPerson.getGender());
	}
}
		