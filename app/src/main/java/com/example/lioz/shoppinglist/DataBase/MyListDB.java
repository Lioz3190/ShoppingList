package com.example.lioz.shoppinglist.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lioz.shoppinglist.AddList;
import com.example.lioz.shoppinglist.MyList;
import com.example.lioz.shoppinglist.DataBase.MyListInformation;

/**
 * Created by Lioz on 11/11/2016.
 */

public class MyListDB {
    // database table's variables
    private static final String TABLE_NAME = "MyList";
    public static final String KEY_ID_MyList = "IdMyList";
    public static final String ARTICLE = "Article";
    public static final String QUANTITY = "Quantite";
    public static final String BOUGHT = "Check";

    // create and drop table SQL query
    public static final String CREATE_TABLE_MY_LIST = "CREATE TABLE IF NOT EXISTS " +TABLE_NAME+
            "("+ KEY_ID_MyList +"INTEGER PRIMARY KEY,"+
            ARTICLE + "VARCHAR2(20),"+QUANTITY+" INTEGER,"+BOUGHT+" BOOLEAN);";
    public static final String DROP_TABLE_MY_LIST = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
    // my database
    private DataBase mDb;
    private SQLiteDatabase db;

    public MyListDB(Context context){
        mDb = DataBase.getInstance(context);
    }
    // open database
    public void open(){db = mDb.getWritableDatabase();}
    // close database
    public void close(){
        db.close();
    }
    // add articles to my list
    public long addMyList(MyListInformation myListInformation){
        ContentValues values = new ContentValues();
        values.put(KEY_ID_MyList,myListInformation.getId());
        values.put(ARTICLE,myListInformation.getArticle());
        values.put(QUANTITY,myListInformation.getQuantity());
        values.put(BOUGHT,myListInformation.isBought());
        return db.insert(TABLE_NAME,null,values);
    }
    // select everything from the table SQL query
    public Cursor getMyList(){
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
    }

    // delete the selected list
    public void deleteList(MyListInformation myListInformation){
        long id = myListInformation.getId();
        db.delete(TABLE_NAME,KEY_ID_MyList + " = " + id,null);
    }
    //update the select List
    public int updateMyList(int id , MyListInformation myList){
        ContentValues values = new ContentValues();
        values.put(ARTICLE,myList.getArticle());
        values.put(QUANTITY,myList.getQuantity());
        values.put(BOUGHT,myList.isBought());
        return db.update(TABLE_NAME,values, KEY_ID_MyList + " = "+ id,null);
    }
}
