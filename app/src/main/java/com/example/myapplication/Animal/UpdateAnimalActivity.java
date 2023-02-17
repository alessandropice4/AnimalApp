package com.example.myapplication.Animal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.R;

public class UpdateAnimalActivity extends AppCompatActivity {

    EditText nome_animale, age, species, race, microchip, residenza;
    Button update_button, delete_button;

    String id, nome, età, specie, razza, micro, luogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_animal2);

        nome_animale = findViewById(R.id.upname);
        age = findViewById(R.id.upage);
        species = findViewById(R.id.upspecie);
        race = findViewById(R.id.uprazza);
        microchip = findViewById(R.id.upmicrochip);
        residenza = findViewById(R.id.upresidenza);

        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nome);
        }
        update_button.setOnClickListener(view -> {
            //And only then we call this
            DBHelper myDB = new DBHelper(UpdateAnimalActivity.this);
            nome = nome_animale.getText().toString();
            età = age.getText().toString();
            specie = species.getText().toString();
            razza = race.getText().toString();
            micro = microchip.getText().toString();
            luogo = residenza.getText().toString();
            myDB.updateDataAnimal( id, nome, età, specie, razza, micro, luogo);
        });
        delete_button.setOnClickListener(view -> confirmDialog());

    }

    void getAndSetIntentData(){
        if(
                   getIntent().hasExtra("_id")
                && getIntent().hasExtra("nome_animale")
                && getIntent().hasExtra("species")
                && getIntent().hasExtra("race")
                && getIntent().hasExtra("microchip")
                && getIntent().hasExtra("residenza")
                && getIntent().hasExtra("age")){

            id = getIntent().getStringExtra("_id");
            nome = getIntent().getStringExtra("nome_animale");
            età = getIntent().getStringExtra("age");
            specie = getIntent().getStringExtra("species");
            razza = getIntent().getStringExtra("race");
            micro = getIntent().getStringExtra("microchip");
            luogo = getIntent().getStringExtra("residenza");

            nome_animale.setText(nome);
            age.setText(età);
            species.setText(specie);
            race.setText(razza);
            microchip.setText(micro);
            residenza.setText(luogo);


        }else{
          Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cancellare l'animale  " + nome + " ?");
        builder.setMessage("Sei sicuro che vuoi cancellare l'animale " + nome + " ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            DBHelper myDB = new DBHelper(UpdateAnimalActivity.this);
            myDB.deleteOneRow(id);
            finish();
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {

        });
        builder.create().show();
    }
}
