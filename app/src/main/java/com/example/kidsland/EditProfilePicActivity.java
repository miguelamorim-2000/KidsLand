package com.example.kidsland;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidsland.backend.JsonPlaceHolderApi2;
import com.example.kidsland.backend.SessionManagement;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfilePicActivity extends AppCompatActivity {

    String photo;
    String photoSelected;
    int id_child;
    private JsonPlaceHolderApi2 jsonPlaceHolderApi;
    String id_child1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_profile_pic);



        //PUT DEFINITIONS
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://188.82.156.135:8080/Back-end/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi2.class);



        //GET SELECTED ICON

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SessionManagement sessionManagement = new SessionManagement(this);
        photo = sessionManagement.getPhoto();
        id_child = sessionManagement.getID_CHILD();


        if (photo.equals("rapaz1.png")){
            findViewById(R.id.rapaz1round_png).setVisibility(View.VISIBLE);
            findViewById(R.id.rapaz2round_png).setVisibility(View.INVISIBLE);
            findViewById(R.id.rapaz3round_png).setVisibility(View.INVISIBLE);
            findViewById(R.id.rapariga1round_png).setVisibility(View.INVISIBLE);
            findViewById(R.id.rapariga2round_png).setVisibility(View.INVISIBLE);
            findViewById(R.id.rapariga3round_png).setVisibility(View.INVISIBLE);

        }else {
            if (photo.equals("rapaz2.png")) {
                findViewById(R.id.rapaz2round_png).setVisibility(View.VISIBLE);
                findViewById(R.id.rapaz1round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapaz3round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga1round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga2round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga3round_png).setVisibility(View.INVISIBLE);
            } else {
                if (photo.equals("rapaz3.png")) {
                    findViewById(R.id.rapaz2round_png).setVisibility(View.INVISIBLE);
                    findViewById(R.id.rapaz1round_png).setVisibility(View.INVISIBLE);
                    findViewById(R.id.rapaz3round_png).setVisibility(View.VISIBLE);
                    findViewById(R.id.rapariga1round_png).setVisibility(View.INVISIBLE);
                    findViewById(R.id.rapariga2round_png).setVisibility(View.INVISIBLE);
                    findViewById(R.id.rapariga3round_png).setVisibility(View.INVISIBLE);
                } else {
                    if (photo.equals("rapariga1.png")) {
                        findViewById(R.id.rapaz2round_png).setVisibility(View.INVISIBLE);
                        findViewById(R.id.rapaz1round_png).setVisibility(View.INVISIBLE);
                        findViewById(R.id.rapaz3round_png).setVisibility(View.INVISIBLE);
                        findViewById(R.id.rapariga1round_png).setVisibility(View.VISIBLE);
                        findViewById(R.id.rapariga2round_png).setVisibility(View.INVISIBLE);
                        findViewById(R.id.rapariga3round_png).setVisibility(View.INVISIBLE);
                    } else {
                        if (photo.equals("rapariga2.png")) {
                            findViewById(R.id.rapaz2round_png).setVisibility(View.INVISIBLE);
                            findViewById(R.id.rapaz1round_png).setVisibility(View.INVISIBLE);
                            findViewById(R.id.rapaz3round_png).setVisibility(View.INVISIBLE);
                            findViewById(R.id.rapariga1round_png).setVisibility(View.INVISIBLE);
                            findViewById(R.id.rapariga2round_png).setVisibility(View.VISIBLE);
                            findViewById(R.id.rapariga3round_png).setVisibility(View.INVISIBLE);
                        } else {
                            if (photo.equals("rapariga3.png")) {
                                findViewById(R.id.rapaz2round_png).setVisibility(View.INVISIBLE);
                                findViewById(R.id.rapaz1round_png).setVisibility(View.INVISIBLE);
                                findViewById(R.id.rapaz3round_png).setVisibility(View.INVISIBLE);
                                findViewById(R.id.rapariga1round_png).setVisibility(View.INVISIBLE);
                                findViewById(R.id.rapariga2round_png).setVisibility(View.INVISIBLE);
                                findViewById(R.id.rapariga3round_png).setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
            }
        }

//Button Back
        findViewById(R.id.buttonbackpic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( EditProfilePicActivity.this, com.example.kidsland.VisualizarDadosCriancaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Rapaz1
        findViewById(R.id.rapaz1_png).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoSelected = "rapaz1.png";
                findViewById(R.id.rapaz1round_png).setVisibility(View.VISIBLE);
                findViewById(R.id.rapaz2round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapaz3round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga1round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga2round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga3round_png).setVisibility(View.INVISIBLE);
            }
        });

        //Rapaz2
        findViewById(R.id.rapaz2_png).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoSelected = "rapaz2.png";
                findViewById(R.id.rapaz1round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapaz2round_png).setVisibility(View.VISIBLE);
                findViewById(R.id.rapaz3round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga1round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga2round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga3round_png).setVisibility(View.INVISIBLE);
            }
        });

        //Rapaz3
        findViewById(R.id.rapaz3_png).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoSelected = "rapaz3.png";
                findViewById(R.id.rapaz1round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapaz2round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapaz3round_png).setVisibility(View.VISIBLE);
                findViewById(R.id.rapariga1round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga2round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga3round_png).setVisibility(View.INVISIBLE);
            }
        });

        //Rapariga1
        findViewById(R.id.rapariga1_png).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoSelected = "rapariga1.png";
                findViewById(R.id.rapaz1round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapaz2round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapaz3round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga1round_png).setVisibility(View.VISIBLE);
                findViewById(R.id.rapariga2round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga3round_png).setVisibility(View.INVISIBLE);
            }
        });

        //Rapariga2
        findViewById(R.id.rapariga2_png).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoSelected = "rapariga2.png";
                findViewById(R.id.rapaz1round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapaz2round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapaz3round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga1round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga2round_png).setVisibility(View.VISIBLE);
                findViewById(R.id.rapariga3round_png).setVisibility(View.INVISIBLE);
            }
        });

        //Rapariga3
        findViewById(R.id.rapariga3_png).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoSelected = "rapariga3.png";
                findViewById(R.id.rapaz1round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapaz2round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapaz3round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga1round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga2round_png).setVisibility(View.INVISIBLE);
                findViewById(R.id.rapariga3round_png).setVisibility(View.VISIBLE);
            }
        });


        //SAVE PIC
        findViewById(R.id.buttonApply2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(id_child + photoSelected);
                Call<ResponseBody> call = jsonPlaceHolderApi.putPostPic(photoSelected, id_child);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()) {
                            System.out.println(response);
                            Toast.makeText(EditProfilePicActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(EditProfilePicActivity.this, "Fotografia alterada com sucesso!", Toast.LENGTH_SHORT).show();
                            sessionManagement.updatePic(photoSelected);
                            Intent intent = new Intent( EditProfilePicActivity.this, com.example.kidsland.VisualizarDadosCriancaActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println(t.getMessage());
                        Toast.makeText(EditProfilePicActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }
}