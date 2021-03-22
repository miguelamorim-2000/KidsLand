package com.example.kidsland;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AtividadesList extends AppCompatActivity {

    //Instance Variables
    private static final String TAG = "AtividadesList";
    ArrayList<ListItem> listItems;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    private Button backbutton;





    //Constructors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades_list);
        backbutton = findViewById(R.id.button9);



        //FETCH ACTIVITIES FROM DATABASE

        listItems = new ArrayList<>();
        //Create Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);




        //HTTP GET
        OkHttpClient client = new OkHttpClient();

        String url = "http://188.82.156.135:8080/Back-end/ActivitiesRGet";

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

                        listItems.add(new ListItem(jsonItem.getString("description"), jsonItem.getString("address" ) + " , " + jsonItem.getString("county" ) +
                                " , " +jsonItem.getString("district" ), jsonItem.getString("photo")));




                    }

                    AtividadesList.this.runOnUiThread(() -> {


                        //Create Recycler View
                        mAdapter = new AtividadesListAdapter(listItems);
                        mRecyclerView.setLayoutManager(mLayoutManager);

                        //CREATE ADAPTER
                        mRecyclerView.setAdapter(mAdapter);


                        System.out.println(listItems);
                        for (int j=0; j < listItems.size(); j++){
                            Log.d(TAG, "onCreate: "+ listItems.get(j));
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
                Intent intent = new Intent( AtividadesList.this, com.example.kidsland.MenuActivity.class);
                startActivity(intent);
                finish();

            }

        });




    }
}