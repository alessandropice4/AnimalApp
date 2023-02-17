package com.example.myapplication.Segnalazioni;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.Prenotazioni.UpdatePrenotazioniActivity;
import com.example.myapplication.R;

public class ObjectSegnalazioni extends AppCompatActivity {
    EditText oggetto;
    ImageView view;
    String object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_segnalazioni);
        oggetto = findViewById(R.id.oggettodellasegnalazione);
        view = findViewById(R.id.imageViewTriangolo);
        getAndSetIntentData();



    }



    private void getAndSetIntentData() {
        getIntent().hasExtra("oggetto");
        object = getIntent().getStringExtra("oggetto");
        oggetto.setText(object);
    }
}