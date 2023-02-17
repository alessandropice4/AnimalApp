package com.example.myapplication.Prenotazioni;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Animal.UpdateAnimalActivity;
import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.R;

public class UpdatePrenotazioniActivity extends AppCompatActivity {

    EditText nome_animale, esame, species, data, ora, esito;
    Button update_button, delete_button;

    String id, nome, esami, specie, date, time, feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_prenotazioni);

        nome_animale = findViewById(R.id.upnameprenotazione);
        esame = findViewById(R.id.upesame);
        species = findViewById(R.id.upspecieprenotazione);
        data = findViewById(R.id.updata);
        ora = findViewById(R.id.upora);
        esito = findViewById(R.id.upesito);

        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();
//  String id, nome, esami, specie, date, time, feedback;
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nome);
        }
        update_button.setOnClickListener(view -> {
            //And only then we call this  EditText nome_animale, esame, species, data, ora, esito;
            //    Button update_button, delete_button;
            DBHelper myDB = new DBHelper(UpdatePrenotazioniActivity.this);
            nome = nome_animale.getText().toString();
            esami = esame.getText().toString();
            specie = species.getText().toString();
            date = data.getText().toString();
            time = ora.getText().toString();
            feedback = esito.getText().toString();
            myDB.updateDataPrenotazione( id, nome, specie, esami, date, time, feedback);
        });
        delete_button.setOnClickListener(view -> confirmDialog());

    }

    void getAndSetIntentData(){
        if(


                getIntent().hasExtra("id")
                        && getIntent().hasExtra("nome_animale")
                        && getIntent().hasExtra("esame")
                        && getIntent().hasExtra("species")
                        && getIntent().hasExtra("data")
                        && getIntent().hasExtra("ora")
                        && getIntent().hasExtra("esito")){

            id = getIntent().getStringExtra("id");
            nome = getIntent().getStringExtra("nome_animale");
            esami = getIntent().getStringExtra("esame");
            specie = getIntent().getStringExtra("species");
            date = getIntent().getStringExtra("data");
            time = getIntent().getStringExtra("ora");
            feedback = getIntent().getStringExtra("esito");

            nome_animale.setText(nome);
            esame.setText(esami);
            species.setText(specie);
            data.setText(date);
            ora.setText(time);
            esito.setText(feedback);


        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cancellare la prenotazione?  " );
        builder.setMessage("Sei sicuro che vuoi cancellare la prenotazione " );
        builder.setPositiveButton("Sì", (dialogInterface, i) -> {
            DBHelper myDB = new DBHelper(UpdatePrenotazioniActivity.this);
            myDB.deleteOneRowPrenotazioni(id);
            finish();
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {

        });
        builder.create().show();
    }
    }
