package me.ctrlor.ServiceClientDemo;

import me.ctrlor.ServiceClientDemo.IExpression;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
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

	final static String TAG = "MainActivity";
	
	private TextView tv_1;
	private EditText et_1;
	private Button btn_1;
    private IExpression expressionBinder;
    
    private String strExpression;
    private int iResult;

    // Service connnector
    final ServiceConnection mConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected( ComponentName name, 
                IBinder service )
        {
            expressionBinder = IExpression.Stub.asInterface( service );
            Log.d( TAG, "in onServiceConnected" );
            thread.start();
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
		
		btn_1 = (Button) findViewById( R.id.btn_1);
		btn_1.setOnClickListener(compareResultListener); 
			
		tv_1 = (TextView) findViewById(R.id.tv_1);
		et_1 = (EditText) findViewById(R.id.et_1);


    	Intent mIntent = new Intent();
	    mIntent.setAction( "ctrlor.intent.action.EXPRESSION" );
	    bindService( mIntent, mConnection, Context.BIND_AUTO_CREATE );

		Log.d( TAG, "expressionBinder:" + expressionBinder );
		
	}



    // Handler message
    private Handler mHandler = new Handler()
    {
    	@Override
    	public void handleMessage(Message msg)
    	{
    		if(msg.what == 0x1024)
    		{
    			strExpression = (String)msg.obj;
    			iResult = msg.arg1;
    			
    			tv_1.setText(strExpression);

		    	Log.d(TAG, "strExpression:" + strExpression);
		    	Log.d(TAG, "iResult:" + iResult);
    		}
    	}
    };
    // Query expression and result
    Thread thread = new Thread(new Runnable()
    {
    	@Override
	    public void run()
	    {
    		try
    		{

    			int[] iNum = {};
    			char strOperator = ' ';
    			String strExpression = "";
		    	
		    	iNum 			= expressionBinder.getNum();
		    	strOperator 	= expressionBinder.getOperator();
		    	strExpression 	= iNum[0] + strOperator + iNum[1] + " = ";
		    	int iResult 	= expressionBinder.getResult();
		    	
		    	Message msg = new Message();
		    	msg.what = 0x1024;
		    	msg.obj = strExpression;
		    	msg.arg1 = iResult;
		    	mHandler.sendMessage(msg);
		    	
		    	Log.d(TAG, "strExpression:" + strExpression);
		    	Log.d(TAG, "iResult:" + iResult);

		    }
		    
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
	
	    }
    });

    /*
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
        tv.setText( "expressioin" );
        //tv.setText( getExpression() );

        et = new EditText( this );
        et.setText( "result" );
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
   */ 
    // The listener of the button
    private Button.OnClickListener compareResultListener = 
    		new Button.OnClickListener()
    {
    	@Override
    	public void onClick( View v )
    	{
    		try
    		{
	    		
	    		int iInputValue = Integer.parseInt(et_1.getText().toString());
	    		
	    		if( iInputValue == iResult )
	    		{
	    			btn_1.setText( "Yes: " + iResult );
	    		}
	    		else
	    		{
	    			btn_1.setText( "No: " + iResult );
	    		}

    		}
    		catch (Exception e)
    		{
    			e.printStackTrace();
    		}
    		
	    		
    	}
    };
    
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
			this.unbindService( mConnection );
		Log.d( TAG, "onDestroy" );
	}
}
