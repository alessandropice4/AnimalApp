package com.example.myapplication.Segnalazioni;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Animal.AnimalAdapter;
import com.example.myapplication.Animal.UpdateAnimalActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class SegnalazioniAdapter extends RecyclerView.Adapter<SegnalazioniAdapter.MyViewHolder>{


    private Context context;
    private Activity activity;
    private ArrayList id, titolo, oggetto, latitudine, longitudine;


    SegnalazioniAdapter(Activity activity, Context context,
                  ArrayList id,
                  ArrayList titolo,
                  ArrayList oggetto,
                  ArrayList latitudine,
                  ArrayList longitudine
                 ){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.titolo = titolo;
        this.oggetto = oggetto;
        this.latitudine = latitudine;
        this.longitudine = longitudine;

    }




    @NonNull
    @Override
    public SegnalazioniAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapterforum, parent, false);
        return new SegnalazioniAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    public void onBindViewHolder(@NonNull SegnalazioniAdapter.MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.titolo.setText(String.valueOf(titolo.get(position)));
        holder.latitudine.setText(String.valueOf(latitudine.get(position)));
        holder.longitudine.setText(String.valueOf(longitudine.get(position)));


        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, ObjectSegnalazioni.class);

            intent.putExtra("oggetto", String.valueOf(oggetto.get(position)));



            activity.startActivityForResult(intent, 1);
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //  public View mainLayout;
        TextView id, titolo, oggetto, latitudine, longitudine;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_adapter);
            titolo = itemView.findViewById(R.id.titolo_a);

            latitudine = itemView.findViewById(R.id.latitudine_a);
            longitudine = itemView.findViewById(R.id.longitudine_a);

            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }


}
