package com.example.kidsland;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.Header;



public class RegisterActivity extends AppCompatActivity {

        EditText email, password;
        Button submitbutton;
        String e, p;
        RequestParams params;
        AsyncHttpClient client;
        String URL ="http://10.0.2.2:8080/Back-end/Register";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText)findViewById(R.id.emailTxt);
        password = (EditText) findViewById(R.id.passwordTxt);
        submitbutton = (Button) findViewById(R.id.submitbutton);

        submitbutton.setOnClickListener(new View.OnClickListener() {
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
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        Toast.makeText(RegisterActivity.this, "Registado com sucesso!"+ response, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        Toast.makeText(RegisterActivity.this, "Falha no registo!", Toast.LENGTH_SHORT).show();
                        System.out.println(responseString);
                    }

                });


            }
        });

    }



}