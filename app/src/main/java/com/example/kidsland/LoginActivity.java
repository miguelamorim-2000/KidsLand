package com.example.kidsland;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    private Button botaoLogin;
    private TextView createAccBtn;
    EditText email, password;
    Button submitbutton;
    String e, p;
    RequestParams params;
    AsyncHttpClient client;
    String URL ="http://10.0.2.2:8080/Back-end/Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        botaoLogin = (Button)findViewById(R.id.buttonLogin);
        createAccBtn = (TextView) findViewById(R.id.createAccBtn);
        email = (EditText)findViewById(R.id.emailTxtLogin);
        password = (EditText) findViewById(R.id.passwordTxtLogin);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e = email.getText().toString();
                p = password.getText().toString();
                params= new RequestParams();
                params.put("email", e);
                params.put("password", p);
                client = new AsyncHttpClient();
                client.post(URL, params, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        System.out.println(response);
                        Toast.makeText(LoginActivity.this, "Login com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent( LoginActivity.this, com.example.kidsland.MenuActivity.class);
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(LoginActivity.this, "Falha no Login!", Toast.LENGTH_SHORT).show();
                        System.out.println(errorResponse);
                    }

                });


            }
        });
        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivity.this, com.example.kidsland.RegisterActivity.class);
                startActivity(intent);
                finish();

            }

        });
    }
}