package com.example.kidsland;

import android.content.Context;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.kidsland.backend.JsonPlaceHolderApi4;
import com.example.kidsland.backend.ListItem;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AvaliacaoAtividadeAdapter extends RecyclerView.Adapter<AvaliacaoAtividadeAdapter.ViewHolder> {

    ArrayList<ListItem> items;
    RequestOptions option;
    AsyncHttpClient client;
    AsyncHttpClient client1;
    AsyncHttpClient client2;
    String URL ="http://188.82.156.135:8080/Back-end/SubscriptionPost";
    String URL2 ="http://188.82.156.135:8080/Back-end/SubscriptionDelete";
    int id_childPost;
    int id_request;
    RequestParams params;
    RequestParams params1;
    String status_evaluation = "";
    Context context;
    float points = 0;
    private JsonPlaceHolderApi4 jsonPlaceHolderApi;





    public AvaliacaoAtividadeAdapter(ArrayList<ListItem> listItems, int id_child, Context context){
        this.items = listItems;
        this.id_childPost = id_child;
        this.context = context;



    }
    @Override
    public AvaliacaoAtividadeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowevaluation1, parent, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return new AvaliacaoAtividadeAdapter.ViewHolder(view);






    }

    @Override
    public void onBindViewHolder(@NonNull AvaliacaoAtividadeAdapter.ViewHolder holder, int position) {
    holder.textTitle434345.setText(items.get(position).getTittle());
        holder.textLocation3464547.setText(items.get(position).getLocation());
        holder.timeActivity656576.setText(items.get(position).getTime());
        id_request = items.get(position).getId_item();

        //PUT DEFINITIONS
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://188.82.156.135:8080/Back-end/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi4.class);

        //CHECK IF REQUEST WAS ALREADY EVALUATED
        //HTTP GET
        OkHttpClient client1 = new OkHttpClient();
        System.out.println(id_childPost);
        System.out.println(id_request);


        String url = "http://188.82.156.135:8080/Back-end/EvaluationRequestGetId?id_child="+id_childPost+"&id_request="+id_request;

        Request request = new Request.Builder().url(url).build();

        client1.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String body = response.body().string();

                    try {

                        JSONObject root = new JSONObject(body);
                        System.out.println(root);
                        status_evaluation = root.getString("STATUS");
                        if (status_evaluation.equals("200")) {

                            JSONArray msg = root.getJSONArray("MSG");


                            for (int i = 0; i < msg.length(); i++) {
                                JSONObject jsonItem = msg.getJSONObject(i);


                                // GET DATE AND FORMAT
                                points = jsonItem.getInt("points");
                                System.out.println(points);


                            }
                        } else {
                            points = 0;
                        }

                        //SET EVALUATION IF ALREADY DONE
                        ((AppCompatActivity) context).runOnUiThread(() -> {

                            if (status_evaluation.equals("200")){
                                System.out.println(points);
                                holder.rating_bar.setRating(points);
                            } else {holder.rating_bar.setRating(points);}







                        });



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Gson gson = new Gson();


                }
            }
        });



        holder.rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                points = holder.rating_bar.getRating();

                if ( status_evaluation.equals("200")) {
                    System.out.println("Teste");
                    System.out.println(id_childPost);
                    System.out.println(id_request);
                    System.out.println(points);



                    retrofit2.Call<ResponseBody> call = jsonPlaceHolderApi.putEvaluation(id_childPost, id_request, (int) points);
                    call.enqueue(new retrofit2.Callback<ResponseBody>() {
                        @Override
                        public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                            if (!response.isSuccessful()) {
                                System.out.println(response);

                            } else {
                                System.out.println("Sucesso Bitch");

                            }
                        }

                        @Override
                        public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                            System.out.println(t.getMessage());

                        }
                    });



                } else {
                    //FETCH POST
                    params = new RequestParams();
                    params.put("id_child", id_childPost);
                    params.put("id_request", id_request);
                    params.put("points", (int) rating);
                    status_evaluation="200";

                    client = new AsyncHttpClient();
                    client.post(URL, params, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
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
                            if (status.equals("200")) {
                                System.out.println("Avaliado com sucesso");


                            }

                            if (status.equals("400")) {


                            }

                        }

                        @Override
                        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                            System.out.println(errorResponse);
                        }

                    });

                }





            }
        });



        //SHOW PHOTO
        String photo = "http://188.82.156.135:8080/Back-end/IMAGES/" + items.get(position).getPhoto();
        Picasso.get().load(photo)
                .placeholder(R.drawable.loadingicon)
                .error(R.drawable.loadingicon)
                .into((ImageView) holder.itemView.findViewById(R.id.activityLogo2));


    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle434345,textLocation3464547, timeActivity656576;
        ImageView activityLogo865659;
        RatingBar rating_bar;
        Button participatebtn275656;




        public ViewHolder (View itemView){
            super (itemView);
            textTitle434345 = itemView.findViewById(R.id.textTitleEvaluation);
            textLocation3464547= itemView.findViewById(R.id.textLocation3467);
            activityLogo865659 = itemView.findViewById(R.id.activityLogo2);
            timeActivity656576 = itemView.findViewById(R.id.timeActivity2);
            rating_bar = itemView.findViewById(R.id.rating_bar);




        }

    }



}
