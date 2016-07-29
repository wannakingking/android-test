package me.ctrlor.intentdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TestBroadcastReceiver extends BroadcastReceiver 
{
	public static final String BR_TEST = 
			"ctrlor.intent.action.BROADCAST_RECEIVER_TEST";

	@Override
	public void onReceive( Context context, Intent intent )
	{
		try {
			if( intent.getAction() == BR_TEST )
			{
				String strSender = "Test Broadcast Receiver";
	
				Intent mIntent = new Intent();
				mIntent.setAction( "ctrlor.intent.action.TEST" );
				mIntent.addCategory( "ctrlor.intent.category.TEST" );
				mIntent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
				mIntent.putExtra( "sender", strSender );
	
				context.startActivity( mIntent );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}