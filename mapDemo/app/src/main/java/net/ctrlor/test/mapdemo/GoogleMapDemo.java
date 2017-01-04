package net.ctrlor.test.mapdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.maps.model.PointOfInterest;

import java.io.IOException;

public class GoogleMapDemo extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final String TAG = "ctrlor__________";

    private GoogleMap mGoogleMap;
    private CameraPosition mCameraPosition;

    // The entry point to Google play services, used by the Places API
    // and Fused location provider
    private GoogleApiClient mGoogleApiClient;

    // A request object to store parameters for requests to
    // the Fused location provider api.
    private LocationRequest mLocationRequest;

    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    private final LatLng mDefaultLocation = new LatLng(106.00, 23.33);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION  = 1;
    private boolean mLocationPermissionGranted;

    private Location mCurrentLocation;
    private MarkerOptions markerOptions;

    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LCOATION = "location";


    private int resultCode = 0;
    private CustomMapData mCustomMapData = new CustomMapData();
    private static boolean isFirstLocation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mCurrentLocation = savedInstanceState.getParcelable(KEY_LCOATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        setContentView(R.layout.activity_google_map_demo);

        buildGoogleApiClient();
        mGoogleApiClient.connect();


        // Button click listener
        Button btnOk = (Button) findViewById(R.id.btn_ok_google_map_demo);
        btnOk.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultCode = 1;
                onBackPressed();
            }
        });

        Button btnCancel = (Button) findViewById(R.id.btn_cancel_google_map_demo);
        btnCancel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultCode = 0;
                onBackPressed();
            }
        });
    }

    // Google map ready ?
    @Override
    public void onMapReady(GoogleMap map) {
        mGoogleMap = map;

        updateLocationUI();

        //updateMarkers();

        mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View infoWindow = getLayoutInflater()
                        .inflate(R.layout.custom_info_contents, null);
                TextView title = ((TextView) infoWindow.findViewById(R.id.title));
                title.setText(marker.getTitle());

                TextView snippet = ((TextView) infoWindow.findViewById(R.id.snippet));
                snippet.setText(marker.getSnippet());

                return infoWindow;

            }
        });

        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                makeMarker(latLng);


            }
        });

        mGoogleMap.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
            @Override
            public void onPoiClick(PointOfInterest poi) {
                LatLng latLng = poi.latLng;

                makeMarker(latLng);
                //mCustomMapData.setTargetGeoLocation(latlngToGeo(latLng));

            }
        });

        if (mCameraPosition != null) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
        } else if (mCurrentLocation != null) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(mCurrentLocation.getLatitude(),
                            mCurrentLocation.getLongitude()), DEFAULT_ZOOM));
        } else {
            Log.d(TAG, "Current location is null, Using default");
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    mDefaultLocation, DEFAULT_ZOOM));
        }

        // Set initializing marker.
        if (mCurrentLocation != null) {
            Log.d(TAG, "onMapReady()-> load current location");

            // Current location
            mGoogleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(mCurrentLocation.getLatitude(),
                            mCurrentLocation.getLongitude()))
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.one_point)));

            // Target location
            markerOptions = new MarkerOptions().position(
                    new LatLng(mCurrentLocation.getLatitude(),
                            mCurrentLocation.getLongitude()));
        }

    }

    @Override
    public void onBackPressed() {
        mCustomMapData.setMapVendor("Google");

        mCustomMapData.setTargetGeoLocation(
                latlngToGeo(markerOptions.getPosition()));

        setResult(resultCode, getIntent().
                putExtra("data", mCustomMapData));

        super.onBackPressed();

    }

    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        createLocationRequest();
    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();

        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }

    private void getDeviceLocation() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        if (mLocationPermissionGranted) {
            mCurrentLocation= LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);

            LatLng latLng = new LatLng(mCurrentLocation.getLatitude(),
                    mCurrentLocation.getLongitude());

            if (isFirstLocation) {
                makeMarker(latLng);
                isFirstLocation = false;

                Log.d(TAG, "first location");
            }


            // Set data for call back
            mCustomMapData.setLatitude(latLng.latitude);
            mCustomMapData.setLongitude(latLng.longitude);
            mCustomMapData.setCurrentGeoLocation(latlngToGeo(latLng));

            Log.d(TAG, "Current's location: " + latLng.latitude +
                    ", " + latLng.longitude);

        }
    }

    private String latlngToGeo(LatLng latLng) {
        Address address = null;
        String strAddress = "";
        try {
            if (latLng.latitude != 0 && latLng.longitude !=0) {
                address = new Geocoder(getBaseContext())
                        .getFromLocation(latLng.latitude, latLng.longitude, 1)
                        .get(0);
            }

            if (address != null) {
                strAddress = String.format(
                        "%s, %s, %s",
                        address.getMaxAddressLineIndex() > 0
                                ? address.getAddressLine(0) : "",
                        address.getLocality(),
                        address.getCountryName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return strAddress;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                          @NonNull String[] permissions,
                                          @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (mGoogleMap == null) {
            return;
        }

        if (mLocationPermissionGranted) {
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);

            Log.d(TAG, "update location UI");
        } else {
            mGoogleMap.setMyLocationEnabled(false);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
            mCurrentLocation = null;
        }
    }

    private void makeMarker(LatLng latLng) {
        if (mGoogleMap != null) {
            // Clear marker before
            mGoogleMap.clear();

            markerOptions.position(latLng);
            mGoogleMap.addMarker(markerOptions);
            //mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            //updateMarkers();
        }

    }
    private void updateMarkers() {
        if (mGoogleMap == null) {
            return;
        }

        if (mLocationPermissionGranted) {
            @SuppressWarnings("MissingPermission")
            PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                    .getCurrentPlace(mGoogleApiClient, null);
            result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                @Override
                public void onResult(@NonNull PlaceLikelihoodBuffer likelyPlaces) {
                    for (PlaceLikelihood placeLikelihood : likelyPlaces) {

                        String attributions = (String) placeLikelihood.getPlace()
                                .getAttributions();
                        String snippet = (String) placeLikelihood.getPlace()
                                .getAddress();
                        if (attributions != null) {
                            snippet = snippet + "\n" + attributions;
                        }

                        mGoogleMap.addMarker(new MarkerOptions()
                                .position(placeLikelihood.getPlace().getLatLng())
                                .title((String) placeLikelihood.getPlace().getName())
                                .snippet(snippet));
                    }

                    likelyPlaces.release();
                }

            });
        } else {
            mGoogleMap.addMarker(new MarkerOptions()
                    .position(mDefaultLocation)
                    .title(getString(R.string.default_info_title))
                    .snippet(getString(R.string.default_info_snippet)));

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mGoogleMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION,
                    mGoogleMap.getCameraPosition());
            outState.putParcelable(KEY_LCOATION,
                    mCurrentLocation);
            super.onSaveInstanceState(outState);
        }
    }

    /**
     * Gets the device's current location and builds the map
     * when the Google play services is successfully connected.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        getDeviceLocation();

        // Build the map.
        SupportMapFragment mapFragment  = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_google_map_demo);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        Log.d(TAG, "Connection failed, Error code:" + result.getErrorCode());
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.d(TAG, "Connection suspended");
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        //updateMarkers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (mGoogleApiClient.isConnected()) {
            getDeviceLocation();
        }

        //updateMarkers();
    }

}