package me.ctrlor.animationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{

	Button btnTrans;
	Button btnTween;
	Button btnFrame;
	Button btnProperty;
	Button btnView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Animation Demo");

        // Start transition drawable animation demo
        btnTrans 	= (Button) findViewById(R.id.btn_transition_drawable_demo);
        btnTrans.setOnClickListener(this);

        btnTween 	= (Button) findViewById(R.id.btn_tween_animation_demo);
        btnTween.setOnClickListener(this);

        btnFrame 	= (Button) findViewById(R.id.btn_frame_animation_demo);
        btnFrame.setOnClickListener(this);

        btnProperty = (Button) findViewById(R.id.btn_property_animation_demo);
        btnProperty.setOnClickListener(this);

        btnView		= (Button) findViewById(R.id.btn_textview_animation_demo);
        btnView.setOnClickListener(this);
        

	}
	
	public void onClick(View v)
	{
		Intent intent = null;
		switch(v.getId())
		{
			case R.id.btn_transition_drawable_demo:
				intent = new Intent(this,
						TransitionDrawableDemo.class);
				break;
				
			case R.id.btn_tween_animation_demo:
				intent = new Intent(this,
						TweenAnimationDemo.class);
				break;
				
			case R.id.btn_frame_animation_demo:
				intent = new Intent(this,
						FrameAnimationDemo.class);
				break;
				
			case R.id.btn_property_animation_demo:
				intent = new Intent(this,
						PropertyAnimationDemo.class);
				break;
				
			case R.id.btn_textview_animation_demo:
				intent = new Intent(this,
						ViewAnimationDemo.class);
				break;
				
			default:
				break;
		}
		
		if(intent != null)
		{
			startActivity(intent);
		}
	}
}
