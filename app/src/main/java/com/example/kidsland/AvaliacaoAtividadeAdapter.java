package com.example.kidsland;

import android.content.Context;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.kidsland.backend.ListItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AvaliacaoAtividadeAdapter extends RecyclerView.Adapter<AvaliacaoAtividadeAdapter.ViewHolder> {

    ArrayList<ListItem> items;
    RequestOptions option;
    AsyncHttpClient client;
    AsyncHttpClient client1;
    AsyncHttpClient client2;
    String URL ="http://188.82.156.135:8080/Back-end/SubscriptionPost";
    String URL2 ="http://188.82.156.135:8080/Back-end/SubscriptionDelete";
    int id_childPost;
    int id_activity;
    RequestParams params;
    RequestParams params1;
    Context context;
    String statusCode = "";



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





        //SHOW PHOTO
        String photo = items.get(position).getPhoto();
        System.out.println(items.get(position).getPhoto());
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
        Button participatebtn275656;




        public ViewHolder (View itemView){
            super (itemView);
            textTitle434345 = itemView.findViewById(R.id.textTitleEvaluation);
            textLocation3464547= itemView.findViewById(R.id.textLocation3467);
            activityLogo865659 = itemView.findViewById(R.id.activityLogo2);
            timeActivity656576 = itemView.findViewById(R.id.timeActivity2);




        }

    }



}
