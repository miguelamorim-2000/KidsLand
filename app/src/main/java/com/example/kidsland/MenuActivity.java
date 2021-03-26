package com.example.kidsland;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidsland.backend.SessionManagement;

public class MenuActivity extends AppCompatActivity {
    private Button btnPrizes;
    private Button btnFacts;
    private Button btnActivities;
    private Button btnHistoric;
    private Button btnSuggestions;
    private Button btnDefinitions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnPrizes = (Button)findViewById(R.id.prizesButton);
        btnActivities = (Button)findViewById(R.id.gameButton);
        btnDefinitions = (Button)findViewById(R.id.defitionsButton);
        btnFacts = (Button)findViewById(R.id.factsButton);

        System.out.println("ola1");
        //GET USER DATA
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SessionManagement sessionManagement = new SessionManagement(this);
        int id_user = sessionManagement.getSession();
        System.out.println(id_user);
        System.out.println("ola2");


        //BUTTON LISTENERS TO REDIRECT TO PAGES
        btnPrizes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuActivity.this, com.example.kidsland.PremiosActivity.class);
                startActivity(intent);
                finish();

            }

        });

        btnActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuActivity.this, com.example.kidsland.AtividadesList.class);
                startActivity(intent);
                finish();

            }

        });

        btnDefinitions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuActivity.this, com.example.kidsland.DefinicoesActivity.class);
                startActivity(intent);
                finish();

            }

        });

        btnFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuActivity.this, com.example.kidsland.FactosActivity.class);
                startActivity(intent);
                finish();

            }

        });
    }
}