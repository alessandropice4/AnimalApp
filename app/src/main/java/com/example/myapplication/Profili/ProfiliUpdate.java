package com.example.myapplication.Profili;

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

public class ProfiliUpdate extends AppCompatActivity {
    EditText nome, cognome, user, telefono, password;
    Button update_button, delete_button;

    String id, name, surname, username, cell, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profili_update);
        nome = findViewById(R.id.upnomeutente);
        cognome = findViewById(R.id.upcognomeutente);
        password = findViewById(R.id.upusername);
        user = findViewById(R.id.uppassword);
        telefono = findViewById(R.id.uptelefono);

        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }
        update_button.setOnClickListener(view -> {
            //And only then we call this
            DBHelper myDB = new DBHelper(ProfiliUpdate.this);
            name = nome.getText().toString();
            surname = cognome.getText().toString();
            username = user.getText().toString();
            pass = password.getText().toString();
            cell = telefono.getText().toString();

            myDB.updateDataUser( id, name, surname, username, pass, cell);
        });
        delete_button.setOnClickListener(view -> confirmDialog());

    }
/*
 intent.putExtra("id", String.valueOf(id.get(position)));

            intent.putExtra("nome", String.valueOf(nome.get(position)));
            intent.putExtra("cognome", String.valueOf(cognome.get(position)));
            intent.putExtra("username", String.valueOf(username.get(position)));
            intent.putExtra("password", String.valueOf(password.get(position)));
            intent.putExtra("telefono", String.valueOf(telefono.get(position)));
 */
    void getAndSetIntentData(){
        if(
                getIntent().hasExtra("id")
                        && getIntent().hasExtra("nome")
                        && getIntent().hasExtra("cognome")
                        && getIntent().hasExtra("username")
                        && getIntent().hasExtra("password")
                        && getIntent().hasExtra("telefono")
                        ){

            id = getIntent().getStringExtra("_id");
            name = getIntent().getStringExtra("nome_animale");
            surname = getIntent().getStringExtra("age");
            username = getIntent().getStringExtra("species");
            pass = getIntent().getStringExtra("race");
            cell = getIntent().getStringExtra("microchip");

            nome.setText(name);
            cognome.setText(surname);
            user.setText(username);
            password.setText(pass);
            telefono.setText(cell);


        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cancellare l'utente ?");
        builder.setMessage("Sei sicuro che vuoi cancellare l'utente ?");
        builder.setPositiveButton("Sì", (dialogInterface, i) -> {
            DBHelper myDB = new DBHelper(ProfiliUpdate.this);
            myDB.deleteOneRowUtente(id);
            finish();
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {

        });
        builder.create().show();
    }
    }
