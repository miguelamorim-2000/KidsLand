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
import com.example.kidsland.backend.Fact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FactosActivityAdapter extends RecyclerView.Adapter<FactosActivityAdapter.ViewHolder> {

    ArrayList<Fact> items;
    RequestOptions option;

    public FactosActivityAdapter(ArrayList<Fact> listFacts){
        this.items = listFacts;

    }
    @Override
    public FactosActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowfacts, parent, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return new FactosActivityAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull FactosActivityAdapter.ViewHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
        holder.description.setText(items.get(position).getDescription());
        holder.itemView.findViewById(R.id.container434).setBackgroundColor(Color.parseColor("#F8FBFF"));

        String photo = "http://188.82.156.135:8080/Back-end/IMAGES/" + items.get(position).getPhoto();
        Picasso.get().load(photo)
                .placeholder(R.drawable.loadingicon)
                .error(R.drawable.loadingicon)
                .into((ImageView) holder.itemView.findViewById(R.id.factImage));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView description;
        public ImageView factPhoto;
        public RecyclerView recyclerView;


        public ViewHolder (View itemView){
            super (itemView);
            name = itemView.findViewById(R.id.textTitleFacts);
            description = itemView.findViewById(R.id.textDescriptionFact);
            factPhoto = (ImageView) itemView.findViewById(R.id.factImage);
            recyclerView = itemView.findViewById(R.id.recycler_viewFacts);


        }
    }
}
