package com.example.myapplication.Animal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;

public class AnimalActivity extends AppCompatActivity {
    //dichiarazione elementi grafici
    EditText nome_animale, age, species, race, microchip, residenza;
    Button aggiungianimale, displayanimals, selectImage;
    private static final int REQUEST_PICK_PHOTO = 100;
    private final int GALLERY_REQ_CODE = 1000;
    DBHelper DB; //database
    int userId; //Id Utente che verrà passato attraverso
    // le varie activity per permettere le operazioni sul DB
    ImageView imgGallery;
    Bitmap bitmap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        DB = new DBHelper(this);

        //elementi grafici
        nome_animale = (EditText) findViewById(R.id.nomeanimaleprenotazione);
        age = (EditText) findViewById(R.id.problema);
        species = (EditText) findViewById(R.id.species);
        race = (EditText) findViewById(R.id.race);
        microchip = (EditText) findViewById(R.id.microchip);
        residenza = (EditText) findViewById(R.id.residenza);
       // animalImage = (ImageView) findViewById(R.id.animal_image);

        aggiungianimale = (Button) findViewById(R.id.aggiungianimale);
        displayanimals = (Button) findViewById(R.id.lista_animali);
        selectImage = (Button) findViewById(R.id.foto_animale);
        imgGallery = findViewById(R.id.fotoprofilo);
        selectImage.setOnClickListener(v -> {
           Intent iGallery = new Intent(Intent.ACTION_PICK);
           iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
           startActivityForResult(iGallery, GALLERY_REQ_CODE);
        });




        aggiungianimale.setOnClickListener(view -> {
            String nomi = nome_animale.getText().toString();
            String età = age.getText().toString();
            String specie = species.getText().toString();
            String razza = race.getText().toString();
            String chip = microchip.getText().toString();
            String luogo = residenza.getText().toString();
            if(nomi.equals("") || età.equals("") || specie.equals("") || razza.equals("") || chip.equals("") || luogo.equals(""))
                Toast.makeText(AnimalActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                userId = getIntent().getIntExtra("userId", 0);


                bitmap = Bitmap.createBitmap(imgGallery.getWidth(), imgGallery.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                imgGallery.draw(canvas);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                Boolean insert = DB.insertAnimalsData(nomi, età, specie, razza, chip, luogo, userId, bitmap );

                if(insert) {
                    Toast.makeText(AnimalActivity.this, "Animale aggiunto con successo", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AnimalActivity.this, "Errore durante l'inserimento dell'animale", Toast.LENGTH_SHORT).show();
                }
            }




        });


        displayanimals.setOnClickListener(view ->{


            Intent intent  = new Intent(getApplicationContext(), AnimalView.class);
            intent.putExtra("userId", userId); //passiamo l'Id all'activity AnimalView
            startActivity(intent);



        } );//fine bottone displayanimals



    }//fine oncreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==GALLERY_REQ_CODE){
                //for gallery
                imgGallery.setImageURI(data.getData());
            }
        }
    }
}//fine classe




