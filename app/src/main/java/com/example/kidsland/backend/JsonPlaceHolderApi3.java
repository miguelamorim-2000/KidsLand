package com.example.kidsland.backend;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;

public interface JsonPlaceHolderApi3 {

@FormUrlEncoded
    @PUT("AndroidIdPut")
    Call<ResponseBody> putTouchId (@Field("email") String email, @Field("Android_ID") String android_id);


}
