package com.ctrlor.widget1;

import com.ctrlor.widget1.R;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class TabViewActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("TabView");
        //setContentView(R.layout.activity_tab_view);
        TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.activity_tab_view,
                tabHost.getTabContentView(), true);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("tab1")
                .setContent(R.id.view1));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("tab2")
                .setContent(R.id.view2));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("tab3")
                .setContent(R.id.view3));
    }
}
