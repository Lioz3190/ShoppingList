package com.example.lioz.shoppinglist.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lioz.shoppinglist.AddList;

/**
 * Created by Lioz on 11/11/2016.
 */

public class AddListDB {
    private static final String TABLE_NAME = "ListOfList";
    public static final String KEY_ID_ListOfList = "idListOfList";
    public static final String KEY_NAME_MyList = "MyListName";
    public static final String CREATE_TABLE_LIST = "CREATE TABLE IF NOT EXISTS " +TABLE_NAME+
            "("+ KEY_ID_ListOfList +"INTEGER PRIMARY KEY);";
    public static final String DROP_TABLE_LIST = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
    private DataBase mDb;
    private SQLiteDatabase db;

    public AddListDB(Context context){
        mDb = DataBase.getInstance(context);
    }
    public void open(){
        db = mDb.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    public long addList(AddList addList){
        ContentValues values = new ContentValues();
        values.put(KEY_ID_ListOfList,addList.getIdListOfList());
        return db.insert(TABLE_NAME,null,values);
    }
    public Cursor getAddList(){
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
    }
}
