package com.example.kidsland;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    Button backBtnMap, defitionsButton3 ;
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this );

        backBtnMap = findViewById(R.id.backBtnMap);
        defitionsButton3 = findViewById(R.id.defitionsButton3);




        backBtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MapActivity.this, com.example.kidsland.MenuActivity.class);
                startActivity(intent);
                finish();

            }

        });

        defitionsButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MapActivity.this, com.example.kidsland.DefinicoesActivity.class);
                startActivity(intent);
                finish();

            }

        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        LatLng Guimaraes = new LatLng(41.44282, -8.29179);
        map.addMarker(new MarkerOptions().position(Guimaraes).title("Guimaraes"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Guimaraes));
        map.animateCamera(CameraUpdateFactory.zoomTo( 12.0f ));


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}