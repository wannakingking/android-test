package me.ctrlor.animationdemo;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

public class FrameAnimationDemo extends Activity {

	Drawable mDrawable;
	AnimationDrawable frameAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Frame animation demo");
		
		try
		{
			frameAnimation = new AnimationDrawable();
			for(int i=1; i<3; i++)
			{
				int id = getResources().getIdentifier(
						"image_" + i, 
						"drawable", 
						getPackageName());
				mDrawable = ContextCompat.getDrawable(
						getBaseContext(), id);
				frameAnimation.addFrame(mDrawable, 1000);
			}	
			
			// Display loop back(false)
			frameAnimation.setOneShot(false);
	
			View view = new View(this);
			view.setBackground(frameAnimation);
	        setContentView(view);
	        
	        frameAnimation.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


	}
}
