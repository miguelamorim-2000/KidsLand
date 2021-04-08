package com.example.kidsland;

import android.content.Context;
import android.graphics.Color;
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
import com.example.kidsland.backend.ListItem;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
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

public class EvaluationActivityAdapter extends RecyclerView.Adapter<EvaluationActivityAdapter.ViewHolder> {

    ArrayList<ListItem> items;
    RequestOptions option;
    AsyncHttpClient client;
    AsyncHttpClient client1;
    RequestParams params;
    String URL ="http://188.82.156.135:8080/Back-end/evaluation_requestPost";
    int id_childPost;
    Context context;
    int id_request;
    String status_evaluation = "";
    float points = 0;





    public EvaluationActivityAdapter(ArrayList<ListItem> listItems, int id_child, Context context){
        this.items = listItems;
        this.id_childPost = id_child;
        this.context = context;



    }
    @Override
    public EvaluationActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowevaluation1, parent, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return new EvaluationActivityAdapter.ViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull EvaluationActivityAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTittle());
        holder.itemView.findViewById(R.id.containerActivity145).setBackgroundColor(Color.parseColor("#F8FBFF"));
        holder.itemView.findViewById(R.id.textTitleEvaluation).setPadding(0,40,20,40);
        holder.timeActivity2.setText(items.get(position).getTime());
        holder.textLocation34.setText(items.get(position).getLocation());

        id_request = items.get(position).getId_item();
        System.out.println("id"+id_request);






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

        holder.button12312.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("-------------------------");
                System.out.println(id_request);
                System.out.println(points);
                System.out.println(status_evaluation);
                System.out.println(id_childPost);
                System.out.println(id_request);
                System.out.println("-------------------------");

            }
        });
/*
        //EVALUATING A ACTIVITY REQUEST
        rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                if ( status_evaluation.equals("200")) {

                  /*  @PUT("posts/{id}")
                            CALL <Post> putPost(@Path("id") int id, @Body Post post)*/
  /*                  //FETCH UPDATE
                    params = new RequestParams();
                    params.put("id_child", id_childPost);
                    params.put("id_request", id_request);
                    params.put("points", rating);


                    client = new AsyncHttpClient();
                    client.put("http://188.82.156.135:8080/Back-end/EvaluationRequestPut", params, new JsonHttpResponseHandler() {

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
                                System.out.println("Reavaliado com sucesso");


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




                } else {
                    //FETCH POST
                    params = new RequestParams();
                    params.put("id_child", id_childPost);
                    params.put("id_request", id_request);
                    params.put("points", rating);

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

*/



        //SHOW PHOTO
        String photo = items.get(position).getPhoto();
        System.out.println(photo);
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
        public TextView title, textLocation34, timeActivity2;
        public ImageView activityLogo;
        public RecyclerView recyclerView;
        public Button button12312;
        RatingBar rating_bar;



        public ViewHolder (View itemView){
            super (itemView);
            title = itemView.findViewById(R.id.textTitleEvaluation);
            activityLogo = (ImageView) itemView.findViewById(R.id.activityLogo2);
            recyclerView = itemView.findViewById(R.id.recycler_view43);
            rating_bar = itemView.findViewById(R.id.rating_bar);
            textLocation34 = itemView.findViewById(R.id.textLocation3467);
            timeActivity2 = itemView.findViewById(R.id.timeActivity2);
            button12312 = itemView.findViewById(R.id.participatebtn98);




        }

    }

}
