package com.example.kidsland;

import android.graphics.Color;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.kidsland.backend.HistoryItem;
import com.example.kidsland.backend.JsonPlaceHolderApi5;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolder> {

    ArrayList<HistoryItem> items;
    RequestOptions option;
    AsyncHttpClient client;
    AsyncHttpClient client1;
    int id_child;
    float points = 0;
    int id_activity;
    RequestParams params;
    private JsonPlaceHolderApi5 jsonPlaceHolderApi;
    String URL ="http://188.82.156.135:8080/Back-end/EvaluationActivityPost";





    public HistoryListAdapter(ArrayList<HistoryItem> historyItems, int id_child){
        this.items = historyItems;
        this.id_child = id_child;

    }
    @Override
    public HistoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowhistoryy, parent, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String[] status_evaluation = {""};
        points = 0;
        //FORMAT DATA FROM DB
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat1.setLenient(false);
        dateFormat2.setLenient(false);
        Date d = null;
        String invalidDate =  items.get(position).getDate();
        try {
            d = dateFormat1.parse(invalidDate);
        } catch (Exception e) {
            System.out.println("reversed date " + invalidDate);
        }

        String newDate = dateFormat2.format(d);

        holder.title.setText(items.get(position).getTittle());
        holder.description.setText("Realizada no dia : " + newDate);
        holder.pointsTxt.setText(String.valueOf(items.get(position).getPoints()));
        holder.itemView.findViewById(R.id.containerActivity43).setBackgroundColor(Color.parseColor("#F8FBFF"));
        holder.itemView.findViewById(R.id.textTitle4345).setPadding(0,40,20,40);
        holder.itemView.findViewById(R.id.textLocation3467).setPadding(0,30,20,40);
        id_activity = items.get(position).getId_item();


        String photo = "http://188.82.156.135:8080/Back-end/IMAGES/" + items.get(position).getPhoto();
        Picasso.get().load(photo)
                .placeholder(R.drawable.loadingicon)
                .error(R.drawable.loadingicon)
                .into((ImageView) holder.itemView.findViewById(R.id.activityLogo89));

        //PUT DEFINITIONS
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://188.82.156.135:8080/Back-end/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi5.class);

        //CHECK IF REQUEST WAS ALREADY EVALUATED
        //HTTP GET
        OkHttpClient client1 = new OkHttpClient();



        String url = "http://188.82.156.135:8080/Back-end/EvaluationActivityGetId?id_child="+id_child+"&id_activity="+items.get(position).getId_item();

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
                        status_evaluation[0] = root.getString("STATUS");
                        System.out.println("#######################");
                        System.out.println( status_evaluation[0]);
                        System.out.println("#######################");
                        if (status_evaluation[0].equals("200")) {

                            JSONArray msg = root.getJSONArray("MSG");


                            for (int i = 0; i < msg.length(); i++) {
                                JSONObject jsonItem = msg.getJSONObject(i);


                                // GET DATE AND FORMAT
                                holder.rating_bar.setRating(jsonItem.getInt("points"));



                            }
                        } else {
                            points = 0;
                        }





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


                if ( status_evaluation[0].equals("200")) {
                    System.out.println("Teste");
                    System.out.println(id_child);
                    System.out.println(items.get(position).getId_item());
                    System.out.println(points);



                    retrofit2.Call<ResponseBody> call = jsonPlaceHolderApi.putEvaluationActivity((int) points, id_child, items.get(position).getId_item());
                    call.enqueue(new retrofit2.Callback<ResponseBody>() {
                        @Override
                        public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                            if (!response.isSuccessful()) {
                                System.out.println(response);
                                System.out.println("Problemas Bitch");
                                System.out.println(items.get(position).getId_item());


                            } else {
                                System.out.println("Sucesso Bitch");
                                System.out.println(items.get(position).getId_item());
                                System.out.println(response);


                            }
                        }

                        @Override
                        public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                            System.out.println(t.getMessage());

                        }
                    });


                    System.out.println("*******************************");


                } else {

                    //FETCH POST
                    params = new RequestParams();
                    params.put("id_child", id_child);
                    params.put("id_activity", items.get(position).getId_item());
                    params.put("points", (int) rating);

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
                                status_evaluation[0]="200";



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


        }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description, pointsTxt;
        public ImageView activityLogo;
        RatingBar rating_bar;
        public RecyclerView recyclerView;


        public ViewHolder (View itemView){
            super (itemView);
            title = itemView.findViewById(R.id.textTitle4345);
            description = itemView.findViewById(R.id.textLocation3467);
            activityLogo = (ImageView) itemView.findViewById(R.id.activityLogo89);
            recyclerView = itemView.findViewById(R.id.recycler_view54665);
            pointsTxt = itemView.findViewById(R.id.points1Txt);
            rating_bar = itemView.findViewById(R.id.rating_bar2);



        }
    }


}
