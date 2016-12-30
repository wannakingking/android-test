package net.ctrlor.test.mapdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapDemo extends AppCompatActivity
                        implements OnMapReadyCallback{

    private GoogleMap mGoogleMap;
    private int resultCode = 0;
    private CustomMapData mCustomMapData = new CustomMapData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_demo);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_google_map_demo);
        mapFragment.getMapAsync(this);
    }

    // Google map ready ?
    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("origin"));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        mCustomMapData.setMapVendor("Google");

        setResult(resultCode, getIntent().
                putExtra("data", mCustomMapData));

        super.onBackPressed();

    }
}