package me.ctrlor.animationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TextViewAnimationDemo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_textview_animation_demo);
		
		Button btnDo = (Button) findViewById(R.id.btn_do);
        btnDo.setOnClickListener(new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        EditText et = (EditText) findViewById(R.id.et_input);
                        TextView tv = (TextView) findViewById(R.id.tv_show);

                        String string  = et.getText().toString();
                        if(string != "")
                        {
                            Animation mAnimation = AnimationUtils.loadAnimation(
                                getBaseContext(), R.anim.textview_shake);
                            tv.setText(string);
                            tv.startAnimation(mAnimation);
                        }
                    }
                });
		
	}
}
