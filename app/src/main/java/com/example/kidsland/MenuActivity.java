package com.example.kidsland;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidsland.backend.SessionManagement;
import com.squareup.picasso.Picasso;

public class MenuActivity extends AppCompatActivity {
    private Button btnPrizes;
    private Button btnFacts;
    private Button btnActivities;
    private Button btnHistoric;
    private Button btnSuggestions;
    private Button btnDefinitions, mapButton,historyBtn, gameEvaButton, profileButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnPrizes = (Button)findViewById(R.id.prizesButton);
        btnActivities = (Button)findViewById(R.id.gameButton2);
        btnDefinitions = (Button)findViewById(R.id.defitionsButton);
        btnFacts = (Button)findViewById(R.id.factsButton);
        mapButton = (Button)findViewById(R.id.mapButton);
        historyBtn = (Button) findViewById(R.id.historyBtn);
        btnSuggestions = (Button) findViewById(R.id.sugestionsButton);
        gameEvaButton = (Button) findViewById(R.id.gameEvaButton);
        profileButton = (Button) findViewById(R.id.profileButton);




        //GET USER DATA
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SessionManagement sessionManagement = new SessionManagement(this);
        int id_user = sessionManagement.getSession();
        String name_child = sessionManagement.getNAME_CHILD();


        //SET FIELDS WITH DATA
        TextView txt1 = (TextView)findViewById(R.id.nameTxt);
        txt1.setText(name_child);

        //SHOW PHOTO
        String photo =  "http://188.82.156.135:8080/Back-end/IMAGES/" + sessionManagement.getPhoto();
        System.out.println(photo);
        Picasso.get().load(photo)
                .placeholder(R.drawable.loadingicon)
                .error(R.drawable.loadingicon)
                .into((ImageView) findViewById(R.id.photo));




        //BUTTON LISTENERS TO REDIRECT TO PAGES
        btnPrizes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuActivity.this, com.example.kidsland.PremiosActivity.class);
                startActivity(intent);


            }

        });

        btnActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuActivity.this, com.example.kidsland.AtividadesList.class);
                startActivity(intent);


            }

        });

        btnDefinitions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuActivity.this, com.example.kidsland.DefinicoesActivity.class);
                startActivity(intent);


            }

        });

        btnFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuActivity.this, com.example.kidsland.FactosActivity.class);
                startActivity(intent);


            }

        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuActivity.this, com.example.kidsland.MapActivity.class);
                startActivity(intent);


            }

        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuActivity.this, com.example.kidsland.HistoryActivity.class);
                startActivity(intent);


            }

        });

        btnSuggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuActivity.this, com.example.kidsland.SugestionActivity.class);
                startActivity(intent);


            }

        });

        gameEvaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuActivity.this, com.example.kidsland.AvaliacaoAtividade.class);
                startActivity(intent);


            }

        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MenuActivity.this, com.example.kidsland.VisualizarDadosCriancaActivity.class);
                startActivity(intent);
                finish();


            }

        });
    }
}