package com.example.myapplication.Profili;

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
import com.example.myapplication.Prenotazioni.PrenotazioniAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ProfiliAdapter extends RecyclerView.Adapter<ProfiliAdapter.MyViewHolder>{
    private Context context;
    private Activity activity;
    private ArrayList nome,  cognome, username,  password, telefono, id;

    ProfiliAdapter(Activity activity, Context context, ArrayList nome,
    ArrayList cognome,    ArrayList username,    ArrayList password,    ArrayList telefono,
                     ArrayList id){
        this.activity = activity;
        this.context = context;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.telefono = telefono;
        this.id = id;
    }



    @NonNull
    @Override
    public ProfiliAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_profili_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nome.setText(String.valueOf(nome.get(position)));
        holder.cognome.setText(String.valueOf(cognome.get(position)));
        holder.username.setText(String.valueOf(username.get(position)));
        holder.password.setText(String.valueOf(password.get(position)));
        holder.telefono.setText(String.valueOf(telefono.get(position)));
        holder.id.setText(String.valueOf(id.get(position)));


        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateAnimalActivity.class);
            intent.putExtra("id", String.valueOf(id.get(position)));

            intent.putExtra("nome", String.valueOf(nome.get(position)));
            intent.putExtra("cognome", String.valueOf(cognome.get(position)));
            intent.putExtra("username", String.valueOf(username.get(position)));
            intent.putExtra("password", String.valueOf(password.get(position)));
            intent.putExtra("telefono", String.valueOf(telefono.get(position)));


            activity.startActivityForResult(intent, 1);
        });
    }



    /*Activity activity, Context context, ArrayList nome,
    ArrayList cognome,    ArrayList username,    ArrayList password,    ArrayList telefono,
                     ArrayList id*/


    @Override
    public int getItemCount() {
        return id.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        //  public View mainLayout;
        TextView id, nome, cognome, username, password, telefono;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_adapter);
            nome = itemView.findViewById(R.id.nomeutente);
            cognome = itemView.findViewById(R.id.cognomeutente);
            username = itemView.findViewById(R.id.usernamepersona);
            password = itemView.findViewById(R.id.password);
            telefono = itemView.findViewById(R.id.numerotelefono);
            mainLayout = itemView.findViewById(R.id.mainLayout);


        }
    }
}
