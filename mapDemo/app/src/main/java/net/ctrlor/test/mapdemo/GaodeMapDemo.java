package net.ctrlor.test.mapdemo;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationClientOption.AMapLocationProtocol;
import com.amap.api.location.AMapLocationListener;


public class GaodeMapDemo extends AppCompatActivity {
	private static final String TAG = "ctrlor_____";

	// Amap
	private AMap aMap;
	private MapView mapView;
	private Marker marker;

	private AMapLocationClient mLocationClient = null;
	private AMapLocationClientOption mLocationClientOption;

	private static int resultCode;
	private static CustomMapData mCustomMapData = new CustomMapData();

    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gaode_map_demo);

		mapView = (MapView) findViewById(R.id.gaode_map_demo);
		mapView.onCreate(savedInstanceState);

		initView();
		initLocation();

		Button btnOk = (Button) findViewById(R.id.btn_ok_gaode_map_demo);
		btnOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				resultCode = 1;
				onBackPressed();

			}
		});

		Button btnCancel = (Button) findViewById(R.id.btn_cancel_gaode_map_demo);
		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				resultCode = 0;
				onBackPressed();
			}
		});
	}

	private void initView() {
		if (aMap == null) {
			aMap = mapView.getMap();
		}

		// Set type of map
		aMap.setMapType(AMap.MAP_TYPE_NORMAL);

		// Add marker
		marker = aMap.addMarker(new MarkerOptions()
				.position(new LatLng(109.0, 23.0))
				.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
						.decodeResource(getResources(), R.drawable.icon_mark)))
				.draggable(true));

    }

	private void initLocation() {
		mLocationClient = new AMapLocationClient(this.getApplicationContext());
		mLocationClientOption = new AMapLocationClientOption();
		mLocationClientOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
		mLocationClientOption.setOnceLocationLatest(true);
		mLocationClientOption.setLocationCacheEnable(true);
		mLocationClientOption.setNeedAddress(true);

		mLocationClient.setLocationOption(mLocationClientOption);

		// Set the parameter of location client
		mLocationClient.setLocationListener(mLocationListener);

		// Start to locate
		mLocationClient.startLocation();


	}

	// Location listener
	private AMapLocationListener mLocationListener = new AMapLocationListener() {
		@Override
		public void onLocationChanged(AMapLocation location){
			if (null != location) {
				if (location.getErrorCode() == 0) {
					// Successful
					mCustomMapData.setCurrentGeoLocation(
							location.getAddress());
					mCustomMapData.setLatitude(
							location.getLatitude());
					mCustomMapData.setLongitude(
							location.getLongitude());

				} else {
					// Fail
					Log.d(TAG, "Locate error, code: " +
						location.getErrorCode() + ", error info:" +
						location.getErrorInfo());
				}
				//String result = Utils.getLocationStr(location);
			}

		}
	};

	@Override
	public void onBackPressed() {
		mCustomMapData.setMapVendor("Gaode");
		setResult(resultCode,
				getIntent().putExtra("data", mCustomMapData));
		super.onBackPressed();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

}
