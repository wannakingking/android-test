package me.ctrlor.ServiceServerDemo;

import java.util.Random;

import me.ctrlor.ServiceServerDemo.IExpression.Stub;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ServiceServerDemo extends Service
{
    // Define debugger tag
    final static String TAG = "ctrlor-Service";

	// number1 and number2
	public static int[] iNum = {0, 0};

    // The range of the number1 and number2 
	private final static int[] iNumRange = {11,99};

    // The random operator
	public static  char strOperator;

    // The range of the operators
	private final static char[] strOperators = {'+', '-', 'x', '/'};

    // The result of expressioin
	public static int iResult;
	
    // Interface object
	public ExpressionBinder expressionBinder;
	
	// Define interface
	public class ExpressionBinder extends Stub
	{
		@Override
		public int[] getNum() throws RemoteException
		{
			return iNum;
		}

		@Override
		public char getOperator() throws RemoteException
		{
			return strOperator;
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
        buildRandom();
        calcResult();

	}
	
	@Override
	public IBinder onBind( Intent intent )
	{
		
        buildRandom();
        calcResult();
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
	
	// Create random expression
	public void buildRandom()
	{
		Random random = new Random();
        iNum[0] = random.nextInt( ( iNumRange[1]-iNumRange[0] )+1 ) + iNumRange[0];
        iNum[1] = random.nextInt( ( iNumRange[1]-iNumRange[0] )+1 ) + iNumRange[0];
        strOperator = strOperators[ random.nextInt(4) ];
        
        Log.d(TAG, "iNum[0]:" + iNum[0] );
        Log.d(TAG, "iNum[1]:" + iNum[1] );
        Log.d(TAG, "strOperator:" + strOperator );
	}
	

	// Calc the result of expression
	public void calcResult()
	{
		switch( strOperator )
		{
		case '+':
			iResult = iNum[0] + iNum[1];
			break;
		
		case '-':
			iResult = iNum[0] - iNum[1];
			break;
			
		case 'x':
			iResult = iNum[0] * iNum[1];
			break;
			
		case '/':
			iResult = iNum[0] / iNum[1];
			break;
		}
		
		// Print expression
		Log.d( TAG, iNum[0] + " " + strOperator + " " + iNum[1] + " = " +
				iResult );
	}
}
