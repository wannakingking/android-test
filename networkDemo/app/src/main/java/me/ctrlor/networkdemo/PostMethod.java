package me.ctrlor.networkdemo;

import me.ctrlor.common.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

public class PostMethod extends Activity
{

	private static final String TAG = "ctrlor-PostMethod";
	private String addressUploadInfo= "http://192.168.0.11/test/postRequest.php";
	private String strPost;
	private String strResult;



	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_method);
		setTitle("Post method");

		Button btnSubmit = (Button) findViewById(R.id.btn_post_submit);
		btnSubmit.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String strUsername = ((EditText)findViewById(R.id.et_post_username))
						.getText().toString();
				String strPassword = ((EditText)findViewById(R.id.et_post_password))
						.getText().toString();


				uploadInfo( strUsername, strPassword );
			}
		});
	}
	private void uploadInfo(final String strUsername, final String strPassword)
	{
		strResult = "null";
		strPost = "username=" + strUsername + "&password=" + strPassword;

		Runnable r = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					HttpURLConnection conn = (HttpURLConnection)
							(new URL(addressUploadInfo)).openConnection();
					conn.setRequestMethod("POST");
					conn.setConnectTimeout(3000);
					conn.setDoOutput(true);
					conn.setDoInput(true);

					OutputStream os = conn.getOutputStream();
					os.write(strPost.getBytes());
					os.flush();
					if(conn.getResponseCode() == 200)
					{
						byte[] data = StreamTool.read(conn.getInputStream());
						strResult = new String(data);
						Log.d(TAG, "strResult: " + strResult);

						Message msg = new Message();
						msg.arg1 = 0x1;
						msg.obj = strResult;
						mHandler.sendMessage(msg);
					}
					else
					{
						Log.d(TAG, "Error in post info page");
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		};

		new Thread(r).start();
	}

	final Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			if(msg.arg1 == 0x1)
			{
				((TextView)findViewById(R.id.tv_post_return_content))
					.setText((String)msg.obj);
			}
		}
	};
}
