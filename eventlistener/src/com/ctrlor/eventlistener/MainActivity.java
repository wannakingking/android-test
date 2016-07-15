package com.ctrlor.eventlistener;

import com.ctrlor.eventlistener.R;
import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        /*// inner class listener
        Button button1 = (Button) findViewById(R.id.btn_inner_class_listener);
        button1.setOnClickListener( new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(this, 
                        InnerClassListenerActivity.class);
                    startActivity(mIntent);
                }
	    });
*/
        // outer class listener
        Button button2 = (Button) findViewById(R.id.btn_outer_class_listener);
        button2.setOnClickListener( new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(MainActivity.this, OuterClassListenerActivity.class);
                    startActivity(mIntent);
                }
	    });

        // activity itself listener
        Button button3 = (Button) findViewById(R.id.btn_activity_listener);
        button3.setOnClickListener( new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(MainActivity.this, 
                        ItselfListenerActivity.class);
                    startActivity(mIntent);
                }
		});

        // anonymous class listener
        Button button4 = (Button) findViewById(R.id.btn_anonymous_listener);
        button4.setOnClickListener( new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(MainActivity.this, 
                        AnonymousListenerActivity.class);
                    startActivity(mIntent);
                }
		});

        // blinding label listener
        Button button5 = (Button) findViewById(R.id.btn_blind_label_listener);
        button5.setOnClickListener( new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent(MainActivity.this, 
                        BlindLabelListenerActivity.class);
                    startActivity(mIntent);
                }
		});
    }

}
