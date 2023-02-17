package com.example.myapplication.Segnalazioni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Animal.AnimalActivity;
import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;

public class ForumSegnalazioni extends AppCompatActivity {
    EditText titolo, oggetto, latitudine, longitudine;
    Button allegaposizione, invia;
    DBHelper DB; //database
    String latitudeString, longitudeString;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_segnalazioni);
        DB = new DBHelper(this);
        titolo = findViewById(R.id.titolosegnalazione);
        oggetto = findViewById(R.id.testosegnalazione);
        latitudine = findViewById(R.id.latitudine);
        longitudine = findViewById(R.id.longitudine);
        allegaposizione = findViewById(R.id.allegaposizione);
        invia = findViewById(R.id.segnala);

        allegaposizione.setOnClickListener(view -> {



            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);

            if (ActivityCompat.checkSelfPermission(ForumSegnalazioni.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ForumSegnalazioni.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Richiedere i permessi di geolocalizzazione
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null


            ) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String latitudeString = String.valueOf(latitude);
                String longitudeString = String.valueOf(longitude);
                latitudine.setText(latitudeString);
                longitudine.setText(longitudeString);

            } else {
                Toast.makeText(ForumSegnalazioni.this, "Posizione non disponibile", Toast.LENGTH_SHORT).show();
            }


        });


        invia.setOnClickListener(view ->{

            String titolo_segnalazione = titolo.getText().toString();
            String oggetto_segnalazione = oggetto.getText().toString();
            if(titolo_segnalazione.equals("") || oggetto_segnalazione.equals("") || latitudeString.equals("")
            || longitudeString.equals(""))
                Toast.makeText(ForumSegnalazioni.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                userId = getIntent().getIntExtra("userId", 0);



                Boolean insert = DB.insertReportDatas(titolo_segnalazione, oggetto_segnalazione, latitudeString, longitudeString, userId);

                if(insert) {
                    Toast.makeText(ForumSegnalazioni.this, "Segnalazione aggiunta", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForumSegnalazioni.this, "Errore durante l'inserimento della segnalazione", Toast.LENGTH_SHORT).show();
                }
            }




        });



    }
}