package com.example.myapplication.Prenotazioni;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Context;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Animal.AnimalAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;

public class PrenotazioniAdapter extends RecyclerView.Adapter<PrenotazioniAdapter.MyViewHolder> {


    private Context context;
    private Activity activity;
    private ArrayList id, nome_animale, esame, data, species, ora, esito;


    PrenotazioniAdapter(Activity activity, Context context,
                  ArrayList id,
                  ArrayList nome_animale,
                  ArrayList species,
                  ArrayList esame,
                  ArrayList data,
                  ArrayList ora,
                        ArrayList esito
               ){
        this.id = id;
        this.nome_animale = nome_animale;
        this.esame = esame;
        this.data = data;
        this.species = species;
        this.context = context;
        this.activity = activity;
        this.ora = ora;
        this.esito = esito;

    }

    @NonNull
    @Override
    public PrenotazioniAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_prenotazioni_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    public void onBindViewHolder(@NonNull PrenotazioniAdapter.MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.nome_animale.setText(String.valueOf(nome_animale.get(position)));
        holder.esame.setText(String.valueOf(esame.get(position)));
        holder.data.setText(String.valueOf(data.get(position)));
        holder.species.setText(String.valueOf(species.get(position)));
        holder.ora.setText(String.valueOf(ora.get(position)));
        holder.esito.setText(String.valueOf(esito.get(position)));

        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdatePrenotazioniActivity.class);
            intent.putExtra("id", String.valueOf(id.get(position)));

            intent.putExtra("nome_animale", String.valueOf(nome_animale.get(position)));
            intent.putExtra("esame", String.valueOf(esame.get(position)));
            intent.putExtra("species", String.valueOf(species.get(position)));
            intent.putExtra("data", String.valueOf(data.get(position)));
            intent.putExtra("ora", String.valueOf(data.get(position)));
            intent.putExtra("esito", String.valueOf(esito.get(position)));

            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }



       public class MyViewHolder extends RecyclerView.ViewHolder {
            //  public View mainLayout;
            TextView id, nome_animale, esame, species, data, ora, esito;
            LinearLayout mainLayout;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                id = itemView.findViewById(R.id.id_adapter);
                nome_animale = itemView.findViewById(R.id.animale_adapter);
                esame = itemView.findViewById(R.id.esame_adapter);
                species = itemView.findViewById(R.id.specie_adapter);
                data = itemView.findViewById(R.id.data_adapter);
                ora = itemView.findViewById(R.id.ora_adapter);
                esito = itemView.findViewById(R.id.esito_adapter);
                mainLayout = itemView.findViewById(R.id.mainLayout);

            }
        }



    }





