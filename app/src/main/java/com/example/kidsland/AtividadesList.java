package com.example.kidsland;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private static final String URL_DATA = "http://188.82.156.135:8080/Back-end/ActivitiesRGet";
    ArrayList<ListItem> listItems;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;




    //Constructors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades_list);


        listItems = new ArrayList<>();
        listItems.add(new ListItem("ola","ola"));
        //Create Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        //Create Recycler View
        mAdapter = new AtividadesListAdapter(listItems);


        mLayoutManager = new LinearLayoutManager(this);

        //CREATE ADAPTER
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);



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
                    //listItems.add(new ListItem("ola","ola"));
                    for ( int i = 0; i < msg.length(); i++) {
                        JSONObject jsonItem = msg.getJSONObject(i);

                        listItems.add(new ListItem(jsonItem.getString("description"), jsonItem.getString("address")));




                    }

                    AtividadesList.this.runOnUiThread(() -> {


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




    }
}
