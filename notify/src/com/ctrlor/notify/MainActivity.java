package com.ctrlor.notify;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	// define object
	private static int NOTIFICATIONS_ID = R.layout.activity_main;
	private NotificationManager mNotificationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// init
		mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Button noteInfoBtn1 = (Button) findViewById(R.id.notify_info_button1);
		Button noteInfoBtn2 = (Button) findViewById(R.id.notify_info_button2);
		Button noteInfoBtn3 = (Button) findViewById(R.id.notify_info_button3);
		Button noteTypeBtn1 = (Button) findViewById(R.id.notify_type_button1);
		Button noteTypeBtn2 = (Button) findViewById(R.id.notify_type_button2);
		Button noteTypeBtn3 = (Button) findViewById(R.id.notify_type_button3);
		Button clearBtn = (Button) findViewById(R.id.clear_button);
		
		// set click listener
		noteInfoBtn1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendNotification1();
			}
		});

		noteInfoBtn2.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendNotification2();
			}
		});

		noteInfoBtn3.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendNotification3();
			}
		});

		noteTypeBtn1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				setNotificationType(Notification.DEFAULT_SOUND);
			}
		});

		noteTypeBtn2.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				setNotificationType(Notification.DEFAULT_VIBRATE);
			}
		});

		noteTypeBtn3.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				setNotificationType(Notification.DEFAULT_LIGHTS);
			}
		});

		clearBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				mNotificationManager.cancel(NOTIFICATIONS_ID);
			}
		});

	}
	
	// send notification
	private void  sendNotification(String tickerText, String title, String content,
			int drawable) 
	{
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
			.setSmallIcon(drawable)
			.setContentTitle(title)
			.setContentText(content);
		Intent resultIntent = new Intent(this, MainActivity.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(MainActivity.class);
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = 
				stackBuilder.getPendingIntent(
						0,
						PendingIntent.FLAG_UPDATE_CURRENT
						);
		mBuilder.setContentIntent(resultPendingIntent);
		mNotificationManager.notify(NOTIFICATIONS_ID, mBuilder.build());
	}
	
	// send notification1
	@SuppressWarnings("deprecation")
	private void sendNotification1() {
		// PendingIntent 不是马上调用，需要在下拉状态条发出的activity
		PendingIntent pendingIntent = PendingIntent.getActivity(
				this, 0, new Intent(this, MainActivity.class), 0);
		// used in the android 2.x
		Notification notify1 = new Notification();
		notify1.icon = R.drawable.ic_launcher;
		notify1.tickerText = "android 2.x ticktext";
		notify1.when = System.currentTimeMillis();
		notify1.setLatestEventInfo(this, "notify",
				"notify context", pendingIntent);
		notify1.number = 1;
		notify1.ledARGB = 0xff00ff00;
		notify1.ledOnMS = 300;
		notify1.ledOffMS = 1000;
		notify1.flags |= Notification.FLAG_AUTO_CANCEL;
		
		mNotificationManager.notify(NOTIFICATIONS_ID, notify1);
			
		
	}
	
	// send notification 2
	private void sendNotification2() {
		
	}

	// send notification 3
	private void sendNotification3() {
		
	}

	// set the type of notification
	private void setNotificationType(int type) {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle("title")
			.setContentText("content");
		Intent resultIntent = new Intent(this, MainActivity.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(MainActivity.class);
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = 
				stackBuilder.getPendingIntent(
						0,
						PendingIntent.FLAG_UPDATE_CURRENT
						);
		mBuilder.setContentIntent(resultPendingIntent);
	/*	
		switch(type) {
			case DEFAULT_SOUND:
				break;
			
			case DEFAULT_VIBRATE:
				long[] vibrate = {0, 100, 200, 300};
				mBuilder.setVibrate(vibrate);
				break;
			case DEFAULT_LIGHTS:
				mBuilder.setLights(0xff00ff00, 300, 1000);
		}
		*/
		mNotificationManager.notify(NOTIFICATIONS_ID, mBuilder.build());

	}
}
