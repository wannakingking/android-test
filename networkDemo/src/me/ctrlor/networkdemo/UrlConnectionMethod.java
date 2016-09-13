package me.ctrlor.networkdemo;


import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;


public class UrlConnectionMethod extends Activity 
{
	
	private static final String TAG = "ctrlor-networkDemo-UrlConnectionMethod";

	private Spinner spinner;
	private ImageView mImageView;
	private ArrayAdapter<String> adapter;
	
	private int mIndex;
	private static final String[][] strList = {
		{"1", "2", "3", "4", "5"},
		{"http://192.168.0.11/test/pic/1.jpg", 
		"http://192.168.0.11/test/pic/2.jpg", 
		"http://192.168.0.11/test/pic/3.jpg", 
		"http://192.168.0.11/test/pic/4.jpg", 
		"http://192.168.0.11/test/pic/5.jpg"} 
	};

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.url_connection_method);
		setTitle("Url connection method");

		mImageView = (ImageView) findViewById(R.id.imageview);

		adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_item, strList[0]);

		// Set the style of the spinner
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setAdapter(adapter);
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id)
			{
				
				mIndex = position;
				//mHandler.post(r);
				new Thread(r).start();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
				mIndex = 0;
				//mHandler.post(r);
				new Thread(r).start();
			}
			
		});
	}
	
	private final Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			try
			{
				// Apply the image.
				if(msg.what == 0x1)
				{
		            mImageView.setImageBitmap((Bitmap)msg.obj);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	};
	
	// Thread to get the image from the Internet.
	private final Runnable r = new Runnable()
	{
		@Override
		public void run()
		{
			try
			{
				URL url = new URL( strList[1][mIndex] );
					
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.connect();
				if(conn.getResponseCode() != 200)
				{
					Toast.makeText(getBaseContext(), "Response fail!", 
							Toast.LENGTH_SHORT).show();
					throw new RuntimeException("Response fail!");
				}
				Bitmap bm = BitmapFactory.decodeStream(conn.getInputStream());
				
				Message msg = new Message();
				msg.what = 0x1;
				msg.obj = bm;
				mHandler.sendMessage(msg);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
	};

}
