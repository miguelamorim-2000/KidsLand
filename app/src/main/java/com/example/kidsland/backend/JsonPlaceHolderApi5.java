package com.example.kidsland.backend;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;

public interface JsonPlaceHolderApi5 {

@FormUrlEncoded
    @PUT("EvaluationActivityPut")
    Call<ResponseBody> putEvaluationActivity (@Field("points") int points, @Field("id_child") int id_child, @Field("id_activity") int id_activity);


}
