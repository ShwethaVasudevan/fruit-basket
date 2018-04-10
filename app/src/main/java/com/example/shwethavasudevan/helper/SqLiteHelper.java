package com.example.shwethavasudevan.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shwethavasudevan.model.CartItem;
import com.example.shwethavasudevan.model.User;

/**
 * Created by shwethavasudevan on 07/04/18.
 */

public class SqLiteHelper extends SQLiteOpenHelper {

    // Database name and version
    public static final String DB_NAME= "fbdata";
    public static final int DB_VERSION = 1;

    // User table Strings
    public static final String TABLE_USERS = "users";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DOB = "dob";
    public static final String KEY_ADDRESS = "address";

    // Execute SQL for "users" table
    public static final String SQL_TABLE_USERS = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS +
            "(" + KEY_USERNAME + " TEXT, " + KEY_PASSWORD + " TEXT, " +KEY_EMAIL
            +" TEXT, "+ KEY_DOB + " TEXT, " + KEY_ADDRESS + "TEXT)";

    // Cart table Strings
    public static final String TABLE_CART = "cart";
    public static final String KEY_PROD_NAME = "name";
    public static final String KEY_QUANTITY = "quantity";

    // Execute SQL for "cart" table
    // DO NOT FORGET to parse Integer "quantity" to String

    public static final String SQL_TABLE_CART = "CREATE TABLE IF NOT EXISTS " + TABLE_CART + "(" +
            KEY_PROD_NAME + " TEXT, " + KEY_QUANTITY + " INTEGER)";


    public SqLiteHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);

    }

    // Create tables here
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_CART);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop all tables and recreate to upgrade
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(sqLiteDatabase);

    }

                /* user defined methods begin here.. */

    // Add data to the "users" database
    public void addUser(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_USERNAME, user.username);
        values.put(KEY_PASSWORD, user.password);
        values.put(KEY_EMAIL, user.email);
        values.put(KEY_DOB, user.dob);
        values.put(KEY_ADDRESS, user.address);

        final long insert = sqLiteDatabase.insert(TABLE_USERS, null, values);
    }

    // Authenticate username and password during login
    public User authenticateUser(User user) {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_USERS,new String[]{KEY_USERNAME,KEY_PASSWORD,
                        KEY_EMAIL,KEY_DOB,KEY_ADDRESS}, KEY_USERNAME + "= ? AND "
                        + KEY_PASSWORD + "= ? ", new String[]{user.username,user.password},
                null,null,null);

        if(cursor != null && cursor.moveToFirst() && cursor.getCount()>0) {
            User userTemp = new User(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4));
            return userTemp;
        }
        return null;
    }

    // Check if user with specified email already exists during registration
    public boolean isEmailExists(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_USERS,new String[]{KEY_USERNAME,KEY_PASSWORD,KEY_DOB,
                KEY_DOB,KEY_ADDRESS},KEY_EMAIL + "=?",new String[]{email},
                null,null,null);

        if(cursor != null && cursor.moveToFirst() && cursor.getCount()>0) {
            return  true;
        }
        return false;
    }

    // Add item to cart
    public void addCartItem(CartItem cartItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_PROD_NAME, cartItem.productName);
        values.put(KEY_QUANTITY,cartItem.quantity);

        final long insert = sqLiteDatabase.insert(TABLE_CART, null, values);

    }

    // Add extra cart item
    public void addExtraCartItem(CartItem cartItem) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int quantity = Integer.parseInt(cartItem.quantity) + 1;
        cartItem.quantity = String.valueOf(quantity);
        values.put(KEY_QUANTITY, cartItem.quantity);
        final int update = sqLiteDatabase.update(TABLE_CART, values,
                KEY_PROD_NAME + " =? ", new String[]{cartItem.productName});
    }

    // decrease cart item quantity by 1
    public void decreaseQuantityByOne(CartItem cartItem) {

        int quantity = Integer.parseInt(cartItem.quantity);
        if(quantity>0) {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            quantity = quantity - 1;
            cartItem.quantity = String.valueOf(quantity);
            values.put(KEY_QUANTITY, cartItem.quantity);
            final int update = sqLiteDatabase.update(TABLE_CART, values,
                    KEY_PROD_NAME + " =? ",new String[]{cartItem.productName});
        }
    }

    // remove an item from the cart
    public void removeCartItem(CartItem cartItem) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        final int delete = sqLiteDatabase.delete(TABLE_CART,KEY_PROD_NAME + "=?",
                new String[]{cartItem.productName});
    }
}

