package com.ctrlor.eventcallback;

import android.content.Context;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

public class MyButton extends Button implements OnLongClickListener{
    public MyButton(Context context, AttributeSet set) {
        super(context, set);
    }

    @Override
	public boolean onLongClick(View v) {
    	Log.v("-ctrlor.net-", "one keydown event in MyButton");
    	return true;
	}
}
