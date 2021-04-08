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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.kidsland.backend.ListItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

public class Evaluation1ActivityAdapter extends RecyclerView.Adapter<Evaluation1ActivityAdapter.ViewHolder> {

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
    Context context;
    String statusCode = "";



    public Evaluation1ActivityAdapter(ArrayList<ListItem> listItems, int id_child, Context context){
        this.items = listItems;
        this.id_childPost = id_child;
        this.context = context;



    }

    @Override
    public Evaluation1ActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowevaluation1, parent, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return new Evaluation1ActivityAdapter.ViewHolder(view);
    }


        @Override
    public void onBindViewHolder(@NonNull Evaluation1ActivityAdapter.ViewHolder holder, int position) {
            holder.title.setText(items.get(position).getTittle());
            holder.itemView.findViewById(R.id.containerActivity145).setBackgroundColor(Color.parseColor("#F8FBFF"));
            holder.itemView.findViewById(R.id.textTitleEvaluation).setPadding(0,40,20,40);
            holder.timeActivity2.setText(items.get(position).getTime());
            holder.textLocation34.setText(items.get(position).getLocation());

            id_request = items.get(position).getId_item();
            System.out.println(id_request);


            holder.button12312.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("---------------------");
                    System.out.println(id_request);
                    System.out.println(id_childPost);
                    System.out.println("---------------------");

                }
            });
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
