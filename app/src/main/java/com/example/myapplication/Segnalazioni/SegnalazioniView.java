package com.example.myapplication.Segnalazioni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;


import com.example.myapplication.Animal.AnimalView;
import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.R;

import java.util.ArrayList;

public class SegnalazioniView extends AppCompatActivity {
    ArrayList<String> titolo, oggetto, latitudine, longitudine;
    ArrayList<Integer> id;

    DBHelper DB;
    SegnalazioniAdapter adattatore;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segnalazioni_view);


        DB = new DBHelper(SegnalazioniView.this);
        id = new ArrayList<>();
        titolo = new ArrayList<>();
        oggetto = new ArrayList<>();
        latitudine = new ArrayList<>();
        longitudine = new ArrayList<>();


        storeDataInArrays();
        adattatore = new SegnalazioniAdapter(SegnalazioniView.this,this, id, titolo, oggetto, latitudine, longitudine);
        recyclerView.setAdapter(adattatore);
        recyclerView.setLayoutManager(new LinearLayoutManager(SegnalazioniView.this));
    }


    void storeDataInArrays(){
        int userId = getIntent().getIntExtra("userId", 0);
        Cursor cursor = DB.readDataByUserId(userId); //funzione che restituisce un cursore filtrato sull'userId
        if(cursor.getCount() == 0){
            Toast.makeText(SegnalazioniView.this, "Nessuna segnalazione presente", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id.add(cursor.getInt(0));
                titolo.add(cursor.getString(1));
                oggetto.add(cursor.getString(2));
                latitudine.add(cursor.getString(3));
                longitudine.add(cursor.getString(4));

            }
        }
    }


}