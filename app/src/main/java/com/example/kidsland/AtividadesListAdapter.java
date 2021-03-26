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
import com.example.kidsland.backend.ListItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AtividadesListAdapter extends RecyclerView.Adapter<AtividadesListAdapter.ViewHolder> {

    ArrayList<ListItem> items;
    RequestOptions option;

    public AtividadesListAdapter(ArrayList<ListItem> listItems){
        this.items = listItems;

    }
    @Override
    public AtividadesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTittle());
        holder.description.setText(items.get(position).getLocation());
        holder.itemView.findViewById(R.id.containerActivity).setBackgroundColor(Color.parseColor("#F8FBFF"));
        holder.itemView.findViewById(R.id.textTitle).setPadding(0,40,20,40);
        holder.itemView.findViewById(R.id.textLocation).setPadding(0,30,20,40);

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
        public TextView description;
        public ImageView activityLogo;
        public RecyclerView recyclerView;


        public ViewHolder (View itemView){
            super (itemView);
            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textLocation);
            activityLogo = (ImageView) itemView.findViewById(R.id.activityLogo);
            recyclerView = itemView.findViewById(R.id.recycler_view);


        }
    }


}
