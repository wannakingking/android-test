package me.ctrlor.ServiceServerDemo;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.Math;

import me.ctrlor.ServiceServerDemo.IExpression.Stub;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ServiceServerDemo extends Service
{
    // Define debugger tag
    private static final String TAG = "ctrlor-random-server";

	// number1 and number2
	public static int[] randomNumbers = {0, 0};

    // The range of the number1 and number2 
	private final static int[] rangeNumbers= {11,99};

    // The random operator
	public static  char chOperator;

    // The range of the operators
	private final static char[] chOperators = {'+', '-', 'x', '/'};

    // The result of expressioin
	public static float iResult;
	
	// The expression
	public static String strExpression;
	
    // Interface object
	public static ExpressionBinder expressionBinder;
	
	// Timer
	Timer timer = new Timer();
	
	// Define interface
	public class ExpressionBinder extends Stub
	{
		@Override
		public int[] getNumbers() throws RemoteException
		{
			return randomNumbers;
		}

		@Override
		public char getOperator() throws RemoteException
		{
			return chOperator;
		}

		@Override
		public float getResult() throws RemoteException
		{
			return iResult;
		}
		
		@Override
		public String getExpression() throws RemoteException
		{
			return strExpression;
		}
		
	}

    // Main code
	@Override
	public void onCreate() 
	{
		super.onCreate();
        
        expressionBinder = new ExpressionBinder();
       

	}
	
	@Override
	public IBinder onBind( Intent intent )
	{
        // Timer to random when binding
        timer.schedule(new TimerTask()
        {
        	@Override
        	public void run()
        	{
        		randomNumbers();
        		randomOperator();
        		calcResult();
        		
        		strExpression = Integer.toString(randomNumbers[0])
        				+ chOperator + randomNumbers[1];
        		Log.d(TAG, "timer to print expression:" + strExpression
        				+ "=" + iResult);
        	}
        	
        }, 0, 500);
		
		Log.d( TAG, "onBind(),return expression" );

		return expressionBinder;
	}

	@Override
	public boolean 	onUnbind( Intent intent )
	{
		timer.cancel();
		return false;
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();

		Log.d( TAG, "onDestroy()" );
	}
	
	// Random number
	public void randomNumbers()
	{
		int[] range = rangeNumbers;
		int iMin = Math.min(range[0], range[1]); 
		int iMax = Math.max(range[0], range[1]);

		Random random = new Random();
        randomNumbers[0] = random.nextInt( ( iMax - iMin ) + 1 ) + iMin;
        randomNumbers[1] = random.nextInt( ( iMax - iMin ) + 1 ) + iMin;
        
        Log.d(TAG, "randNumbers()->randomNumbers[0]:" + randomNumbers[0] );
        Log.d(TAG, "randNumbers()->randomNumbers[1]:" + randomNumbers[1] );
        
        
	}
	
	// Random strOperator
	public void randomOperator()
	{
		char[] operators = chOperators;
		Random random = new Random();

		chOperator = operators[ random.nextInt(chOperators.length) ];

        Log.d(TAG, "randomOperator()->operator:" + chOperator );

	}

	// Calc the result of expression
	private void calcResult()
	{
		int[] num = randomNumbers;
		char operator = chOperator;
		float result = 0;

		switch(operator)
		{
		case '+':
			result = num[0] + num[1];
			break;
		
		case '-':
			result = num[0] - num[1];
			break;
			
		case 'x':
			result = num[0] * num[1];
			break;
			
		case '/':
			result = (float) num[0] / (float) num[1];
			break;
		}
		
		iResult = result;
	}
}
