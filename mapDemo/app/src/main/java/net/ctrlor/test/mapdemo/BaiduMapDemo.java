package net.ctrlor.test.mapdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.Poi;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

public class BaiduMapDemo extends AppCompatActivity implements
        OnGetGeoCoderResultListener {

    private static final String TAG = "Baidu Map Demo  Tag";

    int resultCode;

    // Map class
    MapView mMapView;
    BaiduMap mBaiduMap;
    LocationClient mLocClient;
    MarkerOptions mMarker = new MarkerOptions();
    GeoCoder mSearch = null;
    private MyLocationListener myListener = new MyLocationListener();
    private CustomMapData mCustomMapData = new CustomMapData();

    // UI
    boolean isFirstLoc = true;
    boolean isLocationSuccessful= false;

    // Marker icon
    BitmapDescriptor mBitmapDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	
	    // Initialzing baidu map
	    SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_baidu_map_demo);
        setTitle("Baidu map demo");

        Button btnOk = (Button) findViewById(R.id.btn_ok_baidu_map_demo);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultCode = 1;
                mCustomMapData.setMapVendor("baidu");
                onBackPressed();

            }
        });

        Button btnCancel = (Button) findViewById(R.id.btn_cancel_baidu_map_demo);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultCode = 0;
                mCustomMapData.setMapVendor("baidu");
                onBackPressed();
            }
        });
        // Load map
        mMapView = (MapView) findViewById(R.id.baidu_map_view_demo);
        mBaiduMap = mMapView.getMap();

        // Begin to locating
        mBaiduMap.setMyLocationEnabled(true);

        // Initializing location
        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        //option.setIgnoreKillProcess(false);

        mLocClient.setLocOption(option);
        mLocClient.start();


        // Set mark
        initOverlay();

    }

    // Listener class
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // Return null if destroyed.
            if (location == null
                    || mMapView == null
                    || (location.getLocType() != 61 && location.getLocType() != 161)) {
                isLocationSuccessful = false;
                Log.d(TAG, "getLocType():" + location.getLocType());
                return;
            }

            isLocationSuccessful = true;

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll);
                builder.zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory
                        .newMapStatus(builder.build()));

                // Set the marker
                mBitmapDesc = BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_mark);
                mMarker.position(new LatLng(location.getLatitude(),
                                            location.getLongitude()))
                        .icon(mBitmapDesc)
                        .zIndex(9)
                        .draggable(true);
                mMarker.animateType(MarkerOptions.MarkerAnimateType.grow);
                mBaiduMap.addOverlay(mMarker);
            }

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(100)
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(locData);

            //Log.d(TAG, "Current's geo:" + location.getAddrStr());

            // Set callback data
            mCustomMapData.setLatitude(location.getLatitude());
            mCustomMapData.setLongitude(location.getLongitude());
            mCustomMapData.setCurrentGeoLocation(location.getAddrStr());

        }

        public void onReceivePoi(BDLocation poiLocation) {

        }
    }

    // Init overlay
    public void initOverlay() {
        // Geo searching
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);

        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(getBaseContext(),
                        "New position: " + mMarker.getPosition().latitude + ", "
                        + mMarker.getPosition().longitude,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onMarkerDragStart(Marker marker) {

            }
        });

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                if (isLocationSuccessful) {
                    mBaiduMap.clear();
                    mMarker.position(point);
                    mBaiduMap.addOverlay(mMarker);

                    // Set marker's geo
                    mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                            .location(point));
                }

            }
            @Override
            public boolean onMapPoiClick(MapPoi poi) {
                if (isLocationSuccessful) {
                    mBaiduMap.clear();
                    mMarker.position(poi.getPosition());
                    mBaiduMap.addOverlay(mMarker);

                    // Set marker's geo
                    mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                            .location(poi.getPosition()));

                }
                return true;
            }
        });

    }

    // Reverse Geo
    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(getBaseContext(), "No find the location of marker",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d(TAG, "Get geo:" + result.getAddress());
        Toast.makeText(getBaseContext(), result.getAddress(),
                Toast.LENGTH_SHORT).show();
        mCustomMapData.setTargetGeoLocation(
                result.getAddress());

    }

    // Geo to LatLng position(not used)
    @Override
    public void onGetGeoCodeResult(GeoCodeResult result) {

    }
    @Override
    public void onBackPressed() {
        setResult(resultCode,
                getIntent().putExtra("data", mCustomMapData));
        super.onBackPressed();
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
        //mLocClient.unRegisterLocationListener(myListener);
        mLocClient.stop();

        // Close map
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();

        // Recycle mark
        //mBitmapDesc.recycle();
    }

}
