package com.example.kidsland;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AtividadesList extends AppCompatActivity {

    //Instance Variables
    private static final String TAG = "AtividadesList";
    ArrayList<String> titles;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    private RequestQueue mQueue;


    //Constructors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades_list);
        titles = new ArrayList<>();

        //Create Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //Get Request of the Activities

        mQueue = Volley.newRequestQueue(this);

        String url= "http://10.0.2.2:8080/Back-end/RewardsGet";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("MSG");

                            for(int i=0; i < jsonArray.length(); i++){
                                JSONObject activity = jsonArray.getJSONObject(i);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
/*
        titles.add("ola");
        titles.add("miguel");
        titles.add("lol");
        titles.add("ola");
        titles.add("miguel");
        titles.add("lol");
        titles.add("ola");
        titles.add("miguel");
        titles.add("lol");
        titles.add("ola");
        titles.add("miguel");
        titles.add("lol");
        titles.add("ola");
        titles.add("miguel");
        titles.add("lol");*/



        for (int i=0; i < titles.size(); i++){
            Log.d(TAG, "onCreate: "+ titles.get(i));
        }

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AtividadesListAdapter(titles);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}