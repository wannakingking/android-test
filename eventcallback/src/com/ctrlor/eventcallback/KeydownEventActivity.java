package com.ctrlor.eventcallback;

import android.app.Activity;
import android.os.Bundle;

/*
import android.widget.Button;
import android.content.Context;
import android.view.KeyEvent;
import android.util.AttributeSet;
import android.util.Log;
*/

public class KeydownEventActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keydown_event);
        setTitle("key down event callback");

        //MyButton button = (MyButton) findViewById(R.id.btn_keydown_event);
    }
/*
    private class MyButton extends Button {
        public MyButton(Context context, AttributeSet set) {
            super(context, set);
        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            super.onKeyDown(keyCode, event);
            Log.v("-ctrlor.net-", "one keydown event in MyButton");
            return true;
        }
    }
    */
}
