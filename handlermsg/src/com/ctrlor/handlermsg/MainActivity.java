package com.ctrlor.handlermsg;

import android.app.Activity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class MainActivity extends Activity {

    // Display the picture by defined in circle
    int[] imageIds = new int[] {
        R.drawable.a,
        R.drawable.b,
        R.drawable.c,
        R.drawable.d,
        R.drawable.e,
        R.drawable.f,
        R.drawable.g,
        R.drawable.h,
        R.drawable.i,
        R.drawable.j
    };
    int currentImageId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Display picture circularly");
		setContentView(R.layout.activity_main);

        final ImageView show = (ImageView) findViewById(R.id.show);
        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // If the message was send by this program
                if(msg.what == 0x1233) {
                    
                    // Display the picture in circle
                    show.setImageResource(imageIds[currentImageId++
                            % imageIds.length]);
                }
            }
        };

        // Timer
        new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    // send empty message
                    mHandler.sendEmptyMessage(0x1233);
                }
        }, 0, 2000);
    }
                    
}
