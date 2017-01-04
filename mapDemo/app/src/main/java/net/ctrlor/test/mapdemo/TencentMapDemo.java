package net.ctrlor.test.mapdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.CameraPosition;
import com.tencent.mapsdk.raster.model.CircleOptions;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.CameraUpdate;
import com.tencent.tencentmap.mapsdk.map.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;


/**
 * Created by Adminstrator on 2017/1/3.
 */

public class TencentMapDemo extends AppCompatActivity implements
        TencentLocationListener {

    private static final String TAG = "ctrlor_____";
    private static int mResultCode;
    private CustomMapData mCustomMapData = new CustomMapData();
    private static boolean isFirstLocation = true;

    private MapView mMapView = null;
    private TencentMap mTencentMap;
    private Marker mLocationMarker;

    private TencentLocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tencent_map_demo);

        initMap(savedInstanceState);

        mLocationManager = TencentLocationManager.getInstance(this);
        mLocationManager.setCoordinateType(TencentLocationManager.COORDINATE_TYPE_GCJ02);

        Button btnOk = (Button) findViewById(R.id.btn_ok_tencent_map_demo);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultCode = 1;
                onBackPressed();

            }
        });

        Button btnCancel = (Button) findViewById(R.id.btn_cancel_tencent_map_demo);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultCode = 0;
                onBackPressed();
            }
        });
    }

    private void initMap(Bundle savedInstanceState) {
        mMapView = (MapView) findViewById(R.id.tencent_map_demo);
        mMapView.onCreate(savedInstanceState);
        mTencentMap = mMapView.getMap();
        mTencentMap.setZoom(15);

        mTencentMap.setOnMapClickListener(new TencentMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                addMarker(latLng);
            }
        });

    }

    @Override
    public void onLocationChanged(TencentLocation location,
                                  int error, String reason) {

        if (error == TencentLocation.ERROR_OK) {
            // Locate successful

            LatLng latLng = new LatLng(location.getLatitude(),
                    location.getLongitude());

            // Add current location icon
            mTencentMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.fromResource(
                                R.drawable.one_point)));
            /*
            mTencentMap.addCircle(new CircleOptions()
                    .center(latLng)
                    .radius((double)location.getAccuracy())
                    .fillColor(0x440000ff)
                    .strokeWidth(0f));
                    */

            if (isFirstLocation) {

                mTencentMap.moveCamera(new CameraUpdateFactory()
                        .newCameraPosition(new CameraPosition(latLng, 15.0f)));
                isFirstLocation = false;
            }

            mCustomMapData.setCurrentGeoLocation(location.getAddress());
            mCustomMapData.setLatitude(location.getLatitude());
            mCustomMapData.setLongitude(location.getLongitude());

            Log.d(TAG, "address:" + location.getAddress());
        } else {
            // Locate fail
            Log.d(TAG, "Locating fail, error:" + error);
        }

    }

    private void addMarker(LatLng latLng) {
        if (mLocationMarker == null) {
            mLocationMarker = mTencentMap.addMarker(
                    new MarkerOptions()
                            .draggable(true)
                            .icon(BitmapDescriptorFactory
                                    .defaultMarker())
                            .position(latLng)
                            .title("marker")
                            .snippet(latLng.toString()));
        } else {
            mLocationMarker.setPosition(latLng);
            mLocationMarker.setSnippet(latLng.toString());
        }
        mLocationMarker.showInfoWindow();

    }

    /*
    private void latlngToGeo(LatLng latLng) {
        Geo2AddressParam param = new Geo2AddressParam().location(latLng);
        Geo2AddressResultObject object = (Geo2AddressResultObject)
    }

    @Override
    public void geo2address(Geo2AddressParam object, HttpResponseListener listener) {

    }
    */
    @Override
    public void onStatusUpdate(String name, int status, String desc) {

        Log.d(TAG, "onStatusUpdated");

    }

    private void startLocation() {
        TencentLocationRequest request = TencentLocationRequest.create();
        request.setInterval(1000);
        request.setAllowCache(true);
        request.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_NAME);
        mLocationManager.requestLocationUpdates(request, this);
    }

    private void stopLocation() {
        mLocationManager.removeUpdates(this);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        mCustomMapData.setMapVendor("Tencent");
        setResult(mResultCode,
                getIntent().putExtra("data", mCustomMapData));
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        stopLocation();
        mMapView.onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        startLocation();
        mMapView.onResume();
        super.onResume();

    }


    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();

    }


}
