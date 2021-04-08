package com.example.kidsland.backend;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;

public interface JsonPlaceHolderApi {

@FormUrlEncoded
    @PUT("PasswordPut")
    Call<ResponseBody> putPost (@Field("password") String password, @Field("email") String email);
}
