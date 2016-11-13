package com.example.lioz.shoppinglist.DataBase;

/**
 * Created by Lioz on 10/11/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DataBase extends SQLiteOpenHelper {

    // table et attribut
    private static final String TABLE_NAME_LIST = "List";
    public static final String KEY_ID_List = "IdList";
    public static final String LIST_NAME = "ListName";
    public static final String COMMENT = "Comment";

    // create and drop SQL query List
    public static final String CREATE_TABLE_LIST = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_LIST +
            "(" + KEY_ID_List + " INTEGER PRIMARY KEY AUTOINCREMENT," + LIST_NAME + " VARCHAR2(10)," + COMMENT + " VARCHAR2(10));";
    public static final String DROP_TABLE_LIST = "DROP TABLE IF EXISTS " + TABLE_NAME_LIST + ";";

    private static final String TABLE_NAME_ITEM = "Item";
    public static final String KEY_ID_Item = "idItem";
    public static final String ARTICLE = "article";
    public static final String QUANTITY = "quantite";
    public static final String BOUGHT = "check";

    // create and drop table SQL query Item
    public static final String CREATE_TABLE_ITEM = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ITEM +
            "(" + KEY_ID_Item + "," + KEY_ID_List + " ," +
            ARTICLE + "VARCHAR2(20)," + QUANTITY + " INTEGER," + BOUGHT + " BOOLEAN, FOREIGN KEY (" + KEY_ID_List + ") REFERENCES List(IdList), PRIMARY KEY (" + KEY_ID_Item + "," + KEY_ID_List + "));";
    public static final String DROP_TABLE_ITEM = "DROP TABLE IF EXISTS " + TABLE_NAME_ITEM + ";";

    // database initial creation ( void database )
    private static final String DATABASE_NAME = "ShoppingList.db";
    private static final int DATABASE_VERSION = 1;
    private static DataBase sInstance;
    private static final String query = "INSERT INTO List (Id,ListName,Comment) VALUES(0,\"birthday\",\"ta mere\");";

    // synchronize database
    public static synchronized DataBase getInstance(Context context) {
        if (sInstance == null)
            sInstance = new DataBase(context);
        return sInstance;
    }


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // create table
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LIST);
        db.execSQL(CREATE_TABLE_ITEM);
        db.execSQL(query);

    }

    // refactor database
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_LIST);
        db.execSQL(DROP_TABLE_ITEM);
        onCreate(db);
    }


}

