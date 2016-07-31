package me.ctrlor.servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

import android.util.Log;

public class MainActivity extends Activity 
{

	final static String TAG = "ctrlor-MainActivity";
	private boolean is_bound = false;
	
	// Create the Intent of the service
	final Intent mIntent = new Intent();

	private ServiceConnection mConnection = 
			new ServiceConnection() 
			{
				public void onServiceConnected( ComponentName className, IBinder service)
				{
					Log.v( TAG, "onSericeConnected" );
				}
		
				public void onServiceDisconnected( ComponentName className )
				{
					Log.v( TAG, "onSericeDisconnected" );
				}
			};
		
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setTitle( "MainActivity" );
		
		// Init the Intent of the service
		mIntent.setAction( "ctrlor.intent.action.SERVICE_1" );

		// Start service
		Button button1 = (Button) findViewById( R.id.btn_1 );
		button1.setOnClickListener( new Button.OnClickListener () 
		{
			@Override
			public void onClick( View v ) 
			{
				startService( mIntent );
				Toast.makeText( MainActivity.this, "startService", 
						Toast.LENGTH_LONG ).show();
					Log.v( TAG, "startService" );
			}
		});

		// Stop service
		Button button2 = (Button) findViewById( R.id.btn_2 );
		button2.setOnClickListener( new Button.OnClickListener () 
		{
			@Override
			public void onClick( View v ) 
			{
				stopService( mIntent );
				Toast.makeText( MainActivity.this, "stopService", 
						Toast.LENGTH_LONG ).show();
				Log.v( TAG, "stopService" );
			}
		});

		// Bind service
		Button button3 = (Button) findViewById( R.id.btn_3 );
		button3.setOnClickListener( new Button.OnClickListener () 
		{
			@Override
			public void onClick( View v ) 
			{
				if( !is_bound )
				{
					bindService( mIntent, mConnection, Context.BIND_AUTO_CREATE );
					is_bound = true;
					Log.v( TAG, "bindService" );
				}
			}
		});

		// Unbind service
		Button button4 = (Button) findViewById( R.id.btn_4 );
		button4.setOnClickListener( new Button.OnClickListener () 
		{
			@Override
			public void onClick( View v ) 
			{
				if( is_bound )
				{
					unbindService( mConnection ); 
					is_bound = false;
					Log.v( TAG, "unbindService" );
				}
			}
		});

	}

}
