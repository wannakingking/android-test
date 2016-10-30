package anony.test.webviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener
{

	Button btn1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("WebView Demo");

        // Create shortcut
        Intent.ShortcutIconResource icon = Intent.ShortcutIconResource
            .fromContext(this, R.mipmap.ic_launcher);
        Intent launchIntent = new Intent(this, MainActivity.class);
        //launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launchIntent);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "WebView Demo");
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra("duplicate", false);
        
        sendBroadcast(intent);

        // Buttons
		btn1 = (Button) findViewById(R.id.btn_1);
		btn1.setOnClickListener(this);

		btn1.callOnClick();
	}

	@Override
	public void onClick(View v)
	{
		Intent mIntent = null;
		switch(v.getId())
		{
			case R.id.btn_1:
				mIntent = new Intent(this, BrowserDemo.class);
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
