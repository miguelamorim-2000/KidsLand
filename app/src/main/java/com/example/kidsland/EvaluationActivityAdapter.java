package com.example.kidsland;

import android.content.Context;
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
import com.example.kidsland.backend.ListItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class EvaluationActivityAdapter extends RecyclerView.Adapter<EvaluationActivityAdapter.ViewHolder> {

    ArrayList<ListItem> items;
    RequestOptions option;
    AsyncHttpClient client;
    RequestParams params;
    String URL ="http://188.82.156.135:8080/Back-end/SubscriptionPost";
    String URL2 ="http://188.82.156.135:8080/Back-end/SubscriptionDelete";
    int id_childPost;
    Context context;
    RatingBar rating_bar;
    int id_activity;



    public EvaluationActivityAdapter(ArrayList<ListItem> listItems, int id_child, Context context){
        this.items = listItems;
        this.id_childPost = id_child;
        this.context = context;



    }
    @Override
    public EvaluationActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowevaluation, parent, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return new EvaluationActivityAdapter.ViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull EvaluationActivityAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTittle());
        holder.itemView.findViewById(R.id.containerActivity1).setBackgroundColor(Color.parseColor("#F8FBFF"));
        holder.itemView.findViewById(R.id.textTitleEvaluation).setPadding(0,40,20,40);
        id_activity = items.get(position).getId_item();

        rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                //FETCH POST
                params= new RequestParams();
                params.put("id_child", id_childPost);
                params.put("id_activity", id_activity);
                params.put("evaluation", rating);

                client = new AsyncHttpClient();
                client.post(URL, params, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
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
                        if (status.equals("200")){




                        }

                        if (status.equals("400")){




                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        System.out.println(errorResponse);
                    }

                });








            }
        });
        rating_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(rating_bar.getRating());
                System.out.println("ola");
            }
        });





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
        public TextView title;
        public ImageView activityLogo;
        public RecyclerView recyclerView;


        public ViewHolder (View itemView){
            super (itemView);
            title = itemView.findViewById(R.id.textTitleEvaluation);
            activityLogo = (ImageView) itemView.findViewById(R.id.activityLogo2);
            recyclerView = itemView.findViewById(R.id.recycler_view43);
            rating_bar = itemView.findViewById(R.id.rating_bar);



        }

    }

}
