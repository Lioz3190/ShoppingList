package com.example.lioz.shoppinglist.DataBase;

/**
 * Created by Lioz on 10/11/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBase extends SQLiteOpenHelper  {
    private static final String DATABASE_NAME = "ShoppingList.db";
    private static final int DATABASE_VERSION = 1;
    private static DataBase sInstance;

    public static synchronized DataBase getInstance(Context context){
        if(sInstance == null)
            sInstance = new DataBase(context);
        return sInstance;
    }

    public DataBase(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AddListDB.CREATE_TABLE_LIST);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(AddListDB.DROP_TABLE_LIST);
        db.execSQL(" DROP TABLE IF EXISTS MyList;");
        onCreate(db);
    }


}

