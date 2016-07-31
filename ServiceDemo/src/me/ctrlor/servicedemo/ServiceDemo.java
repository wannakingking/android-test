package me.ctrlor.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ServiceDemo extends Service
{
	final static String TAG = "ctrlor";

	@Override
	public IBinder onBind( Intent intent )
	{
		Log.v( TAG,  "onBind");
		return null;
	}
	
	@Override
	public void onCreate()
	{
		Log.v( TAG,  "onCreate");
	}

	@Override
	public void onStart( Intent intent, int startId )
	{
		Log.v( TAG,  "onStart");
	}
/*
	@Override
	public int onStartCommand( Intent intent, int flags, int startId )
	{
		Log.v( TAG, "onStartCommand" );
		return START_STICKY;
	}
*/	
	@Override
	public boolean onUnbind( Intent intent )
	{
		Log.v( TAG, "onUnbind" );
		return false;
	}
	
	@Override
	public void onRebind( Intent intent )
	{
		Log.v( TAG, "onRebind" );
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.v( TAG, "onDestroy" );
	}
}