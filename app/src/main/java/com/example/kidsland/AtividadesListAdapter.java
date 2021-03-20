package com.example.kidsland;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AtividadesListAdapter extends RecyclerView.Adapter<AtividadesListAdapter.ViewHolder> {

    ArrayList<ListItem> items;

    public AtividadesListAdapter(ArrayList<ListItem> listItems){
        items = listItems;
    }
    @Override
    public AtividadesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AtividadesListAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTittle());
        holder.description.setText(items.get(position).getLocation());
        holder.itemView.findViewById(R.id.containerActivity).setBackgroundColor(Color.parseColor("#F8FBFF"));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;

        public ViewHolder (View itemView){
            super (itemView);
            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textLocation);
        }
    }


}
