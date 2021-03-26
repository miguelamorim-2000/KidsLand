package com.example.kidsland;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidsland.backend.SessionManagement;
import com.example.kidsland.backend.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
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
    String URL ="http://188.82.156.135:8080/Back-end/Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        botaoLogin = (Button)findViewById(R.id.buttonLogin);
        createAccBtn = (TextView) findViewById(R.id.createAccBtn);
        email = (EditText)findViewById(R.id.emailTxtLogin);
        password = (EditText) findViewById(R.id.passwordTxtLogin);

        //SEE IF SESSION IS RUNNING ON START;
       SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
       int userID = sessionManagement.getSession();

       if (userID != -1){

           //IF USER NOT LOGGED IN
           Intent intent = new Intent( LoginActivity.this, com.example.kidsland.MenuActivity.class);
           startActivity(intent);
           finish();
       } else {
           //DO NOTHING
       }




        // ATTEMPT LOGIN
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


                        String status = "";
                        try {
                            status = response.getString("STATUS");
                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
                        if (status.equals("200")){
                            //CREATE SESSION
                            User user = new User("ola@gmail.com", "ola", "ola", 3);
                            SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                            sessionManagement.saveSession(user);
                            Toast.makeText(LoginActivity.this, "Login com sucesso!", Toast.LENGTH_SHORT).show();


                            //MOVE TO MENU PAGE
                            Intent intent = new Intent( LoginActivity.this, com.example.kidsland.MenuActivity.class);
                        startActivity(intent);
                        finish(); }


                        if (status.equals("400")){
                                Toast.makeText(LoginActivity.this, "Erro no Login", Toast.LENGTH_SHORT).show();
                                email.setText("");
                                password.setText("");
                                findViewById(R.id.errorTxt).setVisibility(View.VISIBLE);
                                findViewById(R.id.imageView6).setVisibility(View.VISIBLE);


                        }

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