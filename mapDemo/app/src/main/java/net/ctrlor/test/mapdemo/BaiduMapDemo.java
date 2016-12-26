package net.ctrlor.test.mapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class BaiduMapDemo extends AppCompatActivity {

    CustomMapData mMapData = null;

    // Map class
    MapView mMapView;
    BaiduMap mBaiduMap;
    LocationClient mLocClient;
    public MyLocationListener myListener = new MyLocationListener();

    // UI
    boolean isFirstLoc = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initializing
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map_demo);

        Button btnOk = (Button) findViewById(R.id.btn_ok_baidu_map_demo);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resultCode = 1;
                Intent intent = new Intent();
                intent.putExtra("data", mMapData);
                setResult(resultCode, intent);
                finish();

            }
        });

        Button btnCancel = (Button) findViewById(R.id.btn_cancel_baidu_map_demo);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resultCode = 0;
                setResult(resultCode, null);
                finish();
            }
        });

        // Load map
        mMapView = (MapView) findViewById(R.id.baidu_map_view_demo);
        mBaiduMap = mMapView.getMap();

        // Begin to locating
        mBaiduMap.setMyLocationEnabled(true);

        // Initializing location
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd0911");
        option.setScanSpan(1000);

        mLocClient.setLocOption(option);
        mLocClient.start();

    }

    // Listener class
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // Return null if destroyed.
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(100)
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder()
                        .target(ll)
                        .zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory
                    .newMapStatus(builder.build()));

            }
        }

        public void onReceivePoi(BDLocation poiLocation) {

        }
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }
    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // Stop locating
        mLocClient.stop();

        // Close map
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

}