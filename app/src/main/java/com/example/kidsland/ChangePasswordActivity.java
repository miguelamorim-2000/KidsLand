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

import com.example.kidsland.backend.JsonPlaceHolderApi;
import com.example.kidsland.backend.SessionManagement;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangePasswordActivity extends AppCompatActivity {
    Button backBtn, submitBtn;
    RequestParams params;
    AsyncHttpClient client;
    String URL ="http://188.82.156.135:8080/Back-end/CheckPassChangerPost";
    String email, code;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    EditText passwordPUT, passwordConfirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        backBtn = findViewById(R.id.backBtn14531);
        submitBtn = findViewById(R.id.submitbutton15563);
        passwordPUT = findViewById(R.id.nameTxtRegister2);
        passwordConfirm = findViewById(R.id.nameTxtRegister3);

        //GET EMAIL IN CAUSE
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SessionManagement sessionManagement = new SessionManagement(this);
        email = sessionManagement.getEMAILTEMPORARY();

        //PUT DEFINITION

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://188.82.156.135:8080/Back-end/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);



        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              String passwordChange =  passwordPUT.getText().toString();
              String passwordChangeConf = passwordConfirm.getText().toString();

              if(passwordChange.length() == 0 || passwordChangeConf.length()==0){
                  Toast.makeText(ChangePasswordActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();

              }else {
                  if (passwordChange.equals(passwordChangeConf)){
                      Call<ResponseBody> call = jsonPlaceHolderApi.putPost(passwordChange, email);
                      call.enqueue(new Callback<ResponseBody>() {
                          @Override
                          public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                              if (!response.isSuccessful()) {
                                  System.out.println(response);
                                  Toast.makeText(ChangePasswordActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();

                              } else {
                                  Toast.makeText(ChangePasswordActivity.this, "Password alterada com sucesso!", Toast.LENGTH_SHORT).show();
                                 //CLEAN STORAGE CACHE
                                  SessionManagement sessionManagement = new SessionManagement(ChangePasswordActivity.this);
                                  sessionManagement.removeTempMail();
                                  //GO TO LOGIN
                                  Intent intent = new Intent( ChangePasswordActivity.this, com.example.kidsland.LoginActivity.class);
                                  startActivity(intent);
                                  finish();
                              }
                          }

                          @Override
                          public void onFailure(Call<ResponseBody> call, Throwable t) {
                              System.out.println(t.getMessage());
                              Toast.makeText(ChangePasswordActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();

                          }
                      });

                  } else {
                      Toast.makeText(ChangePasswordActivity.this, "As Palavras-Passe não coincidem!", Toast.LENGTH_SHORT).show();

                  }
              }




            }
        });
    }
}