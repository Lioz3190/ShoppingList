package com.example.lioz.shoppinglist.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Lioz on 11/11/2016.
 */

public class ListManager {
    // database table's variables
    private static final String TABLE_NAME = "List";
    private static final String TABLE_NAME_CHILD = "Item";
    public static final String KEY_ID_List = "IdList";
    public static final String LIST_NAME = "ListName";
    public static final String COMMENT = "Comment";

    // create and drop SQL query
    public static final String CREATE_TABLE_LIST = "CREATE TABLE IF NOT EXISTS " +TABLE_NAME+
            "("+ KEY_ID_List +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ LIST_NAME + " VARCHAR2(10),"+COMMENT+" VARCHAR2(10));";
    public static final String DROP_TABLE_LIST = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
    // my database
    private DataBase mDb;
    private SQLiteDatabase db;

    public ListManager(Context context){
        mDb = DataBase.getInstance(context);
    }
    // open database
    public void open(){db = mDb.getWritableDatabase();}
    //close database
    public void close(){
        db.close();
    }
    // add a new list to the main activity
    public long addList(List list){
        ContentValues values = new ContentValues();
        values.put(LIST_NAME,list.getListName());
        values.put(COMMENT,list.getComment());
        System.out.println(values);
        return db.insert(TABLE_NAME,null,values);
    }
    // get every list of the main activity
    public Cursor getList(){
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
    }

    public int updateList(int id, List list){
        ContentValues values = new ContentValues();
        values.put(LIST_NAME,list.getListName());
        values.put(COMMENT,list.getComment());
        return db.update(TABLE_NAME,values, KEY_ID_List + " = "+ id,null);
    }
    public int removeList(int id){
        return db.delete(TABLE_NAME, KEY_ID_List + " = " + id , null);
    }
}