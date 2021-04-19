package com.example.kidsland;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    Button backBtnMap, defitionsButton3 ;
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
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
                finish();

            }

        });

        defitionsButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MapActivity.this, com.example.kidsland.DefinicoesActivity.class);
                startActivity(intent);

            }

        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        //HTTP GET
        OkHttpClient client = new OkHttpClient();

        String url = "http://188.82.156.135:8080/Back-end/ActivityRequestFutureGet";

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String body = response.body().string();

                    try {
                        JSONArray msg = new JSONArray(body);

                        for ( int i = 0; i < msg.length(); i++) {

                            String description;
                            JSONObject jsonItem = msg.getJSONObject(i);

                            LatLng activity = new LatLng(jsonItem.getDouble("latitude"), jsonItem.getDouble("longitude"));
                            description = jsonItem.getString("description")  ;


                            MapActivity.this.runOnUiThread(() -> {
                                map.addMarker(new MarkerOptions().position(activity).title(description));
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(activity, 15.0f ));


                            });



                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Gson gson = new Gson();


                }
            }
        });




    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}