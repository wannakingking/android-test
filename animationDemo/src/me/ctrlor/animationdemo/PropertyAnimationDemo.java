package me.ctrlor.animationdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Log;

public class PropertyAnimationDemo extends Activity {

	ImageView mImage;

    Button btnValueAnimator;
    Button btnObjectAnimator;
    Button btnAnimatorSet;
    Button btnViewProperty;
    Button btnKeyFrames;
    Button btnViewGroup;

    private final static String TAG = "Property animation demo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Property aniamtion demo");
		setContentView(R.layout.activity_property_animation_demo);
		
        mImage = (ImageView) findViewById(R.id.image);

        // 1, Value animator
        btnValueAnimator = (Button) findViewById(R.id.btn_value_animator);
        btnValueAnimator.setOnClickListener(new Button.OnClickListener()
        { 
            @Override
            public void onClick(View v)
            {
                ValueAnimator animator = ValueAnimator.ofFloat(0,1);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                    	// From transparent to opaque
                    	mImage.setAlpha(
                    			(float)animation.getAnimatedValue());
                    }
                });

                animator.setDuration(3000);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.start();
            	
            }
        });

        // 2, Object animator
        btnObjectAnimator = (Button) findViewById(R.id.btn_object_animator);
        btnObjectAnimator.setOnClickListener(new Button.OnClickListener()
        { 
            @Override
            public void onClick(View v)
            {
            	try
            	{
	            	ObjectAnimator animator = ObjectAnimator.ofFloat(
	            			mImage, "rotation", 0, 360);
	
	            	animator.addListener(new Animator.AnimatorListener() 
	            	{
						
						@Override
						public void onAnimationStart(Animator animation) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationRepeat(Animator animation) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationEnd(Animator animation) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationCancel(Animator animation) {
							// TODO Auto-generated method stub
							
						}
					});
	            	
	            	animator.setDuration(3000);
	            	animator.setRepeatCount(ValueAnimator.INFINITE);
	            	animator.setRepeatMode(ValueAnimator.REVERSE);
	            	animator.start();
            	}
            	catch(Exception e)
            	{
            		e.printStackTrace();
            	}

            }
        });

        // 3, Animator set
        btnAnimatorSet = (Button) findViewById(R.id.btn_animator_set);
        btnAnimatorSet.setOnClickListener(new Button.OnClickListener()
        { 
            @Override
            public void onClick(View v)
            {
            	try
            	{
            		// Alpha animator
	            	ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mImage, 
	            			"alpha", 0, 1);
	            	alphaAnimator.setDuration(1000);
	            	alphaAnimator.setInterpolator(new AccelerateInterpolator());
	            	alphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
	            	alphaAnimator.setRepeatMode(ValueAnimator.REVERSE);

	            	// Rotate animator
	            	ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(mImage, 
	            			"rotation", 0, 360);
	            	rotateAnimator.setDuration(1000);
	            	alphaAnimator.setInterpolator(new DecelerateInterpolator());
	            	alphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
	            	alphaAnimator.setRepeatMode(ValueAnimator.REVERSE);
	            	
	            	
	            	// Animator set 
	            	AnimatorSet animatorSet = new AnimatorSet();
	            	animatorSet.playTogether(alphaAnimator, rotateAnimator);
	            	
	            	animatorSet.start();
            	}
            	catch(Exception e)
            	{
            		e.printStackTrace();
            	}
            	
            }
        });

        // 4, View property animation
        btnViewProperty = (Button) findViewById(R.id.btn_view_property_animator);
        btnViewProperty.setOnClickListener(new Button.OnClickListener()
        { 
            @Override
            public void onClick(View v)
            {
            	try
            	{
	            	// 1, Method 1 - more objectAnimator
	            	ObjectAnimator xAnimator = ObjectAnimator.ofFloat(mImage, 
	            			"translationX", 0, -300);
	            	xAnimator.setDuration(1000);
	            	
	            	ObjectAnimator yAnimator = ObjectAnimator.ofFloat(mImage, 
	            			"translationY", 0, 300);
	            	yAnimator.setDuration(1000);
	            	
	            	AnimatorSet animatorSet1 = new AnimatorSet();
	            	animatorSet1.playTogether(xAnimator, yAnimator);
	            	animatorSet1.start();
	            	
	            	// 2, Method 2 - single objectAnimator
	            	PropertyValuesHolder pvhX = 
	            			PropertyValuesHolder.ofFloat("x", -300f);
	            	PropertyValuesHolder pvhY = 
	            			PropertyValuesHolder.ofFloat("y", 300f);
	            	ObjectAnimator.ofPropertyValuesHolder(
	            			mImage, pvhX, pvhY).start();
	            	
	            	// 3, Method 3 - view propertyAnimator
	            	mImage.animate().x(-300f).y(300f);
            	}
            	catch(Exception e)
            	{
            		e.printStackTrace();
            	}
            }
        });

        // 5, Key frames animation
        btnKeyFrames = (Button) findViewById(R.id.btn_key_frames_animation);
        btnKeyFrames.setOnClickListener(new Button.OnClickListener()
        { 
            @Override
            public void onClick(View v)
            {
            	try
            	{
	            	Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
	            	Keyframe kf1 = Keyframe.ofFloat(0.5f, 360f);
	            	Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
	            	PropertyValuesHolder pvh = PropertyValuesHolder.ofKeyframe(
	            			"rotation", kf0, kf1, kf2);
	            	ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(
	            			mImage, pvh);
	            	animator.setDuration(3000);
	            	animator.start();
            	}
            	catch(Exception e)
            	{
            		e.printStackTrace();
            	}
            }
        });

        // 6, View group animation
        btnViewGroup = (Button) findViewById(R.id.btn_view_group_animation);
        btnViewGroup.setOnClickListener(new Button.OnClickListener()
        {
        	@Override
        	public void onClick(View v)
        	{
        		
        	}
        });
	}
}
