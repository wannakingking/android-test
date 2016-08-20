package me.ctrlor.animationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.animation.RotateAnimation;

public class TweenAnimationDemo extends Activity 
	implements OnClickListener
	{

	Button btnPlay;
	
	Button btnAlpha;
	Button btnScale;
	Button btnTranslate;
	Button btnRotate;
	
	Button btnAlpha2;
	Button btnScale2;
	Button btnTranslate2;
	Button btnRotate2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation_demo);
        setTitle("Tween animation demo");

        // Play
        btnPlay = (Button) findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(this);

        // Left part, animation defined by 'anim' <set>
        btnAlpha = (Button) findViewById(R.id.btn_alpha);
        btnAlpha.setOnClickListener(this);

        btnScale = (Button) findViewById(R.id.btn_scale);
        btnScale.setOnClickListener(this);

        btnTranslate = (Button) findViewById(R.id.btn_translate);
        btnTranslate.setOnClickListener(this);

        btnRotate = (Button) findViewById(R.id.btn_rotate);
        btnRotate.setOnClickListener(this);

        // Right part, animation defined by java
        btnAlpha2 = (Button) findViewById(R.id.btn_alpha_2);
        btnAlpha2.setOnClickListener(this);

        btnScale2 = (Button) findViewById(R.id.btn_scale_2);
        btnScale2.setOnClickListener(this);

        btnTranslate2 = (Button) findViewById(R.id.btn_translate_2);
        btnTranslate2.setOnClickListener(this);

        btnRotate2 = (Button) findViewById(R.id.btn_rotate_2);
        btnRotate2.setOnClickListener(this);

	}

    public void onClick(View v)
    {
    	Animation mAnimation;
        switch(v.getId())
        {
            case R.id.btn_alpha:
                mAnimation = AnimationUtils.loadAnimation(
                        this, R.anim.tween_action_alpha);
                btnAlpha.startAnimation(mAnimation);
                break;

            case R.id.btn_scale:
                mAnimation = AnimationUtils.loadAnimation(
                        this, R.anim.tween_action_scale);
                btnScale.startAnimation(mAnimation);
                break;

            case R.id.btn_translate:
                mAnimation = AnimationUtils.loadAnimation(
                        this, R.anim.tween_action_translate);
                btnTranslate.startAnimation(mAnimation);
                break;

            case R.id.btn_rotate:
                mAnimation = AnimationUtils.loadAnimation(
                        this, R.anim.tween_action_rotate);
                btnRotate.startAnimation(mAnimation);
                break;

            case R.id.btn_alpha_2:
            	mAnimation = new AlphaAnimation(0.0f, 1.0f);
                mAnimation.setDuration(10000);
                btnAlpha2.startAnimation(mAnimation);
            	break;

            case R.id.btn_scale_2:
                mAnimation = new ScaleAnimation(0.0f, 10.0f, 0.0f, 10.0f, 
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                mAnimation.setDuration(10000);
                btnScale2.startAnimation(mAnimation);
            	break;

            case R.id.btn_translate_2:
                mAnimation = new TranslateAnimation(0, -300, 0, 300);
                mAnimation.setDuration(10000);
                btnTranslate2.startAnimation(mAnimation);
            	break;

            case R.id.btn_rotate_2:
                mAnimation = new RotateAnimation(0.0f, +3600.0f, 
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                mAnimation.setDuration(30000);
                btnRotate2.startAnimation(mAnimation);
            	break;

            case R.id.btn_play:
            	playAll();
            	break;

            default:
            	playAll();
                break;
        }
    }
    
    // Play all animation
    public void playAll()
    {
		btnAlpha.callOnClick();
		btnAlpha2.callOnClick();
	
		btnScale.callOnClick();
		btnScale2.callOnClick();
	
		btnTranslate.callOnClick();
		btnTranslate2.callOnClick();
	
		btnRotate.callOnClick();
		btnRotate2.callOnClick();
    }

}
