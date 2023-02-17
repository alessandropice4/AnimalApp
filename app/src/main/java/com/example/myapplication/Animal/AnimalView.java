package com.example.myapplication.Animal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.widget.Toast;

import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.R;

public class AnimalView extends AppCompatActivity {
    ArrayList<String>   nome_animale,  species, race,  residenza;
    ArrayList<Integer> _id, age, microchip;
    ArrayList<Bitmap> images;
    DBHelper DB;
    AnimalAdapter adattatore;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inizializza il RecyclerView
        setContentView(R.layout.activity_animal_view);
        recyclerView = findViewById(R.id.rview);
        // Inizializza gli ArrayList per i dati dell'animale  e inizializza il DB


        DB = new DBHelper(AnimalView.this);
        _id = new ArrayList<>();
        nome_animale = new ArrayList<>();
        age = new ArrayList<>();
        species = new ArrayList<>();
        race = new ArrayList<>();
        microchip = new ArrayList<>();
        residenza = new ArrayList<>();
        images = new ArrayList<>();


        storeDataInArrays();

        adattatore = new AnimalAdapter(AnimalView.this,this,  _id, nome_animale, age, species, race, microchip, residenza, images);
        recyclerView.setAdapter(adattatore);
        recyclerView.setLayoutManager(new LinearLayoutManager(AnimalView.this));
    }



    void storeDataInArrays(){
        int userId = getIntent().getIntExtra("userId", 0);
        Cursor cursor = DB.readDataByUserId(userId); //funzione che restituisce un cursore filtrato sull'userId
        if(cursor.getCount() == 0){
            Toast.makeText(AnimalView.this, "Nessun Animale Presente", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                _id.add(cursor.getInt(0));
                nome_animale.add(cursor.getString(1));
                age.add(cursor.getInt(2));
                species.add(cursor.getString(3));
                race.add(cursor.getString(4));
                microchip.add(cursor.getInt(5));
                residenza.add(cursor.getString(6));
              // image.add(cursor.getBlob(7));
               byte[] imageData = cursor.getBlob(8);


                Bitmap image = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                images.add(image);

            }
        }
    }

}