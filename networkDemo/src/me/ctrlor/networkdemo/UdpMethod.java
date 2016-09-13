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


public class UdpMethod extends Activity 
{
	
	private static final String TAG = "ctrlor-networkDemo-UdpMethod";


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_method);
		setTitle("Udp method");

	}
}
