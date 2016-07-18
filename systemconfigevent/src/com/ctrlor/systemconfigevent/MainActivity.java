package com.ctrlor.systemconfigevent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.content.res.Configuration;
import android.content.pm.ActivityInfo;

public class MainActivity extends Activity {

    EditText mOri;
    EditText mNavigation;
    EditText mTouch;
    EditText mMnc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        mOri        = (EditText) findViewById(R.id.ori);
        mNavigation = (EditText) findViewById(R.id.navigation);
        mTouch      = (EditText) findViewById(R.id.touch);
        mMnc        = (EditText) findViewById(R.id.mnc);

        Button button1 = (Button) findViewById(R.id.btn_get_info);
        button1.setOnClickListener( new Button.OnClickListener() {
                @Override
                public void onClick(View v) {

                	Log.v("com.ctrlor.systemconfigevent", "the button1 is pressed");
	                // Get Configuration object from system
	                Configuration cfg = getResources().getConfiguration();
	                String screen = cfg.orientation ==
	                    Configuration.ORIENTATION_LANDSCAPE
	                    ? "landscape" : "portrait";
	                String mncCode = cfg.mnc + "";
	                String navName = cfg.navigation ==
	                    Configuration.NAVIGATION_NONAV
	                    ? "no navigation controllor" :
	                    cfg.navigation == Configuration.NAVIGATION_WHEEL
	                    ? "wheel navigation controllor" :
	                    cfg.navigation == Configuration.NAVIGATION_DPAD
	                    ? "dpad navigation controllor" : "trackball navigation controllor";
	                String touchName = cfg.touchscreen ==
	                    Configuration.TOUCHSCREEN_NOTOUCH
	                    ? "no touch screen" : "support touch screen";
	
	                mOri.setText(screen);
	                mNavigation.setText(navName);
	                mTouch.setText(touchName);
	                mMnc.setText(mncCode);
                }
        });

        // The button to change the orientation of the screen
        Button button2 = (Button) findViewById(R.id.btn_set_orientation);
        button2.setOnClickListener( new Button.OnClickListener() {
                @Override
                public void onClick(View v) {

                	Log.v("com.ctrlor.systemconfigevent", "the button2 is pressed");
	                // Get configuration from system
	                Configuration cfg = getResources().getConfiguration();
	                if(cfg.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	                    // Set screen to portrait
	                    MainActivity.this.setRequestedOrientation(
	                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	                }
	                else if (cfg.orientation == Configuration.ORIENTATION_PORTRAIT) {
	                    // Set screen to landscape
	                    MainActivity.this.setRequestedOrientation(
	                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	                }
                }
        });
	}

    // Listener the change of screen orientation
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        String screen = newConfig.orientation ==
            Configuration.ORIENTATION_LANDSCAPE
            ? "landscape screen" : "portrait screen";
        Toast.makeText(this, "The orientation of the screen is changed" +
                "\n and now is: " + screen, Toast.LENGTH_LONG).show();
        Log.v("com.ctrlor.systemconfigevent", "the screen orientation is changed");
    }
}
