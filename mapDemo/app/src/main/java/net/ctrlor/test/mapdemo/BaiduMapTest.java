package net.ctrlor.test.mapdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

// Baidu map basic
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;

// Baidu map location
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
//import com.baidu.location.BDNotifyListener;
import com.baidu.location.Poi;

import com.baidu.mapapi.map.MyLocationConfiguration;

import java.util.List;


public class BaiduMapTest extends AppCompatActivity {

    public MapView mMapView = null;
    // Location
    public LocationClient mLocationClient = null;
    public BDLocationListener mListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initializing
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map_test);

        // Get map
        mMapView = (MapView) findViewById(R.id.baiduMapView_test);

        // Location
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(mListener);

        initLocation();
    }

    private void initLocation() {
        int span = 1000;

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd0911");
        option.setScanSpan(span);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(true);
        option.setEnableSimulateGps(false);

        mLocationClient.setLocOption(option);

    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            // Receive location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time: ")
                    .append(location.getTime())
                    .append("\nerror code: ")
                    .append(location.getLocType())
                    .append("\nlatitude: ")
                    .append(location.getLatitude())
                    .append("\nlongitude: ")
                    .append(location.getLongitude())
                    .append("\nradius: ")
                    .append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {
                sb.append("\nspeed: ")
                        .append(location.getSpeed())
                        .append("\nsatellite: ")
                        .append(location.getSatelliteNumber())
                        .append("\nheight: ")
                        .append(location.getAltitude())
                        .append("\ndirection:")
                        .append(location.getDirection())
                        .append("\naddr: ")
                        .append(location.getAddrStr())
                        .append("\ndescribe: ")
                        .append("gps successful.");
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                sb.append("\naddr: ")
                        .append(location.getAddrStr())
                        // ISP info
                        .append("\noperators: ")
                        .append(location.getOperators())
                        .append("\ndescribe: ")
                        .append("network locating successful.");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
                sb.append("\ndescribe: ")
                        .append("offline locating over.");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe: ")
                        .append("service network locating fail.");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe: ")
                        .append("network error.");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe: ")
                        .append("criteria exception.");
            }

            sb.append("\nlocation describe: ")
                    .append(location.getLocationDescribe());
            List<Poi> list = location.getPoiList();
            if (list != null) {
                sb.append("\npoi list size: ")
                        .append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= ")
                            .append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.d("Baidu map location: " , sb.toString());

        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

}
