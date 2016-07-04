package com.ctrlor.widget1;

import com.ctrlor.widget1.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageSwitcher;

public class ImageViewActivity extends Activity {
	
	// object
	ImageSwitcher mSwitcher;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// don't display title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_image_view);
		
		mSwitcher = (ImageSwitcher)findViewById(R.id.switcher);
		
		
	}
}