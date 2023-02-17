package com.example.myapplication.Profili;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;


import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ProfiliView extends AppCompatActivity {



    ArrayList<String> nome,  cognome, username,  password, telefono;
    ArrayList<Integer>  id;
    DBHelper DB;
    ProfiliAdapter adattatore;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profili_view);
        recyclerView = findViewById(R.id.recprofili);

        DB = new DBHelper(ProfiliView.this);
        id = new ArrayList<>();
        nome = new ArrayList<>();
        cognome = new ArrayList<>();
        username = new ArrayList<>();
        password = new ArrayList<>();
        telefono = new ArrayList<>();

        storeDataInArrays();

        adattatore = new ProfiliAdapter(ProfiliView.this, this,  nome, cognome, username, password, telefono, id);
        recyclerView.setAdapter(adattatore);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProfiliView.this));




    }



    void storeDataInArrays(){
        int userId = getIntent().getIntExtra("userId", 0);
        Cursor cursor = DB.readDataUtenti();
        if(cursor.getCount() == 0){
            Toast.makeText(ProfiliView.this, "Nessun utente presente", Toast.LENGTH_SHORT).show();
        }else{
             /*
      MyDB.execSQL("CREATE TABLE utenti(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT, cognome TEXT, username TEXT NOT NULL UNIQUE, password TEXT,
                 telefono TEXT);");
     */
            while (cursor.moveToNext()){
                id.add(cursor.getInt(0));
                nome.add(cursor.getString(1));
                cognome.add(cursor.getString(2));
                username.add(cursor.getString(3));
                password.add(cursor.getString(4));
                telefono.add(cursor.getString(5));



            }
        }
    }
}