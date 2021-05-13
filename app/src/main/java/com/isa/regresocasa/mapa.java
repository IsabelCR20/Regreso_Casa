package com.isa.regresocasa;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapa extends FragmentActivity implements OnMapReadyCallback {
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    private GoogleMap mMap;
    double possition[] = new double[2];
    boolean firstPossition;     // Bandera para marcar por primera vez la posición incial

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        firstPossition = false;
        otro();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("cosa", "onMAP Ready Latitud: " + possition[0] + "  Longitud: " + possition[1]);
        // Agregar marca y mover a posición
        LatLng fp = new LatLng(possition[0], possition[1]);
        mMap.addMarker(new MarkerOptions().position(fp).title("Ubicación actual"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fp));
    }



    private void otro(){
        if (ActivityCompat.checkSelfPermission(
                getBaseContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            locationManager =  (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    if(!firstPossition) {
                        possition[0] = location.getLatitude();
                        possition[1] = location.getLongitude();
                        LatLng fp = new LatLng(possition[0], possition[1]);
                        mMap.addMarker(new MarkerOptions().position(fp).title("Ubicación actual"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(fp));
                        firstPossition = true;
                        Log.d("cosa", "UPDATES Latitud: " + possition[0] + "  Longitud: " + possition[1]);
                    }
                }
                @Override
                public void onProviderEnabled(@NonNull String provider) { }

                @Override
                public void onProviderDisabled(@NonNull String provider) { }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) { }
            });
        }
    }
}

// Referencias
// https://javapapers.com/android/get-current-location-in-android/