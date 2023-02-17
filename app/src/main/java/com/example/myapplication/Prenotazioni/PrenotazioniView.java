package com.example.myapplication.Prenotazioni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.Animal.AnimalAdapter;
import com.example.myapplication.Animal.AnimalView;
import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.R;

import java.util.ArrayList;

public class PrenotazioniView extends AppCompatActivity {
    ArrayList<String> nome_animale,  specie, esame, data, ora;
    ArrayList<String> esito;
    ArrayList<Integer> _id;
    DBHelper DB;
    PrenotazioniAdapter adattatore;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazioni_view);
        recyclerView = findViewById(R.id.rview2);

        DB = new DBHelper(PrenotazioniView.this);
        _id = new ArrayList<>();
        nome_animale = new ArrayList<>();
        specie = new ArrayList<>();
        esame = new ArrayList<>();
        data = new ArrayList<>();
        ora = new ArrayList<>();
        esito = new ArrayList<>();



        storeDataInArrays();

        adattatore = new PrenotazioniAdapter(PrenotazioniView.this,this,  _id, nome_animale, specie,
                esame, data, ora, esito);
        recyclerView.setAdapter(adattatore);
        recyclerView.setLayoutManager(new LinearLayoutManager(PrenotazioniView.this));
    }

/*
    void storeDataInArrays(){
        int userId = getIntent().getIntExtra("userId", 0);

        Cursor cursor = DB.readDataByUserIdPrenotazioni(userId); //funzione che restituisce un cursore filtrato sull'userId
        if(cursor.getCount() == 0){
            Toast.makeText(PrenotazioniView.this, "Nessuna prenotazione presente", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                _id.add(cursor.getInt(0));
                nome_animale.add(cursor.getString(1));
                specie.add(cursor.getString(2));
                esame.add(cursor.getString(3));
                data.add(cursor.getString(4));
                ora.add(cursor.getString(5));
                esito.add(cursor.getString(6));


            }
        }*/
            void storeDataInArrays(){
    int userId = getIntent().getIntExtra("userId", 0);
    Cursor cursor;
    if (userId != 0) {
        cursor = DB.readDataByUserIdPrenotazioni(userId);
    } else {
        cursor = DB.readDataPrenotazioni();
    }

    if(cursor.getCount() == 0){
        Toast.makeText(PrenotazioniView.this, "Nessuna prenotazione presente", Toast.LENGTH_SHORT).show();
    }else{
        while (cursor.moveToNext()){
            _id.add(cursor.getInt(0));
            nome_animale.add(cursor.getString(1));
            specie.add(cursor.getString(2));
            esame.add(cursor.getString(3));
            data.add(cursor.getString(4));
            ora.add(cursor.getString(5));
            esito.add(cursor.getString(6));


        }
    }
}

    }



