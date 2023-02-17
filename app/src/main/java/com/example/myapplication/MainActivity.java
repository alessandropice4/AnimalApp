package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.Veterinario.LoginVeterinario;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword, name, surname, cellphone;
    Button signup, signin, veterinario;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.main_name);
        surname = (EditText) findViewById(R.id.main_surname);
        username = (EditText) findViewById(R.id.main_username);
        password = (EditText) findViewById(R.id.main_pass);
        repassword = (EditText) findViewById(R.id.main_repas);
        cellphone = (EditText) findViewById(R.id.main_cell);

        signup = (Button) findViewById(R.id.button_sup);
        signin = (Button) findViewById(R.id.button_log);
        veterinario = (Button) findViewById(R.id.vet);
        DB = new DBHelper(this);

        //fine onclick
        signup.setOnClickListener(view -> {

            String names = name.getText().toString();
            String surnames = surname.getText().toString();
            String users = username.getText().toString();
            String cellphones = cellphone.getText().toString();

            String pass = password.getText().toString();
            String repass = repassword.getText().toString();

            if(        names.equals("")
                    || pass.equals("")
                    || repass.equals("")
                    || surnames.equals("")
                    || users.equals("")
                    || cellphones.equals("")
            )
                Toast.makeText(MainActivity.this, "Per favore, inserisci tutti i campi", Toast.LENGTH_SHORT).show();
            else{
                if(pass.equals(repass)){
                    Boolean checkuser = DB.checkusername(users);
                    if(!checkuser){
                        Boolean insert = DB.insertUsersData(names, surnames, users, pass, cellphones );
                        if(insert){
                            Toast.makeText(MainActivity.this, "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Registrazione fallita", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "L'utente è già presente nel sistema", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Le password devono essere uguali", Toast.LENGTH_SHORT).show();
                }
            }



        }


        );
        signin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });

        veterinario.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginVeterinario.class);
            startActivity(intent);
        });
    }



}//fine classe