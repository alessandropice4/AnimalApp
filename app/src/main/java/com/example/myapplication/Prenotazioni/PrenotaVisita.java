package com.example.myapplication.Prenotazioni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.R;


/*
   Intent intent  = new Intent(getApplicationContext(), AnimalView.class);
            intent.putExtra("userId", userId); //passiamo l'Id all'activity AnimalView
            startActivity(intent);
 */

public class PrenotaVisita extends AppCompatActivity {
    EditText nome, specie, esame, data, ora;
    Button prenotati, lista_prenotazioni;
    DBHelper DB;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenota_visita);
        nome = findViewById(R.id.nomeanimaleprenotazione);
        specie = findViewById(R.id.specieprenotazione);
        esame = findViewById(R.id.tipodiesame);
        data = findViewById(R.id.preferenzadata);
        ora = findViewById(R.id.ora);
        prenotati = findViewById(R.id.inviaprenotazione);
        lista_prenotazioni = findViewById(R.id.visualizzaprenotazioni);
        DB = new DBHelper(this);

        prenotati.setOnClickListener(view -> {
            String nome_animale = nome.getText().toString();
            String specie_animale = specie.getText().toString();
            String esame_animale = esame.getText().toString();
            String data_preferita = data.getText().toString();
            String orario = ora.getText().toString();

            if(nome_animale.equals("") || specie_animale.equals("") || esame_animale.equals("") || data_preferita.equals("")
            || orario.equals(""))
                Toast.makeText(PrenotaVisita.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else{
                userId = getIntent().getIntExtra("userId", 0);
                Boolean insert = DB.insertPrenotazione(nome_animale, specie_animale,
                       data_preferita,orario, esame_animale, userId );

                if(insert) {
                    Toast.makeText(PrenotaVisita.this, "Prenotazione aggiunta con successo", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PrenotaVisita.this, "Errore durante l'inserimento della prenotazione", Toast.LENGTH_SHORT).show();
                }

            }


        });

        lista_prenotazioni.setOnClickListener(view -> {


            Intent intent  = new Intent(getApplicationContext(), PrenotazioniView.class);
            intent.putExtra("userId", userId); //passiamo l'Id all'activity AnimalView
            startActivity(intent);

        });






    }
}