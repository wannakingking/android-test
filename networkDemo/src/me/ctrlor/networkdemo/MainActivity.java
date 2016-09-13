package me.ctrlor.networkdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener
{

	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	Button btn6;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Network Demo");
		
		btn1 = (Button) findViewById(R.id.btn_url_connection);
		btn1.setOnClickListener(this);

		btn2 = (Button) findViewById(R.id.btn_url_connection);
		btn2.setOnClickListener(this);

		btn3 = (Button) findViewById(R.id.btn_url_connection);
		btn3.setOnClickListener(this);

		btn4 = (Button) findViewById(R.id.btn_url_connection);
		btn4.setOnClickListener(this);

		btn5 = (Button) findViewById(R.id.btn_url_connection);
		btn5.setOnClickListener(this);

		btn6 = (Button) findViewById(R.id.btn_url_connection);
		btn6.setOnClickListener(this);
		
		btn1.callOnClick();
		 
	}
	
	@Override
	public void onClick(View v)
	{
		Intent mIntent = null;
		switch(v.getId())
		{
		case R.id.btn_url_connection:
			mIntent = new Intent(this, UrlConnectionMethod.class);
			break;

		case 2:
			mIntent = new Intent(this, UrlConnectionMethod.class);
			break;

		case 3:
			mIntent = new Intent(this, UrlConnectionMethod.class);
			break;

		case 4:
			mIntent = new Intent(this, UrlConnectionMethod.class);
			break;

		case 5:
			mIntent = new Intent(this, UrlConnectionMethod.class);
			break;

		case 6:
			mIntent = new Intent(this, UrlConnectionMethod.class);
			break;
		
		default:
			break;
		}
		
		if(mIntent != null)
		{
			startActivity(mIntent);
		}
	}
}
