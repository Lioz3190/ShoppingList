package com.example.lioz.shoppinglist;

/**
 * Created by Lioz on 10/11/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper  {
    public static final String listOfList_CREATE = "CREATE TABLE IF NOT EXISTS ListOfList(Id INTEGER PRIMARY KEY AUTOINCREMENT,MyListId INTEGER, FOREIGN KEY(MyListId) REFERENCES MyList(Id) ";
    public static final String myList_CREATE = "CREATE TABLE IF NOT EXISTS MyList(Id INTEGER PRIMARY KEY AUTOINCREMENT";
    public DataBase(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(listOfList_CREATE);
        db.execSQL(myList_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ListOfList;");
        db.execSQL(" DROP TABLE IF EXISTS MyList;");
        onCreate(db);
    }
}

