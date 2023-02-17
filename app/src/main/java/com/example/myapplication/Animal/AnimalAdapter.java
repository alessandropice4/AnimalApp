package com.example.myapplication.Animal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Context;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList _id, nome_animale, age, species, race, microchip, residenza, immagine;


    AnimalAdapter(Activity activity, Context context,
                  ArrayList _id,
                  ArrayList nome_animale,
                  ArrayList age,
                  ArrayList species,
                  ArrayList race,
                  ArrayList microchip,
                  ArrayList residenza,
                  ArrayList immagine){
        this.activity = activity;
        this.context = context;
        this._id = _id;
        this.nome_animale = nome_animale;
        this.age = age;
        this.species = species;
        this.race = race;
        this.microchip = microchip;
        this.residenza = residenza;
        this.immagine = immagine;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_layout, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override

    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder._id.setText(String.valueOf(_id.get(position)));
        holder.nome_animale.setText(String.valueOf(nome_animale.get(position)));
        holder.age.setText(String.valueOf(age.get(position)));
        holder.species.setText(String.valueOf(species.get(position)));
        holder.race.setText(String.valueOf(race.get(position)));
        holder.microchip.setText(String.valueOf(microchip.get(position)));
        Bitmap bitmap = (Bitmap) immagine.get(position);
        holder.immagine.setImageBitmap(bitmap);

        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateAnimalActivity.class);
            intent.putExtra("_id", String.valueOf(_id.get(position)));

            intent.putExtra("nome_animale", String.valueOf(nome_animale.get(position)));
            intent.putExtra("age", String.valueOf(age.get(position)));
            intent.putExtra("species", String.valueOf(age.get(position)));
            intent.putExtra("race", String.valueOf(species.get(position)));
            intent.putExtra("microchip", String.valueOf(microchip.get(position)));
            intent.putExtra("residenza", String.valueOf(residenza.get(position)));
            intent.putExtra("image", String.valueOf(immagine.get(position)));
         //   intent.putExtra("image", image);

            activity.startActivityForResult(intent, 1);
        });





    }



    @Override
    public int getItemCount() {
        return _id.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder {
      //  public View mainLayout;
        TextView _id, nome_animale, age, species, race, microchip, residenza;
        ImageView immagine;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           _id = itemView.findViewById(R.id.id_adapter);
           nome_animale = itemView.findViewById(R.id.animale_adapter);
            age = itemView.findViewById(R.id.animal_age);
            species = itemView.findViewById(R.id.specie_adapter);
            race = itemView.findViewById(R.id.razza);
           microchip = itemView.findViewById(R.id.data_adapter);
            residenza = itemView.findViewById(R.id.residenza);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            immagine = itemView.findViewById(R.id.profiloadapter);

        }
    }
}
