package com.ctrlor.menu;

import com.ctrlor.menu.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Button;
import android.view.View;

public class MainActivity extends Activity {

    // define object
    private static final int ITEM0 = Menu.FIRST;
    private static final int ITEM1 = Menu.FIRST + 1;
    Button button1;
    Button button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        // set two buttons as invisible
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, ITEM0, 0, "Button1");
        menu.add(0, ITEM1, 0, "Button2");
        //menu.findItem(ITEM1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case ITEM0:
                actionClickMenuItem1();
                break;
            case ITEM1:
                actionClickMenuItem2();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // first button action 
    private void actionClickMenuItem1() {
        setTitle("button1 visible");
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.INVISIBLE);
    }

    // second button action
    private void actionClickMenuItem2() {
        setTitle("button2 visible");
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.VISIBLE);
    }
}
