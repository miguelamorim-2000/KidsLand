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

    ArrayList<String> titles;

    public AtividadesListAdapter(ArrayList<String> nomes){
        titles = nomes;
    }
    @Override
    public AtividadesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AtividadesListAdapter.ViewHolder holder, int position) {
    holder.title.setText(titles.get(position));
    holder.itemView.findViewById(R.id.containerActivity).setBackgroundColor(Color.parseColor("#F8FBFF"));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView title;

    public ViewHolder (View itemView){
        super (itemView);
        title = itemView.findViewById(R.id.textTitle);
    }
    }
}
