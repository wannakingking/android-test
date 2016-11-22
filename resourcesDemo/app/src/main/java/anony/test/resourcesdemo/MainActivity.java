package anony.test.resourcesdemo;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("resources Demo");

        // Create shortcut
        Intent.ShortcutIconResource icon = Intent.ShortcutIconResource
            .fromContext(this, R.mipmap.ic_launcher);
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
		Button btn1 = (Button) findViewById(R.id.btn_1);
		btn1.setOnClickListener(this);

		Button btn2 = (Button) findViewById(R.id.btn_2);
		btn2.setOnClickListener(this);

		Button btn3 = (Button) findViewById(R.id.btn_3);
		btn3.setOnClickListener(this);

		Button btn4 = (Button) findViewById(R.id.btn_4);
		btn4.setOnClickListener(this);

		Button btn6 = (Button) findViewById(R.id.btn_6);
		btn6.setOnClickListener(this);

	}

	@Override
	public void onClick(View v)
	{
		Intent mIntent = null;
		switch(v.getId())
		{
		case R.id.btn_1:
			mIntent = new Intent(this, ValuesResourcesDemo.class);
			break;

		case R.id.btn_2:
			mIntent = new Intent(this, DrawableResourcesDemo.class);
			break;

		case R.id.btn_3:
			mIntent = new Intent(this, XmlResourcesDemo.class);
			break;

		case R.id.btn_4:
			mIntent = new Intent(this, StyleResourcesDemo.class);
			break;

		case R.id.btn_6:
			mIntent = new Intent(this, AudioResourcesDemo.class);
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
