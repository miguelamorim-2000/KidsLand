package com.example.kidsland;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class testePrincipal extends AppCompatActivity {

    private static final String TAG ="testePrincipal";
    ArrayList<String> activities;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_principal);

        mRecyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        activities = new ArrayList<String>();
        activities.add("miguel");
        activities.add("kkk");
        activities.add("adeus");

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MainAdapter(activities);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}