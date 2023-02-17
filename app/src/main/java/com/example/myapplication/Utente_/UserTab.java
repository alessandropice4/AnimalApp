package com.example.myapplication.Utente_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.Animal.AnimalActivity;
import com.example.myapplication.Prenotazioni.PrenotaVisita;
import com.example.myapplication.R;
import com.example.myapplication.Segnalazioni.ForumSegnalazioni;

public class UserTab extends AppCompatActivity {
    int userId; //Id Utente che verrà passato attraverso
    Button animalact, prenotazioni, segnalazione;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tab);
        userId = getIntent().getIntExtra("userId", 0);
        id = userId;
        animalact = findViewById(R.id.animalact);
        prenotazioni = findViewById(R.id.prenotazionevisita);
        segnalazione = findViewById(R.id.warning);

        animalact.setOnClickListener(view -> {
            Intent intent  = new Intent(getApplicationContext(), AnimalActivity.class);
            intent.putExtra("userId", id);
            startActivity(intent);
        });


        prenotazioni.setOnClickListener(view -> {
            Intent intent  = new Intent(getApplicationContext(), PrenotaVisita.class);
            intent.putExtra("userId", id);
            startActivity(intent);
        });

        segnalazione.setOnClickListener(view -> {
            Intent intent  = new Intent(getApplicationContext(), ForumSegnalazioni.class);
            intent.putExtra("userId", id);
            startActivity(intent);
        });






    }
}