package com.ctrlor.fragmentdemo;

import com.ctrlor.fragmentdemo.LeftFragment.MyListener;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;

public class MainActivity extends Activity implements MyListener
{
	public void showMessage(int index)   
    {   
        if (1 == index)   
            tvShow.setText(R.string.first_page);   
        if (2 == index)   
            tvShow.setText(R.string.second_page);   
        if (3 == index)   
            tvShow.setText(R.string.third_page);   
    }   
	

    final static String TAG = "ctrlor";
    private TextView tvShow;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        Log.v(TAG, "MainActivity---->onCreate");

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        // To add fragment
        LeftFragment leftFragment = new LeftFragment();
        RightFragment rightFragment = new RightFragment();
        transaction.add( R.id.left_fragment, leftFragment, "leftFragment" );
        transaction.add( R.id.right_fragment, rightFragment, "rightFragment" );
        transaction.commit();
	}

    @Override
    protected void onResume()
    {
        super.onResume();
        
        Log.v(TAG, "MainActivity----->onResume");
        tvShow = (TextView) findViewById( R.id.tv_show);
    }
}
