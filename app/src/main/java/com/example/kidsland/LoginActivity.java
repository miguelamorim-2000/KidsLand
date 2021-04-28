package com.example.kidsland;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.kidsland.backend.Child;
import com.example.kidsland.backend.SessionManagement;
import com.example.kidsland.backend.User;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Executor;

import cz.msebera.android.httpclient.Header;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class LoginActivity extends AppCompatActivity {
    private Button botaoLogin;
    private TextView createAccBtn, forgotPw;
    EditText email, password;
    Button submitbutton;
    String e, p;
    RequestParams params;
    AsyncHttpClient client;
    String URL ="http://188.82.156.135:8080/Back-end/Login";
    private String android_id;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        botaoLogin = (Button)findViewById(R.id.buttonApply);
        createAccBtn = (TextView) findViewById(R.id.createAccBtn);
        email = (EditText)findViewById(R.id.emailTxtLogin);
        password = (EditText) findViewById(R.id.passwordTxtLogin);
        forgotPw = (TextView) findViewById(R.id.forgotPassword);




        //SEE IF SESSION IS RUNNING ON START
       SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
       int userID = sessionManagement.getSession();

        //SET LAST LOGIN EMAIL IF LOGGED
        email.setText(sessionManagement.getEMAIL());

       if (userID != -1){

           //IF USER NOT LOGGED IN
           Intent intent = new Intent( LoginActivity.this, com.example.kidsland.MenuActivity.class);
           startActivity(intent);
           finish();
       } else {
           //DO NOTHING
       }


        android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.e("SIGA",android_id);
        executor = ContextCompat.getMainExecutor(this);
        System.out.println("-----------------------------------");

        System.out.println(android_id);
        System.out.println("-----------------------------------");


        biometricPrompt = new BiometricPrompt(LoginActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();


            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                //CHECK BIOMETRICS WITH EMAIL

                OkHttpClient client = new OkHttpClient();

                String url = "http://188.82.156.135:8080/Back-end/AndroidIdVerifyGet?email="+email.getText().toString()+"&Android_ID=" + android_id;

                Request request = new Request.Builder().url(url).build();

                client.newCall(request).enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull okhttp3.Call call, @NotNull okhttp3.Response response) throws IOException {
                        String msg = "";
                        if (response.isSuccessful()){
                            String body = response.body().string();


                            JSONObject root = null;
                            try {
                                root = new JSONObject(body);
                                msg = root.getString("STATUS");



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            String finalMsg = msg;
                            LoginActivity.this.runOnUiThread(() -> {




                                if(finalMsg.equals("200")){
                                    OkHttpClient client = new OkHttpClient();

                                    String url = "http://188.82.156.135:8080/Back-end/BiometricGet?email="+email.getText().toString();

                                    Request request = new Request.Builder().url(url).build();

                                    client.newCall(request).enqueue(new okhttp3.Callback() {
                                        @Override
                                        public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                                            e.printStackTrace();
                                        }

                                        @Override
                                        public void onResponse(@NotNull okhttp3.Call call, @NotNull okhttp3.Response response) throws IOException {
                                            String msg = "";
                                            //INICIALIZE VARIABLES
                                            String status = "", email_user = "", birth_date = "", name_child = "", photo = "";
                                            int id_user = 0, id_child = 0, total_points = 0;

                                            if (response.isSuccessful()){
                                                String body = response.body().string();

                                                JSONObject root1 = null;
                                                try {
                                                    root1 = new JSONObject(body);
                                                } catch (JSONException jsonException) {
                                                    jsonException.printStackTrace();
                                                }

                                                System.out.println(root1);
                                                try {
                                                    System.out.println(root1.getString("email"));
                                                } catch (JSONException jsonException) {
                                                    jsonException.printStackTrace();
                                                }


                                                //STORE USER DATA
                                                try {
                                                    email_user = root1.getString("email");
                                                    id_user = root1.getInt("id_user");
                                                    id_child = root1.getInt("id_child");
                                                    total_points = root1.getInt("total_points");
                                                    birth_date = root1.getString("birth_date");
                                                    name_child = root1.getString("name");
                                                    photo = root1.getString("photo");


                                                } catch (JSONException jsonException) {
                                                    jsonException.printStackTrace();
                                                }

                                                String finalEmail_user = email_user;
                                                String finalName_child = name_child;
                                                int finalTotal_points = total_points;
                                                String finalBirth_date = birth_date;
                                                int finalId_child = id_child;
                                                String finalPhoto = photo;
                                                int finalId_user = id_user;
                                                LoginActivity.this.runOnUiThread(() -> {

                                                    //CREATE SESSION
                                                    User user = new User(finalEmail_user, finalId_user);
                                                    Child child = new Child(finalName_child, finalTotal_points, finalBirth_date, finalId_child, finalPhoto);
                                                    SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                                                    sessionManagement.saveSession(user, child);
                                                    Toast.makeText(LoginActivity.this, "Login com sucesso!", Toast.LENGTH_SHORT).show();


                                                    //MOVE TO MENU PAGE
                                                    Intent intent = new Intent(LoginActivity.this, com.example.kidsland.MenuActivity.class);
                                                    startActivity(intent);
                                                    finish();

                                                });









                                            }
                                        }
                                    });


                                    Toast.makeText(LoginActivity.this, "Login com sucesso!", Toast.LENGTH_SHORT).show();


                                } else {
                                    Toast.makeText(LoginActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();

                                }

                            });





                            Gson gson = new Gson();


                        }
                    }
                });


            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login com Impressão Digital")
                .setSubtitle("Coloque o dedo no sensor para iniciar sessão.")
                .setNegativeButtonText("Cancelar")
                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
                .build();


        findViewById(R.id.imageView15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(android_id==android_id) {
                    biometricPrompt.authenticate(promptInfo);
                }else{
                    Log.e("TESTE","PARABENS , ES UM GENIO HEHEHE");


                }

            }
        });




        // ATTEMPT LOGIN
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    e = email.getText().toString();
                    p = password.getText().toString();
                    params = new RequestParams();
                    params.put("email", e);
                    params.put("password", p);
                    client = new AsyncHttpClient();
                    System.out.println("ola");
                    client.post(URL, params, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            System.out.println(response);

                            //INICIALIZE VARIABLES
                            String status = "", email_user = "", birth_date = "", name_child = "", photo = "";
                            int id_user = 0, id_child = 0, total_points = 0;

                            //GET STATUS OF OPERATION
                            try {
                                status = response.getString("STATUS");
                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();
                            }
                            if (status.equals("200")) {

                                //STORE USER DATA
                                try {
                                    email_user = response.getString("email");
                                    id_user = response.getInt("id_user");
                                    id_child = response.getInt("id_child");
                                    total_points = response.getInt("total_points");
                                    birth_date = response.getString("birth_date");
                                    name_child = response.getString("name");
                                    photo = response.getString("photo");


                                } catch (JSONException jsonException) {
                                    jsonException.printStackTrace();
                                }

                                //CREATE SESSION
                                User user = new User(email_user, id_user);
                                Child child = new Child(name_child, total_points, birth_date, id_child, photo);
                                SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                                sessionManagement.saveSession(user, child);
                                Toast.makeText(LoginActivity.this, "Login com sucesso!", Toast.LENGTH_SHORT).show();


                                //MOVE TO MENU PAGE
                                Intent intent = new Intent(LoginActivity.this, com.example.kidsland.MenuActivity.class);
                                startActivity(intent);
                                finish();
                            }


                            if (status.equals("400")) {
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


       //Create account button
        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivity.this, com.example.kidsland.RegisterActivity.class);
                startActivity(intent);
                finish();

            }

        });

        //Forgot Password Button
        forgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivity.this, com.example.kidsland.RequestPasswordActivity.class);
                startActivity(intent);

            }

        });

    }
}