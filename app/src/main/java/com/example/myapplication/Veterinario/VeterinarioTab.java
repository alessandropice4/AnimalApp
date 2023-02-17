package com.example.myapplication.Veterinario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.Prenotazioni.PrenotazioniView;
import com.example.myapplication.Profili.ProfiliView;
import com.example.myapplication.R;
import com.example.myapplication.Segnalazioni.ForumSegnalazioni;

public class VeterinarioTab extends AppCompatActivity {
    int id_vet;
    int userId;
    Button  prenotazioni, cartelle, profili,segnalazioni;
    //Menù del veterinario
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinario_tab);
        prenotazioni = findViewById(R.id.gestioneprenotazioni);
        cartelle = findViewById(R.id.cartellecliniche);
        profili = findViewById(R.id.gestioneprofili);
        segnalazioni = findViewById(R.id.segnalazioniutente);
        id_vet  = getIntent().getIntExtra("id_vet", 0);


        userId = id_vet;


        prenotazioni.setOnClickListener(view -> {
            Intent intent  = new Intent(getApplicationContext(), PrenotazioniView.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });


        cartelle.setOnClickListener(view -> {
            Intent intent  = new Intent(getApplicationContext(), PrenotazioniView.class);
            intent.putExtra("isVeterinario", userId);
            startActivity(intent);
        });

        profili.setOnClickListener(view -> {
            Intent intent  = new Intent(getApplicationContext(), ProfiliView.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });

        segnalazioni.setOnClickListener(view -> {
            Intent intent  = new Intent(getApplicationContext(), ForumSegnalazioni.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });
    }
}