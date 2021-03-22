package com.example.kidsland;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AtividadesListAdapter extends RecyclerView.Adapter<AtividadesListAdapter.ViewHolder> {

    ArrayList<ListItem> items;
    private Context contexto;
    RequestOptions option;

    public AtividadesListAdapter(ArrayList<ListItem> listItems){
        items = listItems;
    }
    @Override
    public AtividadesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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
        public ImageView activityLogo;


        public ViewHolder (View itemView){
            super (itemView);
            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textLocation);
            activityLogo = itemView.findViewById(R.id.activityLogo);

          /*  URL url ;
            Bitmap bmp = null;
            ImageView logo;
            try {
                url = new URL("http://image10.bizrate-images.com/resize?sq=60&uid=2216744464");
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                activityLogo.setImageBitmap(bmp);
            }
*/

        }
    }


}
