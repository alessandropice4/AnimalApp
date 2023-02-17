package com.example.myapplication.Veterinario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.R;

public class LoginVeterinario extends AppCompatActivity {
    Button signin;
    EditText username, password;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_veterinario);
        db = new DBHelper(this);
        SQLiteDatabase database = db.getWritableDatabase();

/*
        //account di default unico per il veterinario
        String sql = "INSERT INTO veterinari ( username, password) VALUES (?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, "veterinario");
        statement.bindString(2, "password");
        long id = statement.executeInsert(); */

        signin = findViewById(R.id.btnsigninvet);
        username = findViewById(R.id.usernamevet);
        password = findViewById(R.id.passwordvet);
        signin.setOnClickListener(view -> {
            String vetname = username.getText().toString();
            String pw = password.getText().toString();
            if(vetname.equals("")||pw.equals(""))
                Toast.makeText(LoginVeterinario.this, "Per favore, inserisci tutti i campi", Toast.LENGTH_SHORT).show();
            else{
                Boolean checkuserpass = db.checkveterinario(vetname, pw);
                if(checkuserpass){
                    Toast.makeText(LoginVeterinario.this, "Login effettuato con successo", Toast.LENGTH_SHORT).show();
                    int id_vet = db.getUserId(vetname, pw); //preleviamo l'Id dell'utente che ha fatto il login
                    //passandolo ad AnimalActivity
                    Intent intent  = new Intent(getApplicationContext(), VeterinarioTab.class);
                    intent.putExtra("id_vet", id_vet);
                    startActivity(intent);

                }else{
                    Toast.makeText(LoginVeterinario.this, "Credenziali invalide, ritenta", Toast.LENGTH_SHORT).show();
                }
            }

        });



    }
}