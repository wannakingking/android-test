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
    private static final String TAG = "ctrlor-service";

	// number1 and number2
	public static int[] randomNumbers = {0, 0};

    // The range of the number1 and number2 
	private final static int[] rangeNumbers= {11,99};

    // The random operator
	public static  char chOperator;

    // The range of the operators
	private final static char[] chOperators = {'+', '-', 'x', '/'};

    // The result of expressioin
	public static int iResult;
	
    // Interface object
	public ExpressionBinder expressionBinder;
	
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
		public int getResult() throws RemoteException
		{
			return iResult;
		}
	}

    // Main code
	@Override
	public void onCreate() 
	{
		super.onCreate();
        
        expressionBinder = new ExpressionBinder();
       
        // Timer to random
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
        	@Override
        	public void run()
        	{
        		randomNumbers();
        		randomOperator();
        		calcResult();
        	}
        	
        }, 0, 500);

	}
	
	@Override
	public IBinder onBind( Intent intent )
	{
		
		Log.d( TAG, "onBind(),return expression" );

		return expressionBinder;
	}

	@Override
	public boolean 	onUnbind( Intent intent )
	{
		return false;
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d( TAG, "onDestroy()" );
	}
	
	// Random number
	public int[] randomNumbers()
	{
		int[] range = rangeNumbers;
		int iMin = Math.min(range[0], range[1]); 
		int iMax = Math.max(range[0], range[1]);

		int[] tmpNum = {0, 0};
		Random random = new Random();
        tmpNum[0] = random.nextInt( ( iMax - iMin ) + 1 ) + iMin;
        tmpNum[1] = random.nextInt( ( iMax - iMin ) + 1 ) + iMin;
        
        Log.d(TAG, "randNumbers()->tmpNum[0]:" + tmpNum[0] );
        Log.d(TAG, "randNumbers()->tmpNum[1]:" + tmpNum[1] );
        
        return tmpNum;
	}
	
	// Random strOperator
	public char randomOperator()
	{
		char[] operators = chOperators;
		Random random = new Random();

		char c = operators[ random.nextInt(4) ];

        Log.d(TAG, "randomOperator()->operator:" + c );

		return c;
	}

	// Calc the result of expression
	private int calcResult()
	{
		int[] num = randomNumbers;
		char operator = chOperator;
		int result = 0;

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
			result = num[0] / num[1];
			break;
		}
		
		// Print expression
		Log.d( TAG + "-print expression", num[0] + " " + operator 
				+ " " + num[1] + " = " + result );
		
		return result;
	}
}
