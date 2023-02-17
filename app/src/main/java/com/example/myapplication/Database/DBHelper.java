package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "AnimalApp.db";

    public DBHelper(Context context) {
        super(context, "AnimalApp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE utenti(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT, cognome TEXT, username TEXT NOT NULL UNIQUE, password TEXT, telefono TEXT);");
        MyDB.execSQL("CREATE TABLE animals(_id INTEGER PRIMARY KEY AUTOINCREMENT, nome_animale TEXT NOT NULL , age INTEGER NOT NULL, species TEXT NOT NULL , race TEXT NOT NULL, microchip TEXT NOT NULL, " +
                "residenza TEXT NOT NULL, id_padrone INTEGER, image BLOB);");
        MyDB.execSQL("CREATE TABLE veterinari(_id INTEGER PRIMARY KEY , username TEXT not null, password TEXT not null)");
        MyDB.execSQL("CREATE TABLE prenotazioni(id INTEGER PRIMARY KEY," +
                "nome TEXT not null, specie TEXT not null, esame TEXT not null, data TEXT not null, ora TEXT not null, diagnosi TEXT not null,"
                +
                " id_padrone INTEGER not null);");
        MyDB.execSQL("CREATE TABLE segnalazioni(id INTEGER PRIMARY KEY, " +
                "titolo TEXT not null, oggetto TEXT not null, latitudine STRING not null, longitudine STRING not null, userid INTEGER not null)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {


        MyDB.execSQL("drop Table if exists utenti");
        MyDB.execSQL("drop Table if exists animals");
        MyDB.execSQL("drop Table if exists veterinari");
        MyDB.execSQL("drop table if exists prenotazioni");
        MyDB.execSQL("drop table if exists segnalazioni");
        MyDB.execSQL("drop Table if exists cartelle");


    }

    public Cursor readDataUtenti() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + "utenti";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    //Funzione per l'inserimento dei dati degli utenti
    public Boolean insertUsersData(String nome, String cognome,
                                   String username, String password, String telefono) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("cognome", cognome);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("telefono", telefono);
        long result = MyDB.insert("utenti", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    //Funzione per l'inserimento dei dati degli animali
    public Boolean insertAnimalsData(String nome, String age, String specie,
                                     String razza, String microchip, String residenza,
                                     Integer id_padrone, Bitmap image) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome_animale", nome);
        contentValues.put("age", age);
        contentValues.put("species", specie);
        contentValues.put("race", razza);
        contentValues.put("microchip", microchip);
        contentValues.put("residenza", residenza);
        contentValues.put("id_padrone", id_padrone);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        contentValues.put("image", byteArray);

        long result = MyDB.insert("animals", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean insertPrenotazione(String nome, String specie,
                                      String esame, String data, String orario,
                                      Integer id_padrone) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome_animale", nome);
        contentValues.put("species", specie);
        contentValues.put("data", data);
        contentValues.put("esame", esame);
        contentValues.put("id_padrone", id_padrone);
        contentValues.put("ora", orario);


        long result = MyDB.insert("prenotazioni", null, contentValues);
        if (result == -1) return false;
        else return true;
    }


    public byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }


    //Verifica l'esistenza dell'username
    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from utenti where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //Verifica l'esistenza di user-pass
    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from utenti where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkveterinario(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from veterinari where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //Prelievo dell'UserId + utilizzo dei wildcard per evitare sql injection
    public int getUserId(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id FROM utenti WHERE username = ? AND password = ?", new String[]{username, password});
        int userId = -1;
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(0);
        }
        cursor.close();
        return userId;
    }


    //richiamata in AnimalView
    public Cursor readDataByUserId(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String ANIMALS_TABLE_NAME = "animals";
        Cursor cursor = db.rawQuery("SELECT * FROM " + ANIMALS_TABLE_NAME + " WHERE id_padrone = " + userId, null);
        return cursor;
    }

    public Cursor readDataByUserIdPrenotazioni(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String TABLE_NAME = "prenotazioni";
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id_padrone = " + userId, null);
        return cursor;
    }

    //richiamato in updateanimalactivity
    public void updateDataAnimal(String _id, String nome, String età, String specie, String razza, String micro, String luogo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome_animale", nome);
        cv.put("age", età);
        cv.put("species", specie);
        cv.put("race", razza);
        cv.put("microchip", micro);
        cv.put("residenza", luogo);

        db.update("animals", cv, "_id=?", new String[]{_id});


    }

    public void updateDataUser(String _id, String nome, String età, String specie, String razza, String micro) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", nome);
        cv.put("cognome", età);
        cv.put("username", specie);
        cv.put("password", razza);
        cv.put("telefono", micro);


        db.update("animals", cv, "_id=?", new String[]{_id});


    }

    public void deleteOneRow(String id) {
        String TABLE_NAME = "animals";
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "_id=?", new String[]{id});

    }

    public void deleteOneRowPrenotazioni(String id) {
        String TABLE_NAME = "prenotazioni";
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{id});

    }


    public void updateDataPrenotazione(String id, String nome, String specie, String esame, String date, String time, String feedback) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", nome);
        cv.put("specie", specie);
        cv.put("esame", esame);
        cv.put("data", date);
        cv.put("ora", time);
        cv.put("diagnosi", feedback);

        db.update("animals", cv, "id=?", new String[]{id});

    }

    public void deleteOneRowUtente(String id) {
        String TABLE_NAME = "utenti";
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "_id=?", new String[]{id});

    }

    public Cursor readDataPrenotazioni() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + "prenotazioni";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }


    public Boolean insertReportDatas(String titolo_segnalazione, String oggetto_segnalazione, String latitudine, String longitudine, int userId) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("titolo", titolo_segnalazione);
        contentValues.put("oggetto", oggetto_segnalazione);
        contentValues.put("latitudine", latitudine);
        contentValues.put("longitudine", longitudine);
        contentValues.put("userid", userId);


        long result = MyDB.insert("prenotazioni", null, contentValues);
        if (result == -1) return false;
        else return true;
    }


}