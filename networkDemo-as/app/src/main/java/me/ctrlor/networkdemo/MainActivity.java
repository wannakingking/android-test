package me.ctrlor.networkdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener
{

	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	Button btn6;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Network Demo");

        // Create shortcut
        Intent.ShortcutIconResource icon = Intent.ShortcutIconResource
            .fromContext(this, R.drawable.ic_launcher);
        Intent launchIntent = new Intent(this, MainActivity.class);
        //launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launchIntent);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "networkDemo");
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra("duplicate", false);
        
        sendBroadcast(intent);

        // Buttons
		btn1 = (Button) findViewById(R.id.btn_1);
		btn1.setOnClickListener(this);

		btn2 = (Button) findViewById(R.id.btn_2);
		btn2.setOnClickListener(this);

		btn3 = (Button) findViewById(R.id.btn_3);
		btn3.setOnClickListener(this);

		btn4 = (Button) findViewById(R.id.btn_4);
		btn4.setOnClickListener(this);

		btn5 = (Button) findViewById(R.id.btn_5);
		btn5.setOnClickListener(this);

		btn6 = (Button) findViewById(R.id.btn_6);
		btn6.setOnClickListener(this);

		btn4.callOnClick();

	}

	@Override
	public void onClick(View v)
	{
		Intent mIntent = null;
		switch(v.getId())
		{
		case R.id.btn_1:
			mIntent = new Intent(this, UrlConnectionMethod.class);
			break;

		case R.id.btn_2:
			mIntent = new Intent(this, GetMethod.class);
			break;

		case R.id.btn_3:
			mIntent = new Intent(this, PostMethod.class);
			break;

		case R.id.btn_4:
			mIntent = new Intent(this, SocketMethod.class);
			break;

		case R.id.btn_5:
			mIntent = new Intent(this, HttpMethod.class);
			break;

		case R.id.btn_6:
			mIntent = new Intent(this, UdpMethod.class);
			break;

		default:
			break;
		}

		if(mIntent != null)
		{
			startActivity(mIntent);
		}
	}
}
