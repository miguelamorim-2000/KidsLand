package com.example.kidsland;

import android.graphics.Color;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.kidsland.backend.HistoryItem;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolder> {

    ArrayList<HistoryItem> items;
    RequestOptions option;

    public HistoryListAdapter(ArrayList<HistoryItem> historyItems){
        this.items = historyItems;

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
        holder.itemView.findViewById(R.id.containerActivity).setBackgroundColor(Color.parseColor("#F8FBFF"));
        holder.itemView.findViewById(R.id.textTitle434).setPadding(0,40,20,40);
        holder.itemView.findViewById(R.id.textLocation34).setPadding(0,30,20,40);

        String photo = items.get(position).getPhoto();
        System.out.println(photo);
        Picasso.get().load(photo)
                .placeholder(R.drawable.loadingicon)
                .error(R.drawable.loadingicon)
                .into((ImageView) holder.itemView.findViewById(R.id.activityLogo));
        }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description, pointsTxt;
        public ImageView activityLogo;
        public RecyclerView recyclerView;


        public ViewHolder (View itemView){
            super (itemView);
            title = itemView.findViewById(R.id.textTitle434);
            description = itemView.findViewById(R.id.textLocation34);
            activityLogo = (ImageView) itemView.findViewById(R.id.activityLogo);
            recyclerView = itemView.findViewById(R.id.recycler_view4);
            pointsTxt = itemView.findViewById(R.id.pointsTxt);


        }
    }


}
