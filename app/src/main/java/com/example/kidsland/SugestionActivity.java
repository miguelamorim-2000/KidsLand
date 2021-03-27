package com.example.kidsland;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SugestionActivity extends AppCompatActivity {
    Button backBtnSugestions, settingBtnSugestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestion);

        backBtnSugestions = findViewById(R.id.backBtnSugestions);
        settingBtnSugestions =findViewById(R.id.settingBtnSugestions);


        //MOVE TO LOGIN PAGE

        backBtnSugestions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent( SugestionActivity.this, com.example.kidsland.MenuActivity.class);
                startActivity(intent);
                finish();

            }
        });

        settingBtnSugestions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent( SugestionActivity.this, com.example.kidsland.DefinicoesActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}