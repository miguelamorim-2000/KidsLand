package com.example.kidsland;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidsland.backend.ListItem;
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

public class Evaluation2Activity extends AppCompatActivity {

    //Instance Variables
    private static final String TAG = "Evaluation2Activity";
    ArrayList<ListItem> listItems;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    private Button backbutton;
    private int id_child;





    //Constructors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation2);
        backbutton = findViewById(R.id.button64);

        //PASS ID OF CHILD
        SessionManagement sessionManagement = new SessionManagement(Evaluation2Activity.this);
        id_child = sessionManagement.getID_CHILD();




        //FETCH ACTIVITIES FROM DATABASE

        listItems = new ArrayList<>();
        //Create Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view433);


        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);




        //HTTP GET
        OkHttpClient client = new OkHttpClient();

        String url = "http://188.82.156.135:8080/Back-end/ActivityRequestNonActivityGet";

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



                            listItems.add(new ListItem(jsonItem.getString("description" ),jsonItem.getString("address") + " , " + jsonItem.getString("county" ) +
                                    " , " +jsonItem.getString("district" ), jsonItem.getString("photo"), jsonItem.getInt("id_request"), jsonItem.getString("time")));




                        }

                        Evaluation2Activity.this.runOnUiThread(() -> {


                            //Create Recycler View
                            mAdapter = new Evaluation1ActivityAdapter(listItems, id_child, Evaluation2Activity.this);
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
                finish();

            }

        });




    }


}
