package com.example.kidsland;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidsland.backend.Fact;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FactosActivity extends AppCompatActivity {

    //Instance Variables
    private static final String TAG = "AtividadesList";
    ArrayList<Fact> listFacts;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    private Button backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factos);
        backbutton = findViewById(R.id.button14);

        //FETCH ACTIVITIES FROM DATABASE

        listFacts = new ArrayList<>();
        //Create Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_viewFacts);


        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);




        //HTTP GET
        OkHttpClient client = new OkHttpClient();

        String url = "http://188.82.156.135:8080/Back-end/FactGet";

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
                        JSONArray msg = root.getJSONArray("MSG");
                        for ( int i = 0; i < msg.length(); i++) {
                            JSONObject jsonItem = msg.getJSONObject(i);

                            listFacts.add(new Fact(jsonItem.getString("name"), jsonItem.getString("description"), jsonItem.getString("photo")));




                        }

                        FactosActivity.this.runOnUiThread(() -> {


                            //Create Recycler View
                            mAdapter = new FactosActivityAdapter(listFacts);
                            mRecyclerView.setLayoutManager(mLayoutManager);

                            //CREATE ADAPTER
                            mRecyclerView.setAdapter(mAdapter);


                            System.out.println(listFacts);
                            for (int j=0; j < listFacts.size(); j++){
                                Log.d(TAG, "onCreate: "+ listFacts.get(j));
                            }

                        });



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Gson gson = new Gson();


                }
            }
        });


        //ON CLICK ICONS

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( FactosActivity.this, com.example.kidsland.MenuActivity.class);
                startActivity(intent);
                finish();

            }

        });


    }


}