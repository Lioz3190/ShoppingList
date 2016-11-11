package com.example.lioz.shoppinglist;

/**
 * Created by Lioz on 10/11/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper  {
    protected SQLiteDatabase db = null;
    protected DataBase dbHandler = null;
    public static final String listOfList = "ListOfList";
    public static final String myList = "MyList";
    public static final String listOfList_CREATE = "CREATE TABLE IF NOT EXISTS"+listOfList+"(Id INTEGER PRIMARY KEY AUTOINCREMENT,MyListId INTEGER, FOREIGN KEY(MyListId) REFERENCES MyList(Id) ";
    public static final String myList_CREATE = "CREATE TABLE IF NOT EXISTS"+ myList+"(Id INTEGER PRIMARY KEY AUTOINCREMENT, ListName VARCHAR(20)";
    public DataBase(Context context, CursorFactory factory) {
        super(context, "ShoppingList.db", factory, 1);
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
    public SQLiteDatabase open(){
        db = dbHandler.getWritableDatabase();
        return db;
    }
    public void close(){
        db.close();
    }
    public SQLiteDatabase getDb(){
        return db;
    }
}

