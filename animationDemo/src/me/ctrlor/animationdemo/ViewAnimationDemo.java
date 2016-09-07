package me.ctrlor.animationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.util.Log;

public class ViewAnimationDemo extends Activity {

	private final static String TAG = "View Animation demo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_animation_demo);
		setTitle("View animation demo");
		
        // Animation by anim xml
		Button btnDo1 = (Button) findViewById(R.id.btn_do_by_anim);
        btnDo1.setOnClickListener(new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        EditText et = (EditText) findViewById(R.id.et_input);
                        TextView tv = (TextView) findViewById(R.id.tv_show);

                        String string  = et.getText().toString();
                        if(string != "")
                        {
                        	// one second ? cycles
                            Animation mAnimation = AnimationUtils.loadAnimation(
                                getBaseContext(), R.anim.textview_shake);
                            tv.setText(string);
                            tv.startAnimation(mAnimation);
                        }
                        Log.d( TAG, "string:" + string);
                    }
                });
		
        // Animation by java
		Button btnDo2 = (Button) findViewById(R.id.btn_do_by_java);
        btnDo2.setOnClickListener(new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        EditText et = (EditText) findViewById(R.id.et_input);
                        TextView tv = (TextView) findViewById(R.id.tv_show);

                        String string  = et.getText().toString();
                        if(string != "")
                        {
                            Animation mAnimation = new TranslateAnimation(
                                0.0f, 100.0f, 0.0f, 0.0f);

                            // one second one repeat
                            mAnimation.setDuration(1000);
                            mAnimation.setRepeatMode(Animation.REVERSE);
                            mAnimation.setRepeatCount(1);

                            tv.setText(string);
                            tv.startAnimation(mAnimation);
                        }
                    }
                });
		
	}
}
