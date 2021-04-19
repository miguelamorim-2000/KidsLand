package com.example.kidsland;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class InsertTemporaryPW extends AppCompatActivity {
    Button backBtn121231, submitBtn;
    EditText codeField;
    RequestParams params;
    AsyncHttpClient client;
    String URL ="http://188.82.156.135:8080/Back-end/CheckPassChangerPost";
    String email, code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_temporary_p_w);

        backBtn121231= findViewById(R.id.backBtn121231);
        submitBtn = findViewById(R.id.submitbutton123433);
        codeField = findViewById(R.id.nameTxtRegister123433);


        //GET USER DATA
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SessionManagement sessionManagement = new SessionManagement(this);
        email = sessionManagement.getEMAILTEMPORARY();

        backBtn121231.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code= codeField.getText().toString();
                params= new RequestParams();
                System.out.println(email + " " + code);
                params.put("email", email);
                params.put("pass_changer", code);
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

                            Toast.makeText(InsertTemporaryPW.this, "Código inserido válido!", Toast.LENGTH_SHORT).show();

                           //Open new window
                            Intent intent = new Intent( InsertTemporaryPW.this, com.example.kidsland.ChangePasswordActivity.class);
                            startActivity(intent);

                        }


                        if (status.equals("400")){
                            Toast.makeText(InsertTemporaryPW.this, "O Código inserido não é válido", Toast.LENGTH_SHORT).show();



                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(InsertTemporaryPW.this, "Falha no sistema.", Toast.LENGTH_SHORT).show();
                        System.out.println(errorResponse);
                    }

                });
            }
        });
    }
}