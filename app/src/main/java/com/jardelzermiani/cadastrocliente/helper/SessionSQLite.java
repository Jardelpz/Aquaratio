package com.jardelzermiani.cadastrocliente.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SessionSQLite extends SQLiteOpenHelper {

    private static final String TAG = SessionSQLite.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "android_aquaratio";

    // Login table name
    private static final String TABLE_USER = "user";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NOME = "nome";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NICKNAME = "nickname";
    private static final String KEY_SENHA = "senha";
    private static final String KEY_INTEGRANTES = "integrantes";


    public SessionSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE "
                + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NOME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE,"
                + KEY_NICKNAME + "TEXT UNIQUE,"
                + KEY_SENHA + "TEXT,"
                + KEY_INTEGRANTES + "TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String nome, String email, String nickname, String senha, String integrantes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOME, nome);
        values.put(KEY_EMAIL, email);
        values.put(KEY_NICKNAME, nickname);
        values.put(KEY_SENHA, senha);
        values.put(KEY_INTEGRANTES, integrantes);

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("name", cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("nickname", cursor.getString(3));
            user.put("senha", cursor.getString(4));
            user.put("integrantes", cursor.getString(5));
        }
        cursor.close();
        db.close();
        // return user

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

    }

}
