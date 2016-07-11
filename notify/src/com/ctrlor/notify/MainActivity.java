package com.ctrlor.notify;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

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
		Button noteInfoBtn4 = (Button) findViewById(R.id.notify_info_button4);
		Button noteTypeBtn1 = (Button) findViewById(R.id.notify_type_button1);
		Button noteTypeBtn2 = (Button) findViewById(R.id.notify_type_button2);
		Button noteTypeBtn3 = (Button) findViewById(R.id.notify_type_button3);
		Button clearBtn = (Button) findViewById(R.id.clear_button);
		
		// set click listener
		noteInfoBtn1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendNotification_after_API_7();
			}
		});

		noteInfoBtn2.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendNotification_after_API_11();
			}
		});

		noteInfoBtn3.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendNotification_after_API_16();
			}
		});

		noteInfoBtn4.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				sendNotification_diy();
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
				setNotificationType(Notification.DEFAULT_ALL);
			}
		});

		clearBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				mNotificationManager.cancel(NOTIFICATIONS_ID);
			}
		});

	}
	
	
	// send notification1
	@SuppressWarnings("deprecation")
	private void sendNotification_after_API_7() {
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
		notify1.ledARGB = 0xffffffff;
		notify1.ledOnMS = 300;
		notify1.ledOffMS = 1000;
		notify1.flags |= Notification.FLAG_AUTO_CANCEL;
		
		mNotificationManager.notify(NOTIFICATIONS_ID, notify1);
			
		
	}
	
	// send notification 2
	private void sendNotification_after_API_11() {
		PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 
				0, new Intent(this, MainActivity.class), 0);
		// after api 11, create "Notification.Builder
		@SuppressWarnings("deprecation")
		Notification notify2 = new Notification.Builder(this)
			.setSmallIcon(R.drawable.ic_launcher)
			.setTicker("ticker text")
			.setContentTitle("content title")
			.setContentText("content text")
			.setContentIntent(pendingIntent2)
			.setNumber(2)
			.getNotification(); // use build() in the api 16
		notify2.flags |= Notification.FLAG_AUTO_CANCEL;
		mNotificationManager.notify(NOTIFICATIONS_ID, notify2);
		
	}

	// send notification 3
	private void sendNotification_after_API_16() {
		
		Notification.Builder mBuilder = new Notification.Builder(this)
			.setSmallIcon(R.drawable.ic_launcher)
			.setTicker("ticker text")
			.setContentTitle("content title")
			.setContentText("content text")
			//.setContentIntent(pendingIntent2)
			.setNumber(3);

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

		Notification notify = mBuilder.build();
		notify.flags |= Notification.FLAG_AUTO_CANCEL; 

		mNotificationManager.notify(NOTIFICATIONS_ID, notify);
		
	}

	// send notification used diy layout
	private void sendNotification_diy() {
		
		Notification.Builder mBuilder = new Notification.Builder(this)
			.setSmallIcon(R.drawable.ic_launcher)
			.setTicker("Diy layout notification")
			.setContentTitle("content title")
			.setContentText("content text")
			//.setContentIntent(pendingIntent2)
			.setNumber(4);

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
		mBuilder.setContent(new RemoteViews(getPackageName(),
				R.layout.my_notification));

		Notification notify = mBuilder.build();
		notify.flags |= Notification.FLAG_AUTO_CANCEL;

		mNotificationManager.notify(NOTIFICATIONS_ID, notify);
		
	}

	// set the type of notification
	private void setNotificationType(int defaults) {
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
		
		Notification notify = mBuilder.build();
		notify.flags |= Notification.FLAG_AUTO_CANCEL;
		notify.defaults |= defaults;
		mNotificationManager.notify(NOTIFICATIONS_ID, notify);

	}
}
