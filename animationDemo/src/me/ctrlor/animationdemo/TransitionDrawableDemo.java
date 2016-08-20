package me.ctrlor.animationdemo;

import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TransitionDrawableDemo extends Activity {

	TransitionDrawable trans;
	int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setTitle("Transition Drawable Demo");

        LinearLayout layout;

        try
        {
	        layout = new LinearLayout(this);
	        ImageView imgView = new ImageView(this);
	        imgView.setAdjustViewBounds(true);
	        imgView.setLayoutParams(new LayoutParams(
	                    LayoutParams.MATCH_PARENT,
	                    LayoutParams.MATCH_PARENT));
	
	        layout.addView(imgView);
	        setContentView(layout);
	        
	        trans = (TransitionDrawable)
	            ContextCompat.getDrawable(getBaseContext(), R.anim.transition_drawable);
	        imgView.setImageDrawable(trans);
	        
	        mHandler.post(thread);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
	
	Handler mHandler = new Handler(new Handler.Callback() 
	{
		
		@Override
		public boolean handleMessage(Message msg) 
		{
			if(msg.arg1 % 2 == 0)
			{
				trans.startTransition(msg.arg2);
			}
			else
			{
				trans.reverseTransition(msg.arg2);
			}
			mHandler.postDelayed(thread, msg.arg2);
			// TODO Auto-generated method stub
			return false;
		}
	});
	
	Thread thread = new Thread(new Runnable()
	{
		@Override
		public void run()
		{
			Message msg = mHandler.obtainMessage();
			msg.arg1 = count++;
			msg.arg2 = 5000;
			mHandler.sendMessage(msg);
		}
	});
}
