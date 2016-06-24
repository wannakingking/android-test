package ctrlor.widget;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;

public class MainActivity extends Activity {
	// define four button
	View.OnClickListener listener0 = null;
	View.OnClickListener listener1 = null;
	View.OnClickListener listener2 = null;
	View.OnClickListener listener3 = null;

	Button button0;
	Button button1;
	Button button2;
	Button button3;
	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	private GoogleApiClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		//define the Button function
		listener0 = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent0 = new Intent(ActivityMain.this, ActivityFrameLayout.class);
				setTitle("FrameLayout");
				startActivity(intent0);
			}
		};

		listener1 = new View.OnClickListener(){
			public void onClick(View v) {
				Intent intent1 = new Intent(ActivityMain.this, ActivityRelativeLayout.class);
				setTitle("RelativelLayout");
				startActivity(intent1);
			}
		};

		listener2 = new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent2 = new Intent(ActivityMain.this, ActivityLinearLayout.class);
				setTitle("LinearLayout");
				startActivity(intent2);
			}
		};

		listener3 = new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent3 = new Intent(ActivityMain.this, ActivityTableLayout.class);
				setTitle("TableLayout");
				startActivity(intent3);
			}
		};

		setContentView(R.layout.activity_main);
		button0 = (Button)findViewById(R.id.button0);
		button0.setOnClickListener(listener0);
		button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(listener1);
		button2 = (Button)findViewById(R.id.button2);
		button2.setOnClickListener(listener2);
		button3 = (Button)findViewById(R.id.button3);
		button3.setOnClickListener(listener3);
		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client = new Builder(this).addApi(AppIndex.API).build();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onStart() {
		super.onStart();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client.connect();
		Action viewAction = Action.newAction(
				Action.TYPE_VIEW, // TODO: choose an action type.
				"Main Page", // TODO: Define a title for the content shown.
				// TODO: If you have web page content that matches this app activity's content,
				// make sure this auto-generated web page URL is correct.
				// Otherwise, set the URL to null.
				Uri.parse("http://host/path"),
				// TODO: Make sure this auto-generated app URL is correct.
				Uri.parse("android-app://ctrlor.widget/http/host/path")
		);
		AppIndex.AppIndexApi.start(client, viewAction);
	}

	@Override
	public void onStop() {
		super.onStop();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		Action viewAction = Action.newAction(
				Action.TYPE_VIEW, // TODO: choose an action type.
				"Main Page", // TODO: Define a title for the content shown.
				// TODO: If you have web page content that matches this app activity's content,
				// make sure this auto-generated web page URL is correct.
				// Otherwise, set the URL to null.
				Uri.parse("http://host/path"),
				// TODO: Make sure this auto-generated app URL is correct.
				Uri.parse("android-app://ctrlor.widget/http/host/path")
		);
		AppIndex.AppIndexApi.end(client, viewAction);
		client.disconnect();
	}
}
