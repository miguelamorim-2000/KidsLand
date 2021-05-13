package com.example.kidsland.backend;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;

public interface JsonPlaceHolderApi4 {

@FormUrlEncoded
    @PUT("EvaluationRequestPut")
    Call<ResponseBody> putEvaluation (@Field("id_child") int id_child, @Field("id_request") int id_request, @Field("points") int points);


}
