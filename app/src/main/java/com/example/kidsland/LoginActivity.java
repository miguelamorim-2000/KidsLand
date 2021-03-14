package com.example.kidsland;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.kidsland.backend.User;

public class LoginActivity extends AppCompatActivity {
    private Button botaoLogin;
    private User utilizadorLigado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        botaoLogin = (Button)findViewById(R.id.buttonLogin);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivity.this, com.example.kidsland.MenuActivity.class);
                startActivity(intent);
                finish();

            }

        });
    }
}