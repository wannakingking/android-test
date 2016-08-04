package me.ctrlor.ServiceClientDemo;

import me.ctrlor.ServiceClientDemo.IExpression;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends Activity {

	final static String TAG = "ctrlor-MainActivity";
	
	private TextView tv;
	private EditText et;
	private Button btn;
    private IExpression expressionBinder;
    
    private boolean is_bound = false;

    // Service connnector
    private ServiceConnection mConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected( ComponentName name, 
                IBinder service )
        {
        	try
        	{
            expressionBinder = IExpression.Stub.asInterface( service );
            Log.d( TAG, "in onServiceConnected" );
            Log.d( TAG, "expressionBinder:" + expressionBinder );
            Log.d( TAG, "getExpression():" + getExpression() );
            Log.d( TAG, "getResult():" + expressionBinder.getResult() );
        	}
        	catch (Exception e)
        	{ 
        		e.printStackTrace();
        	}
        }

        @Override
        public void onServiceDisconnected( ComponentName name )
        {
            expressionBinder = null;
        }
    };


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.activity_main );

		
		
		Button button1 = (Button) findViewById( R.id.btn_1);
		button1.setOnClickListener( new Button.OnClickListener() 
		{
			@Override
			public void onClick( View v)
			{
			
		try
		{
		Intent mIntent = new Intent();
		mIntent.setAction( "ctrlor.intent.action.EXPRESSION" );
			//startService( mIntent );
			boolean flag = bindService( mIntent, mConnection, Context.BIND_AUTO_CREATE );
			Log.d( TAG, "mConnection:" + mConnection );
			Log.d( TAG, "bindService:" + flag );

			Log.d( TAG, "expressionBinder:" + expressionBinder );
			//Log.d( TAG, "getNum()" + expressionBinder.getNum() );
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
			}
		});
/*
		if( !is_bound )
		{
			bindService( mIntent, mConnection, Service.BIND_AUTO_CREATE );
			is_bound = true;
		}
		Log.v( TAG, "bindService over" );

		buildView();
		Log.d( TAG, "expression:" + getExpression() );
		Log.d( TAG, "build view over" );
		
		try{
			Log.d( TAG, "onCreate-getNum():" 	+ expressionBinder.getNum() + "" );
			Log.d( TAG, "onCreate-getOperator():" + expressionBinder.getOperator() + "" );
			Log.d( TAG, "onCreate-getResult():" 	+ expressionBinder.getResult() + "" );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
*/
	}

	// Building layout and widget views.
    public void buildView() 
    {
        TableLayout tabLayout = new TableLayout( this );
        setContentView( tabLayout );

        TableRow.LayoutParams lp = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT );

        final TableRow tableRow = new TableRow( this );
        
        tv = new TextView( this );
        tv.setText( "textview" );
        tv.setText( getExpression() );

        et = new EditText( this );
        et.setText( "edittext" );
        et.setInputType( InputType.TYPE_CLASS_NUMBER );

        btn = new Button( this );
        btn.setText( "Check" );
        btn.setOnClickListener( compareResultListener );

        tableRow.addView( tv, lp );
        tableRow.addView( et, lp );
        tableRow.addView( btn, lp );

        tabLayout.addView( tableRow, new TableLayout.LayoutParams( 
        		TableLayout.LayoutParams.WRAP_CONTENT,
        		TableLayout.LayoutParams.WRAP_CONTENT ));
    }
    
    // The listener of the button
    private Button.OnClickListener compareResultListener = 
    		new Button.OnClickListener()
    {
    	@Override
    	public void onClick( View v )
    	{
    		try
    		{
	    		int iResult = expressionBinder.getResult();
	    		int iInputValue = Integer.parseInt(et.getText().toString());
	    		
	    		if( iInputValue == iResult )
	    		{
	    			btn.setText( "Yes: " + iResult );
	    		}
	    		else
	    		{
	    			btn.setText( "No: " + iResult );
	    		}

    		}
    		catch (Exception e)
    		{
    			e.printStackTrace();
    		}
    		
	    		
    	}
    };
    
    // Expression string
	private String getExpression() 

    {
	    int[] iNum = {};
	    char strOperator = ' ';
	    String strExpression = "";
	    	
	    try
	    {
	    	iNum 			= expressionBinder.getNum();
	    	strOperator 	= expressionBinder.getOperator();
	    	strExpression 	= iNum[0] + strOperator + iNum[1] + " = ";
	    }
	    
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }

    	return strExpression;
	    	
    }
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
			this.unbindService( mConnection );
		Log.d( TAG, "onDestroy" );
	}
}
