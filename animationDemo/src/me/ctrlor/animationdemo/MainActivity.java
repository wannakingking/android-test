package me.ctrlor.animationdemo;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("Animation Demo");

        // Start transition drawable animation demo
        Button btnTrans = (Button) findViewById(R.id.btn_transition_drawable_demo);
        btnTrans.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent  = new Intent(MainActivity.this,
                    TransitionDrawableDemo.class);
                startActivity(intent);
            }
        });

            
        // Start tween animation demo
        Button btnTween = (Button) findViewById(R.id.btn_tween_animation_demo);
        btnTween.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent  = new Intent(MainActivity.this,
                    TweenAnimationDemo.class);
                startActivity(intent);
            }
        });

        // Start frame animation demo
        Button btnFrame = (Button) findViewById(R.id.btn_frame_animation_demo);
        btnFrame.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent  = new Intent(MainActivity.this,
                    FrameAnimationDemo.class);
                startActivity(intent);
            }
        });

        // Start property animation demo
        Button btnProperty = (Button) findViewById(R.id.btn_property_animation_demo);
        btnProperty.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent  = new Intent(MainActivity.this,
                    PropertyAnimationDemo.class);
                startActivity(intent);
            }
        });

        // Start gif animation demo
        Button btnGif = (Button) findViewById(R.id.btn_gif_animation_demo);
        btnGif.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent  = new Intent(MainActivity.this,
                    GifAnimationDemo.class);
                startActivity(intent);
            }
        });

        // Start TextView animation demo
        Button btnTv= (Button) findViewById(R.id.btn_textview_animation_demo);
        btnTv.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent  = new Intent(MainActivity.this,
                    TextViewAnimationDemo.class);
                startActivity(intent);
            }
        });

                Intent intent  = new Intent(MainActivity.this,
                    FrameAnimationDemo.class);
                startActivity(intent);
	}
}
