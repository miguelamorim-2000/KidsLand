package com.example.kidsland;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.kidsland.backend.JsonPlaceHolderApi3;
import com.example.kidsland.backend.SessionManagement;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class DefinicoesActivity extends AppCompatActivity {

    private TextView camaraAccess;
    private TextView storageBtn;
    private TextView locationBtn;
    private Button logoutBtn,backBtnDefinitions, faqBtn;
    private String manifestCam;
    private JsonPlaceHolderApi3 jsonPlaceHolderApi;
    private Switch switch1;
    private String[] manifestStorage ={Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private String[] manifestLocation = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    private int STORAGE_PERMISSION_GRANTED = 1;
    private String android_id;

    private static final String TAG ="SearchActivity";
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definicoes);
       camaraAccess= findViewById(R.id.camaraAccess);
       locationBtn = findViewById(R.id.locationBtn);
       storageBtn = findViewById(R.id.storageBtn);
       faqBtn = findViewById(R.id.faqButton5);
       manifestCam = Manifest.permission.CAMERA;
        logoutBtn = findViewById(R.id.logoutBtn);
        backBtnDefinitions= findViewById(R.id.backBtnDefinitions);
        switch1 = findViewById(R.id.switch1);


        //GET PHONE DATA

        android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        //GET USER DATA
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SessionManagement sessionManagement = new SessionManagement(this);
        int id_user = sessionManagement.getSession();
        String email = sessionManagement.getEMAIL();

        //CHECK IF TOUCH ID IS ACTIVATED WITH DATABASE

        OkHttpClient client = new OkHttpClient();

        String url = "http://188.82.156.135:8080/Back-end/AndroidIdVerifyGet?email="+email+"&Android_ID=" + android_id;

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
                    DefinicoesActivity.this.runOnUiThread(() -> {


    if(finalMsg.equals("200")){
        switch1.setChecked(true);
    } else {
        switch1.setChecked(false);

    }

                    });





                    Gson gson = new Gson();


                }
            }
        });



        //PUT DEFINITIONS
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://188.82.156.135:8080/Back-end/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi3.class);


        //Activate Touch Id
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("---------------------------------");
                System.out.println(android_id);
                System.out.println(email);
                System.out.println("---------------------------------");
            if (switch1.isChecked()){
                Call<ResponseBody> call = jsonPlaceHolderApi.putTouchId(email, android_id);
                System.out.println(email);
                System.out.println(android_id);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()) {
                            System.out.println(response);
                            Toast.makeText(DefinicoesActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();

                        } else {


                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println(t.getMessage());
                        Toast.makeText(DefinicoesActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();

                    }
                });

            } else {
                Call<ResponseBody> call = jsonPlaceHolderApi.putTouchId(email, null);
                System.out.println(email);
                System.out.println(android_id);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()) {
                            System.out.println(response);
                            Toast.makeText(DefinicoesActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(DefinicoesActivity.this, "TouchID Desativado!", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println(t.getMessage());
                        Toast.makeText(DefinicoesActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
            }
        });



       //Test Permissions
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                manifestCam) != PERMISSION_GRANTED){
            findViewById(R.id.imageView20).setVisibility(View.INVISIBLE);
        }

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                manifestStorage[0]) != PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this.getApplicationContext(),
                manifestStorage[1]) != PERMISSION_GRANTED){
            findViewById(R.id.imageView13).setVisibility(View.INVISIBLE);

        }

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                manifestLocation[0]) != PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this.getApplicationContext(),
                manifestLocation[1]) != PERMISSION_GRANTED){
            findViewById(R.id.imageView11).setVisibility(View.INVISIBLE);

        }


       //Click Camara Button
        camaraAccess.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                verifyPermissionCamara();

            }
        });

        //Click Location Button
        storageBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                verifyPermissionStorage();

            }


        });
        //Click Memory Button
        locationBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                verifyPermissionLocation();

            }
        });

        //CLICK TO LOGOUT
        logoutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SessionManagement sessionManagement = new SessionManagement(DefinicoesActivity.this);
                sessionManagement.removeSession();
                //MOVE TO LOGIN PAGE
                Intent intent = new Intent( DefinicoesActivity.this, com.example.kidsland.LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

        //BACK TO PREVIOUS PAGE
        backBtnDefinitions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });

        faqBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DefinicoesActivity.this, com.example.kidsland.FAQActivity.class);
                startActivity(intent);

            }
        });

    }


    //Verify or Ask for access to the camera
    private void verifyPermissionCamara (){
        Log.d(TAG, "verifyPermissions: asking user for permissions kkkkkkkkkkkkk");
        String[] permissions = {Manifest.permission.CAMERA};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[0]) == PERMISSION_GRANTED) {
            System.out.println("permitido");
        }else {
            ActivityCompat.requestPermissions(DefinicoesActivity.this,
                    permissions,
                    REQUEST_CODE);
        }

    }

    //Verify or Ask for access to the storage
    private void verifyPermissionStorage (){
        Log.d(TAG, "verifyPermissions: asking user for permissions!!!!!!!");
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[0]) == PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[1]) == PERMISSION_GRANTED) {
        }else {
            ActivityCompat.requestPermissions(DefinicoesActivity.this,
                    permissions,
                    REQUEST_CODE);

        }
    }

    //Verify or Ask for access to the Location
    private void verifyPermissionLocation (){
        Log.d(TAG, "verifyPermissions: asking user for permissions Location!!!!!!!");

     String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[0]) == PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[1]) == PERMISSION_GRANTED) {
        }else {
            ActivityCompat.requestPermissions(DefinicoesActivity.this,
                    permissions,
                    REQUEST_CODE);

        }

    }









/*
    private void requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)){

                new AlertDialog.Builder(this)
                        .setTitle("Permission needed")
                        .setMessage("This permission is needed)")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(DefinicoesActivity.this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},STORAGE_PERMISSION_GRANTED);

                            }
                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION},STORAGE_PERMISSION_GRANTED);
        }
    }
*/


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

            }



}
