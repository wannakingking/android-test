package com.ctrlor.testlauncher;


import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends LauncherActivity {

	// Set the name of two activity. 
	String[] straName = {"set program perference", "watch the list"};
	
	// Implements of the activities.
	Class<?>[] mClass = {TestPreferenceActivity.class,
			TestExpandableListActivity.class};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, straName);

        // Set the adapter needed by the list displayed in the window
        setListAdapter(adapter);
	}

    // Return relative intent depend on the list item.
    @Override
    public Intent intentForPosition(int pos) {
        return new Intent(MainActivity.this, mClass[pos]);
    }
}
