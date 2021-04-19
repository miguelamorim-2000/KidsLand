package com.example.kidsland;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidsland.backend.HistoryItem;
import com.example.kidsland.backend.SessionManagement;
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

public class HistoryActivity extends AppCompatActivity {
    //Instance Variables
    private static final String TAG = "HistoryList";
    ArrayList<HistoryItem> historyItems;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    private Button defitionsButton4, backBtnHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        defitionsButton4 = findViewById(R.id.defitionsButton4);
        backBtnHistory = findViewById(R.id.backBtnHistory);

        //GET USER DATA
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SessionManagement sessionManagement = new SessionManagement(this);
        int id_child = sessionManagement.getID_CHILD();


        //FETCH ACTIVITIES FROM DATABASE

        historyItems = new ArrayList<>();
        //Create Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view54665);


        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);


        //HTTP GET
        OkHttpClient client = new OkHttpClient();

        String url = "http://188.82.156.135:8080/Back-end/ActivityRequestByIdChildGet?id_child="+id_child;

        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String body = response.body().string();
                    System.out.println(body);


                    try {


                        JSONArray msg = new JSONArray(body);


                        for (int i = 0; i < msg.length(); i++) {
                            JSONObject jsonItem = msg.getJSONObject(i);




                            historyItems.add(new HistoryItem(jsonItem.getString("description"), jsonItem.getString("address") + " , " + jsonItem.getString("county") +
                                    " , " + jsonItem.getString("district"), jsonItem.getString("photo"), jsonItem.getString("date"), jsonItem.getInt("points")));


                        }

                        HistoryActivity.this.runOnUiThread(() -> {


                            //Create Recycler View
                            mAdapter = new HistoryListAdapter(historyItems);
                            mRecyclerView.setLayoutManager(mLayoutManager);

                            //CREATE ADAPTER
                            mRecyclerView.setAdapter(mAdapter);


                            System.out.println(historyItems);
                            for (int j = 0; j < historyItems.size(); j++) {
                                Log.d(TAG, "onCreate: " + historyItems.get(j));
                            }

                        });


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                    Gson gson = new Gson();


                }
            }
        });

        defitionsButton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent( HistoryActivity.this, com.example.kidsland.DefinicoesActivity.class);
                startActivity(intent);

            }
        });

        backBtnHistory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }
}
