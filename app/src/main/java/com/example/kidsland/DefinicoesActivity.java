package com.example.kidsland;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.kidsland.backend.SessionManagement;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class DefinicoesActivity extends AppCompatActivity {

    private TextView camaraAccess;
    private TextView storageBtn;
    private TextView locationBtn;
    private Button logoutBtn,backBtnDefinitions;
    private String manifestCam;
    private String[] manifestStorage ={Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private String[] manifestLocation = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    private int STORAGE_PERMISSION_GRANTED = 1;

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
       manifestCam = Manifest.permission.CAMERA;
        logoutBtn = findViewById(R.id.logoutBtn);
        backBtnDefinitions= findViewById(R.id.backBtnDefinitions);






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
