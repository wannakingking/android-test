package anony.ctrlor.datastorage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class NetworkStorageDemo extends Activity implements Runnable{

	private final static String TAG = "NetworkStorageDemo";
	
	private static String urlString = "http://220.181.57.217";
	private String dataString = "";
	
	Button btnGo;
	EditText et;
	TextView tv;
	// handle the message from thread
	final Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if(msg.what == 0x01)
			{
				( (TextView)findViewById(R.id.tv_html) ).setText(dataString);
				//tv.setText(dataString);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_network_storage_demo );
		
		btnGo 	= (Button) findViewById(R.id.btn_go);
		et		= (EditText) findViewById(R.id.et_address);
		et.setText(urlString);

		btnGo.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				urlString = et.getText().toString();
				if(urlString != "")
				{
					Thread thread = new Thread(NetworkStorageDemo.this);
					thread.start();
				}
				else
				{
					tv.setText("");
				}
				
			}
		});
	}
	
	@Override
	public void run()
	{
		try
		{
			HttpURLConnection conn = (HttpURLConnection)new URL(urlString)
					.openConnection();
			conn.setConnectTimeout(15000);
            conn.setReadTimeout(10000);
			conn.setRequestMethod( "GET" );
			conn.setDoInput( true );

            // Start the query
            conn.connect();
			BufferedReader rd = new BufferedReader( 
					new InputStreamReader( conn.getInputStream() ));

			String line = "";
			StringBuffer result = new StringBuffer();
			while(( line = rd.readLine()) != null )
			{
				result.append( line );
				result.append("\n");
				Log.d( TAG, "line:" + line );
			}
			rd.close();

			dataString = result.toString();
			
			Message msg = new Message();
			msg.what = 0x01;
			handler.sendMessage(msg);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
}
