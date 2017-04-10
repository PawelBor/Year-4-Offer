package com.ioffer.gediminas.ioffer_android;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Example markers
        LatLng item = new LatLng(12, -80);
        mMap.addMarker(new MarkerOptions().position(item).title("Cool Item title"));

        LatLng items = new LatLng(34, -44);
        mMap.addMarker(new MarkerOptions().position(items).title("Cool Item title"));

        LatLng ss = new LatLng(-11, -123);
        mMap.addMarker(new MarkerOptions().position(ss).title("Cool Item title"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(item));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(5), 9000, null);
    }
}
