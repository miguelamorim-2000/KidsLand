package com.example.kidsland;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PremiosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premios);
    }
}