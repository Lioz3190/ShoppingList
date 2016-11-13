package com.example.lioz.shoppinglist.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Lioz on 11/11/2016.
 */

public class ItemManager {
    // database table's variables
    private static final String TABLE_NAME = "Item";
    public static final String KEY_ID_Item = "idItem";
    public static final String KEY_ID_List = "idList";
    public static final String ARTICLE = "article";
    public static final String QUANTITY = "quantite";
    public static final String BOUGHT = "check";

    // create and drop table SQL query
    public static final String CREATE_TABLE_ITEM = "CREATE TABLE IF NOT EXISTS " +TABLE_NAME+
            "("+ KEY_ID_Item +","+ KEY_ID_List +" ,"+
            ARTICLE + "VARCHAR2(20),"+QUANTITY+" INTEGER,"+BOUGHT+" BOOLEAN, FOREIGN KEY ("+KEY_ID_List+") REFERENCES List(IdList), PRIMARY KEY ("+KEY_ID_Item+","+KEY_ID_List+"));";
    public static final String DROP_TABLE_ITEM = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
    // my database
    private DataBase mDb;
    private SQLiteDatabase db;

    public ItemManager(Context context){
        mDb = DataBase.getInstance(context);
    }
    // open database
    public void open(){db = mDb.getWritableDatabase();}
    // close database
    public void close(){
        db.close();
    }
    // add articles to my list
    public long addMyList(Item myListInformation){
        ContentValues values = new ContentValues();
        values.put(KEY_ID_Item,myListInformation.getIdItem());
        values.put(KEY_ID_List,myListInformation.getIdList());
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
    public void deleteList(Item myListInformation){
        long id = myListInformation.getIdItem();
        long idList = myListInformation.getIdList();
        db.delete(TABLE_NAME,KEY_ID_Item + " = "+id+" AND "+KEY_ID_List + " = "+idList,null);
    }
    //update the select List
    public int updateMyList(int id , Item myList){
        ContentValues values = new ContentValues();
        values.put(ARTICLE,myList.getArticle());
        values.put(QUANTITY,myList.getQuantity());
        values.put(BOUGHT,myList.isBought());
        return db.update(TABLE_NAME,values, KEY_ID_Item + " = "+ id,null);
    }
}
