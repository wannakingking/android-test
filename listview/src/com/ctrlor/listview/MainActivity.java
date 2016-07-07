package com.ctrlor.listview;

import com.ctrlor.listview.R;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;

import android.widget.SimpleAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        ArrayList<HashMap<String, Object>> users = new 
            ArrayList<HashMap<String, Object>>();
        for(int i=0; i<10; i++) {
            HashMap<String, Object> user = new HashMap<String, Object>();
            user.put("imag", R.drawable.user);
            user.put("username", "name(" + i + ")");
            user.put("age", (11 + i) + "");
            users.add(user);
        }
        SimpleAdapter saImageItems = new SimpleAdapter(this, 
                users,
                R.layout.user,
                new String[] {"imag", "username", "age"},
                new int[] {R.id.img, R.id.name, R.id.age});

       ((ListView) findViewById(R.id.users)).setAdapter(saImageItems);
    }

}
