package com.ctrlor.bundleex;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		Button btSelectCity = (Button) findViewById( R.id.btn_select_city_register);
		btSelectCity.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick( View v ) {
				Intent mIntent = new Intent( RegisterActivity.this, 
						SelectCityActivity.class );

				// Start the SelectCityActivity, and waiting for the result code '0'.
				startActivityForResult( mIntent, 0 );
			}
		});
		Button btRegister = (Button) findViewById( R.id.btn_register );
		btRegister.setOnClickListener( new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				EditText etUsername = 
						(EditText) findViewById( R.id.et_username_register);
				EditText etPassword = 
						(EditText) findViewById( R.id.et_password_register);
				RadioButton rbGender = 
						(RadioButton) findViewById( R.id.radio_male_gender_register);
				
				String strGender = rbGender.isChecked() ? "male" : "female";
				Person mPerson = new Person(
						etUsername.getText().toString(),
						etPassword.getText().toString(),
						strGender);
				
				// Create a bundle object
				Bundle mBundle = new Bundle();
				mBundle.putSerializable( "person", mPerson );
				
				// Create the Intent
				Intent mIntent = new Intent( RegisterActivity.this, 
						ResultActivity.class );
				mIntent.putExtras( mBundle );
				startActivity( mIntent );
				
			}

		});
	}
	
	@Override
	public void onActivityResult( int requestCode, 
			int resultCode, Intent mIntent ) {
		
		// If request code and result code all are '0'.
		if( requestCode == 0 && resultCode == 0 ) {
			Bundle mBundle = mIntent.getExtras();
			String strResult = mBundle.getString("city");
			
			EditText etCity = (EditText) findViewById( R.id.et_input_city_register);
			etCity.setText(strResult);

		}
	}
}
