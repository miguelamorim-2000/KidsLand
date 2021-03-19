package com.example.kidsland;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                Intent intent = new Intent( MenuActivity.this, com.example.kidsland.testePrincipal.class);
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
    }
}