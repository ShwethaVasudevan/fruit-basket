package com.example.shwethavasudevan.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shwethavasudevan on 07/04/18.
 */

public class SqLiteHelper extends SQLiteOpenHelper {

    // Database name and version
    public static final String USER_DB_NAME= "fbdata";
    public static final int DB_VERSION = 1;

    // User table Strings
    public static final String TABLE_USERS = "users";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_DOB = "dob";
    public static final String KEY_ADDRESS = "address";

    // Execute SQL for "users" table
    public static final String SQL_TABLE_USERS = "CREATE TABLE " + TABLE_USERS +
            "(" + KEY_USERNAME + " TEXT, " + KEY_PASSWORD + " TEXT, " + KEY_DOB +
            " TEXT, " + KEY_ADDRESS + "TEXT)";

    // Cart table Strings
    public static final String TABLE_CART = "cart";
    public static final String KEY_PROD_NAME = "name";
    public static final String KEY_QUANTITY = "quantity";

    // Execute SQL for "cart" table
    // DO NOT FORGET to parse Integer "quantity" to String

    public static final String SQL_TABLE_CART = "CREATE TABLE " + TABLE_CART + "(" +
            KEY_PROD_NAME + " TEXT, " + KEY_QUANTITY + " INTEGER)";


    public SqLiteHelper(Context context) {
        super(context,USER_DB_NAME,null,DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_CART);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);


    }
}
