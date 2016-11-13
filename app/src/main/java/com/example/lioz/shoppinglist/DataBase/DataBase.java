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
    public static final String KEY_ID_List = "idList";
    public static final String LIST_NAME = "ListName";
    public static final String COMMENT = "Comment";

    // create and drop SQL query List
    public static final String CREATE_TABLE_LIST = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_LIST +
            " (" + KEY_ID_List + " INTEGER PRIMARY KEY AUTOINCREMENT," + LIST_NAME + " TEXT," + COMMENT + " TEXT);";
    public static final String DROP_TABLE_LIST = "DROP TABLE IF EXISTS " + TABLE_NAME_LIST + ";";

    private static final String TABLE_NAME_ITEM = "Item";
    public static final String KEY_ID_Item = "idItem";
    public static final String ARTICLE = "article";
    public static final String QUANTITY = "quantite";
    public static final String BOUGHT = "checkboxe";

    // create and drop table SQL query Item
    public static final String CREATE_TABLE_ITEM = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ITEM +
            "(" + KEY_ID_Item + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ID_List + " INTEGER," +
            ARTICLE + " TEXT," + QUANTITY + " INTEGER,"+BOUGHT+" INTEGER, FOREIGN KEY (" + KEY_ID_List + ") REFERENCES "+ TABLE_NAME_LIST+" ("+KEY_ID_List+"));";

    public static final String DROP_TABLE_ITEM = "DROP TABLE IF EXISTS " + TABLE_NAME_ITEM + ";";



    // database initial creation ( void database )
    private static final String DATABASE_NAME = "ShoppingList4";
    private static final int DATABASE_VERSION = 1;
    private static DataBase sInstance;

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

    }

    // refactor database
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_LIST);
        db.execSQL(DROP_TABLE_ITEM);
        onCreate(db);
    }

    public void addList(List list) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LIST_NAME, list.getListName());
        values.put(COMMENT, list.getComment());
        db.insert(TABLE_NAME_LIST, null, values);
        db.close();
    }

    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_List, item.getIdList());
        values.put(ARTICLE, item.getArticle());
        values.put(QUANTITY, item.getQuantity());
        values.put(BOUGHT, item.isBought());
        db.insert(TABLE_NAME_ITEM, null, values);
        db.close();
    }

    //get single list
    public List getList(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME_LIST, new String[]{KEY_ID_List,
                        LIST_NAME, COMMENT}, KEY_ID_List + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        List list = new List(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return list;
    }

    public java.util.List<List> getAllList() {
        java.util.List<List> fullList = new ArrayList<List>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME_LIST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME_LIST,null,null,null,null,null, null);

        // looping through all rows and adding to list
        if (cursor.getCount() == 0) return fullList;
        else {
            cursor.moveToFirst();
            do {
               List list = new List(Integer.parseInt(cursor
                        .getString(0)), cursor.getString(1),
                        cursor.getString(2));
                fullList.add(list);
            } while (cursor.moveToNext());
        }
        return fullList;
    }

    // update single List
    public int updateList(List list) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LIST_NAME, list.getListName());
        values.put(COMMENT, list.getComment());

        // updating row
        return db.update(TABLE_NAME_LIST, values, KEY_ID_List + " = ?",
                new String[]{String.valueOf(list.getId())});
    }

    // Deleting single list
    public void deleteList(List list) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_LIST, KEY_ID_List + " = ?",
                new String[]{String.valueOf(list.getId())});
        db.close();
    }
    public int deleteListWithId(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_LIST, KEY_ID_List + " = " + id , null);
    }

    // Getting list Count
    public int getListCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME_LIST;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // get a list of item belonging to the selected list
    public java.util.List<Item> getAllItemWithListId(int id) {
        java.util.List<Item> itemList = new ArrayList<Item>();
        String selectQ = "SELECT * FROM " + TABLE_NAME_ITEM + " WHERE "
                + KEY_ID_List + " = " + String.valueOf(id);
        System.out.println(selectQ);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQ, null);

        Cursor cursor2 = db.query(TABLE_NAME_ITEM, new String[]{KEY_ID_Item,
                        KEY_ID_List, ARTICLE}, KEY_ID_List + "=?",
                new String[]{String.valueOf(id)}, null, null, null,
                null);
        System.out.println(cursor.getCount());
        if ( cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                Item item = new Item(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID_Item))),
                        cursor.getString(cursor.getColumnIndex(ARTICLE)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(QUANTITY))));
                itemList.add(item);
            } while (cursor.moveToNext());
        }
        return itemList;
    }

    public java.util.List<Item> getAllItems() {
        java.util.List<Item> itemList = new ArrayList<Item>();
        String selectQ = "SELECT  * FROM " + TABLE_NAME_ITEM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQ, null);

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID_Item))),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID_List))),
                        cursor.getString(cursor.getColumnIndex(ARTICLE)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(QUANTITY))));
                itemList.add(item);
            } while (cursor.moveToNext());
        }
        return itemList;
    }


}

