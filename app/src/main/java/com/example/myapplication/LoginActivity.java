package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Database.DBHelper;
import com.example.myapplication.Utente_.UserTab;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button btnlogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        DB = new DBHelper(this);
        btnlogin.setOnClickListener(view -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();

            if(user.equals("")||pass.equals(""))
                Toast.makeText(LoginActivity.this, "Per favore, inserisci tutti i campi", Toast.LENGTH_SHORT).show();
            else{
                Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                if(checkuserpass){
                    Toast.makeText(LoginActivity.this, "Login effettuato con successo", Toast.LENGTH_SHORT).show();
                    int id = DB.getUserId(user, pass); //preleviamo l'Id dell'utente che ha fatto il login
                    //passandolo ad usertab
                    Intent intent  = new Intent(getApplicationContext(), UserTab.class);
                    intent.putExtra("userId", id);
                    startActivity(intent);

                }else{
                    Toast.makeText(LoginActivity.this, "Credenziali invalide, ritenta", Toast.LENGTH_SHORT).show();
                }
            }





        });
    }






}//fine classe