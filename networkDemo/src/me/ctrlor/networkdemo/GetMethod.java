package me.ctrlor.networkdemo;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.app.Activity;

public class GetMethod extends Activity
{
	private static final String TAG = "ctrlor-networkDemo-GetMethod";
	private LinearLayout layoutJsonList;
	private LinearLayout layoutMp3Player;
	private TableLayout  layoutUploadInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_method);
		setTitle("Get Method");
	}
}
