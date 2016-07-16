package com.ctrlor.eventlistener;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnLongClickListener;
import android.content.Intent;
import android.app.PendingIntent;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class OuterClassListenerActivity extends Activity {

    EditText mAddress;
    EditText mContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_outer_listener);

        // Get the address and content of SMS from the view page
        mAddress = (EditText) findViewById(R.id.address);
        mContent = (EditText) findViewById(R.id.content);
        Button button1 = (Button) findViewById(R.id.btn_send);
        button1.setOnLongClickListener( new SendSMSListener(
                    this, mAddress, mContent));
    }
    // define the class SendSMSListener 
    public class SendSMSListener implements OnLongClickListener {
        private Activity mAct;
        private EditText mAddress;
        private EditText mContent;

        // construct function
        public SendSMSListener(Activity mAct, EditText mAddress,
                EditText mContent) {
            this.mAct = mAct;
            this.mAddress = mAddress;
            this.mContent = mContent;
        }

        @Override
        public boolean onLongClick(View v) {
            String strAddress = mAddress.getText().toString();
            String strContent = mContent.getText().toString();

            try {
            	// get SMS manager
            	SmsManager smsManager = SmsManager.getDefault();

            	// Create pendingIntent for send SMS
            	PendingIntent mPInent = PendingIntent.getBroadcast(mAct,
            			0, new Intent(), 0);

            	// send SMS
            	smsManager.sendTextMessage(strAddress, null, strContent,
	                    mPInent, null);
	
	            Toast.makeText(getApplicationContext(), "send SMS successful!", Toast.LENGTH_LONG)
	                .show();
            } catch (Exception e) {
            	Toast.makeText(getApplicationContext(), "SMS send fail!", Toast.LENGTH_LONG)
            		.show();
            	e.printStackTrace();
            }
	            
            return false;
        }
        
    }
}
