package com.example.kidsland.backend;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME ="session";
    String SESSION_KEY = "session_key";

    public SessionManagement(Context context){
        sharedPreferences= context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(User user){
        int id = user.getId_user();

        editor.putInt(SESSION_KEY, id).commit();



    }

    //RETURN USER ID SESSION SAVED
    public int getSession(){
        return sharedPreferences.getInt(SESSION_KEY, -1);

    }

    //LOGOUT AND REMOVE SESSION
    public void removeSession(){
        editor.putInt(SESSION_KEY, -1).commit();

    }
}
