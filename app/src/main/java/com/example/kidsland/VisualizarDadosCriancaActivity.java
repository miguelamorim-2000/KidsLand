package com.example.kidsland;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidsland.backend.JsonPlaceHolderApi;
import com.example.kidsland.backend.SessionManagement;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VisualizarDadosCriancaActivity extends AppCompatActivity {
    String name_child;
    int id_user;
    int id_child;
    String birth_date ;
    String email;
    EditText oldpass, newpass, newpassconf;
    private JsonPlaceHolderApi jsonPlaceHolderApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_dados_crianca);

        //GET FIELDS
        oldpass = findViewById(R.id.oldpass);
        newpass = findViewById(R.id.passwordconf);
        newpassconf = findViewById(R.id.passwordconf2);



        //GET USER DATA
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SessionManagement sessionManagement = new SessionManagement(this);
        int id_user = sessionManagement.getSession();
        name_child = sessionManagement.getNAME_CHILD();
        birth_date = sessionManagement.getBIRTH_DATE();
        id_child = sessionManagement.getID_CHILD();
        email = sessionManagement.getEMAIL();


        //SET FIELDS WITH DATA
        EditText name = (EditText) findViewById(R.id.editTextTextFaixa2);
        name.setText(name_child);
        name.setKeyListener(null);
        name.setCursorVisible(false);
        name.setPressed(false);
        name.setFocusable(false);


        EditText birth = (EditText)findViewById(R.id.birthdate);
        birth.setText(birth_date);
        birth.setKeyListener(null);
        birth.setCursorVisible(false);
        birth.setPressed(false);
        birth.setFocusable(false);


//SAVE DATA
        findViewById(R.id.buttonApply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(oldpass.getText().toString().length() != 0){
                    if (newpass.getText().toString().length() != 0 && newpassconf.getText().toString().length() != 0) {
                        if (newpass.getText().toString().equals(newpassconf.getText().toString())) {
                            updatePost();
                        }
                        else {
                            Toast.makeText(VisualizarDadosCriancaActivity.this, "A Nova Password e Confirmação não coincidem.", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(VisualizarDadosCriancaActivity.this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        //EXIT

        findViewById(R.id.exitprofile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://188.82.156.135:8080/Back-end/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


    }

    private void updatePost(){
        EditText pass=  findViewById(R.id.oldpass);
        String password = pass.getText().toString();


        Call<ResponseBody> call = jsonPlaceHolderApi.putPost(password, email);
call.enqueue(new Callback<ResponseBody>() {
    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (!response.isSuccessful()) {
            System.out.println(response);
            Toast.makeText(VisualizarDadosCriancaActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(VisualizarDadosCriancaActivity.this, "Password alterada com sucesso!", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        System.out.println(t.getMessage());
        Toast.makeText(VisualizarDadosCriancaActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();

    }
});
    }
}