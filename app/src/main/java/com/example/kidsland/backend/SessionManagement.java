package com.example.kidsland.backend;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME ="session";
    String SESSION_KEY = "session_key";
    String EMAIL = "email";
    String ID_CHILD = "id_child";
    String TOTAL_POINTS = "total_points";
    String BIRTH_DATE = "birth_date";
    String NAME_CHILD ="name_child";



    public SessionManagement(Context context){
        sharedPreferences= context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(User user, Child child){
        int id_user = user.getId_user();
        String email = user.getEmail();
        int id_child = child.getId_child();
        int total_points = child.getTotal_points();
        String birth_date = child.getBirth_date();
        String name_child = child.getName();

        editor.putInt(SESSION_KEY, id_user).commit();
        editor.putString(EMAIL, email).commit();
        editor.putInt(ID_CHILD, id_child).commit();
        editor.putInt(TOTAL_POINTS, total_points).commit();
        editor.putString(BIRTH_DATE, birth_date).commit();
        editor.putString(NAME_CHILD, name_child).commit();



    }

    //RETURN USER ID SESSION SAVED
    public int getSession(){
        return sharedPreferences.getInt(SESSION_KEY, -1);

    }

    public String getEMAIL() {
        return sharedPreferences.getString(EMAIL, "");
    }

    public int getID_CHILD() {
        return sharedPreferences.getInt(ID_CHILD, -1);
    }

    public int getTOTAL_POINTS() {
        return sharedPreferences.getInt(TOTAL_POINTS, -1);
    }

    public String getBIRTH_DATE() {
        return sharedPreferences.getString(BIRTH_DATE, "");
    }

    public String getNAME_CHILD() {
        return sharedPreferences.getString(NAME_CHILD, "");
    }

    //LOGOUT AND REMOVE SESSION
    public void removeSession(){
        editor.putInt(SESSION_KEY, -1).commit();
        editor.putString(EMAIL, "").commit();
        editor.putInt(ID_CHILD, -1).commit();
        editor.putInt(TOTAL_POINTS, -1).commit();
        editor.putString(BIRTH_DATE, "").commit();
        editor.putString(NAME_CHILD, "").commit();


    }
}
