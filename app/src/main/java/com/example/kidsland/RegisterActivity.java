package com.example.kidsland;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;


public class RegisterActivity extends AppCompatActivity {

        EditText email, password, telephone, birthdate, passwordConfirm,nameTxtRegister;
        Button submitbutton;
        TextView loginRedirect;
        String e, p, t,b, n ;
        RequestParams params;
        AsyncHttpClient client;
        String URL ="http://188.82.156.135:8080/Back-end/Register";
        private DatePickerDialog.OnDateSetListener mDateSetListener;
        private static final String TAG ="RegisterActivity";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            getSupportActionBar().hide();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            email = (EditText) findViewById(R.id.emailTxt);
            password = (EditText) findViewById(R.id.passwordTxt);
            passwordConfirm = (EditText) findViewById(R.id.passwordConfirm);
            telephone = (EditText) findViewById(R.id.telephoneTxt);
            submitbutton = (Button) findViewById(R.id.submitbutton);
            loginRedirect = (TextView) findViewById(R.id.loginRedirect);
            birthdate = (EditText) findViewById(R.id.birthDateTxt);
            nameTxtRegister = (EditText) findViewById(R.id.nameTxtRegister);

            birthdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(
                            RegisterActivity.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            mDateSetListener,
                            year, month, day);

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();


                }
            });
            mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    b= year + "-" + month + "-" + dayOfMonth;
                    Log.d(TAG, "onDateSet: date: " + year + "-" + month + "-" + dayOfMonth);
                    birthdate.setText(b);
                }
            };



            submitbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    e = email.getText().toString();
                    p = password.getText().toString();
                    t = telephone.getText().toString();
                    n = nameTxtRegister.getText().toString();
                    params= new RequestParams();
                    params.put("name", n);
                    params.put("email", e);
                    params.put("password", p);
                    params.put("phone_number_tutor", t);
                    params.put("birth_date", b);
                    client = new AsyncHttpClient();

                    if (password.getText().toString().length() == 0 || passwordConfirm.getText().toString().length() == 0 || email.getText().toString().length() == 0 || birthdate.getText().toString().length() == 0 ||
                            telephone.getText().toString().length() == 0){
                        findViewById(R.id.errorTxt3).setVisibility(View.INVISIBLE);
                        findViewById(R.id.errorTxt2).setVisibility(View.INVISIBLE);
                        findViewById(R.id.errorTxt4).setVisibility(View.VISIBLE);
                        findViewById(R.id.imageView6).setVisibility(View.VISIBLE);



                    }else {

                        if (password.getText().toString().equals(passwordConfirm.getText().toString()) != true) {
                            findViewById(R.id.errorTxt3).setVisibility(View.INVISIBLE);
                            findViewById(R.id.errorTxt4).setVisibility(View.INVISIBLE);
                            findViewById(R.id.errorTxt2).setVisibility(View.VISIBLE);
                            findViewById(R.id.imageView6).setVisibility(View.VISIBLE);

                        } else {
                            if (isValid(email.getText().toString()) != true) {
                                findViewById(R.id.errorTxt2).setVisibility(View.INVISIBLE);
                                findViewById(R.id.errorTxt4).setVisibility(View.INVISIBLE);
                                findViewById(R.id.errorTxt3).setVisibility(View.VISIBLE);
                                findViewById(R.id.imageView6).setVisibility(View.VISIBLE);


                            } else {
                                client.post(URL, params, new JsonHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                        super.onSuccess(statusCode, headers, response);
                                        System.out.println(response.toString());

                                        String status = "";

                                        //GET STATUS OF OPERATION
                                        try {
                                            status = response.getString("STATUS");
                                        } catch (JSONException jsonException) {
                                            jsonException.printStackTrace();
                                        }
                                        if (status.equals("200")) {
                                            Toast.makeText(RegisterActivity.this, "Registado com sucesso!" , Toast.LENGTH_SHORT).show();
                                            email.setText("");
                                            password.setText("");
                                            passwordConfirm.setText("");
                                            telephone.setText("");
                                            nameTxtRegister.setText("");

                                        }

                                        if (status.equals("400")){
                                            Toast.makeText(RegisterActivity.this, "Erro no Registo.", Toast.LENGTH_SHORT).show();

                                        }

                                        if (status.equals("401")){
                                            Toast.makeText(RegisterActivity.this, "Utilizador já registado!", Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                        super.onFailure(statusCode, headers, responseString, throwable);
                                        Toast.makeText(RegisterActivity.this, "Falha no registo!", Toast.LENGTH_SHORT).show();
                                        System.out.println(responseString);
                                    }

                                });

                            }

                        }
                    }


                }
            });

            loginRedirect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent( RegisterActivity.this, com.example.kidsland.LoginActivity.class);
                    startActivity(intent);
                    finish();

                }

            });

        }

    public static boolean isValid (String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }



    }