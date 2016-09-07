package me.ctrlor.ServiceClientDemo;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

import me.ctrlor.ServiceServerDemo.IExpression;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

	final static String TAG = "ctrlor-random-client";
	
	private TextView tv_1;
    private static IExpression expressionBinder;
    private ExpressionServiceConnection mConnection;
    

    // New class ExpressionServiceConnection 
    class ExpressionServiceConnection implements ServiceConnection
    {
        @Override
        public void onServiceConnected( ComponentName name, 
                IBinder service )
        {
            expressionBinder = IExpression.Stub.asInterface( service );
            Log.d( TAG, "onServiceConnected()" );
            Log.d( TAG, "expressionBinder:" + expressionBinder);
            mHandler.sendEmptyMessage(0x1024);
            //buildView();
        }

        @Override
        public void onServiceDisconnected( ComponentName name )
        {
            expressionBinder = null;
            Log.d( TAG, "onServiceDisconnected()." );
        }
    }



	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.activity_main );
		
			
		tv_1 = (TextView) findViewById(R.id.tv_1);

		mConnection = new ExpressionServiceConnection();

    	Intent mIntent = new Intent();
	    mIntent.setAction( "ctrlor.intent.action.EXPRESSION" );
	    bindService( mIntent, mConnection, Context.BIND_AUTO_CREATE );

		// Refresh every 3s
	    new Timer().schedule(new TimerTask()
	    {
	    	@Override
	    	public void run()
	    	{
	    		mHandler.sendEmptyMessage(0x1024);
	    	}
	    }, 0, 1000);
		
	}


    
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();

		unbindService( mConnection );
		mConnection = null;

		Log.d( TAG, "onDestroy()" );
	}
	
    // Handler message
    private Handler mHandler = new Handler()
    {
    	@Override
    	public void handleMessage(Message msg)
    	{

    		if(msg.what == 0x1024)
    		{
    			
    			try
    			{

    				char c 				 = expressionBinder.getOperator();
    				String strExpression = expressionBinder.getExpression();
    				float dResult  		 = expressionBinder.getResult();
    				
    				BigDecimal bg = new BigDecimal(dResult);

    				/**
    				 * 1) If c = '/', the result will be turn to a decimals which has 
    				 * 		two numbers after the point.
    				 * 2) If c != '/', the result will be turn to a integer.
    				 */
		    		tv_1.setText(strExpression + "=" + ( 
		    			(c != '/') ? bg.setScale(0, BigDecimal.ROUND_HALF_UP)
		    					: bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()
		    					));
		
    			}
		    		catch (Exception e)
		    		{
		    			e.printStackTrace();
		    			Log.d(TAG, "throw remote exception");
		    		}
    			}
    	}
    };

}
