package com.example.kidsland;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kidsland.backend.SessionManagement;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class SugestionActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000 ;
    Button backBtnSugestions, settingBtnSugestions, submitSugestion;
    EditText subjectTxt, descriptionSugestion;
    String t, d;
    int id_child, id_user;
    String name_child;
    RequestParams params;
    AsyncHttpClient client;
    String URL ="http://188.82.156.135:8080/Back-end/SugestionPost";
    ImageView micIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestion);

        //GET USER ID
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SessionManagement sessionManagement = new SessionManagement(this);
        id_user = sessionManagement.getSession();
        id_child = sessionManagement.getID_CHILD();
        name_child = sessionManagement.getNAME_CHILD();

        backBtnSugestions = findViewById(R.id.backBtnSugestions);
        settingBtnSugestions =findViewById(R.id.settingBtnSugestions);
        submitSugestion = findViewById(R.id.sendSugestionBtn);
        subjectTxt = findViewById(R.id.subjectTxt);
        descriptionSugestion = findViewById(R.id.descriptionSugestion);
        micIcon = findViewById(R.id.micIcon);


        //MOVE TO LOGIN PAGE

        backBtnSugestions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });

        settingBtnSugestions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent( SugestionActivity.this, com.example.kidsland.DefinicoesActivity.class);
                startActivity(intent);

            }
        });

        // ATTEMPT SEND SUGESTION
        submitSugestion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                //POST
                t = subjectTxt.getText().toString();
                d = descriptionSugestion.getText().toString();
                params= new RequestParams();
                params.put("title", t);
                params.put("designation", d);
                params.put("id_child", id_child);
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
                            Toast.makeText(SugestionActivity.this, "Sugestão enviada com sucesso!", Toast.LENGTH_SHORT).show();


}


                        if (status.equals("400")){
                            Toast.makeText(SugestionActivity.this, "Erro no envio da sugestão. Tente Novamente.", Toast.LENGTH_SHORT).show();



                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(SugestionActivity.this, "Falha de conexão.", Toast.LENGTH_SHORT).show();
                        System.out.println(errorResponse);
                    }

                });


            }
        });

        micIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });


    }

    private void speak() {
        Intent intent = new Intent (RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Podes falar!");

        try{
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);

        } catch (Exception e){
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case REQUEST_CODE_SPEECH_INPUT:{
                if (resultCode == RESULT_OK && null !=data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    descriptionSugestion.setText(result.get(0));
                }
                break;
            }
        }
    }
}