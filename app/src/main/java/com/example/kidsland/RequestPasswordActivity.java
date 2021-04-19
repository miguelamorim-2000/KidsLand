package com.example.kidsland;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidsland.backend.SessionManagement;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RequestPasswordActivity extends AppCompatActivity {
    Button submitbutton123, backBtn1231;
    EditText email;
    String e;
    RequestParams params;
    AsyncHttpClient client;
    String URL ="http://188.82.156.135:8080/Back-end/SendEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_password);

        submitbutton123 = findViewById(R.id.submitbutton123);
        backBtn1231 = findViewById(R.id.backBtn1231);
        email = findViewById(R.id.nameTxtRegister123);

        submitbutton123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e = email.getText().toString();
                params= new RequestParams();
                params.put("email", e);
                client = new AsyncHttpClient();
                client.post(URL, params, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        System.out.println(response);

                        //INICIALIZE VARIABLES
                        String status = "";
                        //GET STATUS OF OPERATION
                        try {
                            status = response.getString("STATUS");
                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
                        if (status.equals("200")){

                            Toast.makeText(RequestPasswordActivity.this, "Email enviado com sucesso!", Toast.LENGTH_SHORT).show();

                            String email_temp = email.getText().toString();
                            //save email on cache
                            SessionManagement sessionManagement = new SessionManagement(RequestPasswordActivity.this);
                            sessionManagement.tempEmail(email_temp);
                            System.out.println(sessionManagement.getEMAILTEMPORARY());

                            //Open new window
                            Intent intent = new Intent( RequestPasswordActivity.this, com.example.kidsland.InsertTemporaryPW.class);
                            startActivity(intent);

 }


                        if (status.equals("400")){
                            Toast.makeText(RequestPasswordActivity.this, "Email inserido não válido", Toast.LENGTH_SHORT).show();



                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(RequestPasswordActivity.this, "Falha no envio!", Toast.LENGTH_SHORT).show();
                        System.out.println(errorResponse);
                    }

                });

            }
        });

        backBtn1231.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CLEAN STORAGE AND CLOSE
                SessionManagement sessionManagement = new SessionManagement(RequestPasswordActivity.this);
                sessionManagement.removeTempMail();
                finish();

            }
        });
    }
}