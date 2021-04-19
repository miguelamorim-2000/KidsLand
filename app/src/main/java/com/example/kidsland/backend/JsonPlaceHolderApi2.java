package com.example.kidsland.backend;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;

public interface JsonPlaceHolderApi2 {

@FormUrlEncoded
    @PUT("ChildUploadPut")
    Call<ResponseBody> putPostPic (@Field("photo") String photo, @Field("id_child") int id_child);


}
