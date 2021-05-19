package com.example.kidsland;

import android.content.Context;
import android.graphics.Color;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.kidsland.backend.ListItem;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AtividadesListAdapter extends RecyclerView.Adapter<AtividadesListAdapter.ViewHolder> {

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



    public AtividadesListAdapter(ArrayList<ListItem> listItems, int id_child, Context context){
        this.items = listItems;
        this.id_childPost = id_child;
        this.context = context;



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
        holder.itemView.findViewById(R.id.containerActivity43).setBackgroundColor(Color.parseColor("#F8FBFF"));
        holder.itemView.findViewById(R.id.textTitle4345).setPadding(0,40,20,40);
        holder.itemView.findViewById(R.id.textLocation3467).setPadding(0,30,20,40);
        holder.timeActivity.setText(items.get(position).getTime().toString());

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
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


        holder.timeActivity673.setText(newDate);

        //IF CHILD HAS ALREADY SUBSCRIPTION TO ACTIVITY
        //HTTP GET
        OkHttpClient client2 = new OkHttpClient();

        id_activity= items.get(position).getId_item();


        String url = "http://188.82.156.135:8080/Back-end/ActivityVerifyGet?id_child="+ id_childPost + "&id_activity="+ id_activity;

        Request request = new Request.Builder().url(url).build();

        client2.newCall(request).enqueue(new Callback() {
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
                        for ( int i = 0; i < root.length(); i++) {
                            String status = "";
                            System.out.println(id_childPost);
                            System.out.println(id_activity);
                            System.out.println(body);
                            status= root.getString("STATUS");

                            String finalStatus = status;
                            ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {



                                    // SET BUTTON TO SUBSCRIPTED ACTIVITY
                                    if(finalStatus.equals("200")){

                                        holder.participateBtn.setVisibility(View.INVISIBLE);
                                        holder.participateBtn2.setVisibility(View.VISIBLE);

                                    }
                                }
                            });



                            }







                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Gson gson = new Gson();


                }
            }
        });








        //SHOW PHOTO
        String photo = "http://188.82.156.135:8080/Back-end/IMAGES/" + items.get(position).getPhoto();
        Picasso.get().load(photo)
                .placeholder(R.drawable.loadingicon)
                .error(R.drawable.loadingicon)
                .into((ImageView) holder.itemView.findViewById(R.id.activityLogo89));

        //CLICK TO PARTICIPATE
        holder.participateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_activity= items.get(position).getId_item();
                System.out.println("------------------------");
                System.out.println(id_activity);
                System.out.println("------------------------");


                //FETCH POST
                params= new RequestParams();
                params.put("id_child", id_childPost);
                params.put("id_activity", id_activity);
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
                        String finalStatus = status;
                        System.out.println("---------");
                        System.out.println(finalStatus);

                        System.out.println(finalStatus.equals("200"));

                        System.out.println("---------");

                        ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {



                                // SET BUTTON TO SUBSCRIPTED ACTIVITY
                                if(finalStatus.equals("200")){

                                    holder.participateBtn.setVisibility(View.INVISIBLE);
                                    holder.participateBtn2.setVisibility(View.VISIBLE);
                                    Toast.makeText(v.getContext(), "Inscrito com sucesso!", Toast.LENGTH_SHORT).show();

                                }

                                if (finalStatus.equals("400")){
                                    Toast.makeText(v.getContext(), "Já se encontra inscrito!", Toast.LENGTH_SHORT).show();




                                }
                            }
                        });








                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        System.out.println(errorResponse);
                    }

                });





            }
        });



        //CLICK TO CANCEL PARTICIPATION
        holder.participateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_activity= items.get(position).getId_item();
                System.out.println(id_activity);



                //FETCH POST
                params1= new RequestParams();
                params1.put("id_child", id_childPost);
                params1.put("id_activity", id_activity);


                //IF CHILD HAS ALREADY SUBSCRIPTION TO ACTIVITY
                //HTTP GET
                OkHttpClient client1 = new OkHttpClient();


                String url = "http://188.82.156.135:8080/Back-end/SubscriptionDelete?id_activity="+ id_activity + "&id_child="+ id_childPost;

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
                                for ( int i = 0; i < root.length(); i++) {
                                    String status = "";
                                    System.out.println(id_childPost);
                                    System.out.println(id_activity);
                                    System.out.println(body);
                                    status= root.getString("STATUS");


                                    String finalStatus = status;


                                    ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {



                                            // SET BUTTON TO SUBSCRIPTED ACTIVITY
                                            if(finalStatus.equals("200")){

                                                holder.participateBtn.setVisibility(View.VISIBLE);
                                                holder.participateBtn2.setVisibility(View.INVISIBLE);

                                            }
                                        }
                                    });


                                }






                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Gson gson = new Gson();


                        }
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface SelectedActivity{
        void selectedActivity(ListItem listItem);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public ImageView activityLogo;
        public RecyclerView recyclerView;
        public Button participateBtn, participateBtn2;
        public TextView timeActivity, timeActivity673;


        public ViewHolder (View itemView){
            super (itemView);
            title = itemView.findViewById(R.id.textTitle4345);
            description = itemView.findViewById(R.id.textLocation3467);
            activityLogo = (ImageView) itemView.findViewById(R.id.activityLogo89);
            recyclerView = itemView.findViewById(R.id.recycler_view54665);
            participateBtn = itemView.findViewById(R.id.participatebtn98);
            participateBtn2 = itemView.findViewById(R.id.participatebtn276);
            timeActivity = itemView.findViewById(R.id.timeActivity676);
            timeActivity673 = itemView.findViewById(R.id.timeActivity673);




        }

    }



}
