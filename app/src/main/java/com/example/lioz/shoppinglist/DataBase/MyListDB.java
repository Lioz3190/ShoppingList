package com.example.lioz.shoppinglist.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lioz.shoppinglist.AddList;
import com.example.lioz.shoppinglist.MyList;

/**
 * Created by Lioz on 11/11/2016.
 */

public class MyListDB {
    private static final String TABLE_NAME = "MyList";
    private static final String TABLE_PARENT_NAME = "ListOfList";
    public static final String KEY_ID_MyList = "idMyList";
    public static final String KEY_NAME_MyList = "MyListName";
    public static final String KEY_PARENT_ID = "idParentList";
    private String[] allColumns = {KEY_ID_MyList,KEY_NAME_MyList};
    public static final String CREATE_TABLE_MY_LIST = "CREATE TABLE IF NOT EXISTS " +TABLE_NAME+
            "("+ KEY_ID_MyList +"INTEGER PRIMARY KEY,"+
            KEY_NAME_MyList + "VARCHAR(20),"+KEY_PARENT_ID+" INTEGER , FOREIGN KEY("+KEY_PARENT_ID+") REFERENCES "+TABLE_PARENT_NAME +"(idListOfList));";
    public static final String DROP_TABLE_MY_LIST = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
    private DataBase mDb;
    private SQLiteDatabase db;

    public MyListDB(Context context){
        mDb = DataBase.getInstance(context);
    }
    public void open(){
        db = mDb.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    public long addMyList(MyList myList){
        ContentValues values = new ContentValues();
        values.put(KEY_ID_MyList,myList.getId());
        values.put(KEY_NAME_MyList,myList.getListName());
        values.put(KEY_PARENT_ID,myList.getIdParentList());
        return db.insert(TABLE_NAME,null,values);
    }
    public Cursor getMyList(){
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
    }
    public void deleteList(MyList myList){
        long id = myList.getId();
        db.delete(TABLE_NAME,KEY_ID_MyList + " = " + id,null);
    }
}
