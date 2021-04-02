package com.example.kidsland;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidsland.backend.SessionManagement;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PremiosActivity extends AppCompatActivity {
    Button previousPage, settings;
    int id_child;
    TextView points1Txt, points1Txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premios);

        previousPage = findViewById(R.id.button18);
        settings = findViewById(R.id.button19);
        points1Txt = findViewById(R.id.points1Txt);
        points1Txt2 =findViewById(R.id.points1Txt2);



        previousPage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });

        settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent( PremiosActivity.this, com.example.kidsland.DefinicoesActivity.class);
                startActivity(intent);


            }
        });

        //GET USER DATA
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SessionManagement sessionManagement = new SessionManagement(this);
        int id_child = sessionManagement.getID_CHILD();

        //HTTP GET
        OkHttpClient client = new OkHttpClient();

        String url = "http://188.82.156.135:8080/Back-end/TotalPointsChildGet?id_child=" + id_child;

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
                            JSONObject root = new JSONObject(body);
                        System.out.println(root);
                        int points = 0;


                       for ( int i = 0; i < root.length(); i++) {
                           points = root.getInt("total_points");




                        }


                       //DISPLAY POINTS
                        int finalPoints = points;

                        PremiosActivity.this.runOnUiThread(() -> {

                            //DISPLAY LEVEL BASED ON POINTS
                            if (finalPoints < 10){
                                points1Txt2.setText("0");
                            } else if (finalPoints >= 10 && finalPoints < 25){
                                points1Txt2.setText("1");
                            } else if (finalPoints >= 25 && finalPoints <45){
                                points1Txt2.setText("2");

                            } else if (finalPoints >= 45 && finalPoints <70){
                                points1Txt2.setText("3");

                            } else if (finalPoints >= 70){
                                points1Txt2.setText("4");

                            }

                /*            int levelI = 0;
                            int pointsLevelF = 9;
                            int levelF;

                            while (pointsLevelF < finalPoints){
                                levelI = levelI + 1;
                                pointsLevelF = pointsLevelF + (levelI + 1) * 5 + 5;
                            }

                            points1Txt2.setText(String.valueOf(levelI));*/



                            //DISPLAY POINTS
                            points1Txt.setText(finalPoints + " pontos");

                        });





                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Gson gson = new Gson();


                }
            }
        });





    }




}