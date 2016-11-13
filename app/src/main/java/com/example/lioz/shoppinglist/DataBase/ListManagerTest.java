package com.example.lioz.shoppinglist.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Lioz on 13/11/2016.
 */

public class ListManagerTest {
    private static final String TABLE_NAME_LIST = "List";
    public static final String KEY_ID_List = "IdList";
    public static final String LIST_NAME = "ListName";
    public static final String COMMENT = "Comment";

    private static final String TABLE_NAME_ITEM = "Item";
    public static final String KEY_ID_Item = "idItem";
    public static final String ARTICLE = "article";
    public static final String QUANTITY = "quantite";
    public static final String BOUGHT = "check";
    private DataBase mDb;
    private SQLiteDatabase db;

    public ListManagerTest(Context context) {
        mDb = DataBase.getInstance(context);
    }

    public void open() {
        db = mDb.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void addList(List list) {
        db = mDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LIST_NAME, list.getListName());
        values.put(COMMENT, list.getComment());
        db.insert(TABLE_NAME_LIST, null, values);
        db.close();
    }

    public void addItem(Item item) {
        db = mDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_Item, item.getIdItem());
        values.put(KEY_ID_List, item.getIdList());
        values.put(ARTICLE, item.getArticle());
        values.put(QUANTITY, item.getQuantity());
        values.put(BOUGHT, item.isBought());
        db.insert(TABLE_NAME_ITEM, null, values);
        db.close();
    }

    //get single list
    public List getList(int id) {
        db = mDb.getReadableDatabase();

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

        db = mDb.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME_LIST,null,null,null,null,null, null);

        // looping through all rows and adding to list
        if (cursor.getCount() == 0) return null;
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
        db = mDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LIST_NAME, list.getListName());
        values.put(COMMENT, list.getComment());

        // updating row
        return db.update(TABLE_NAME_LIST, values, KEY_ID_List + " = ?",
                new String[]{String.valueOf(list.getId())});
    }

    // Deleting single list
    public void deleteList(List list) {
        db = mDb.getWritableDatabase();
        db.delete(TABLE_NAME_LIST, KEY_ID_List + " = ?",
                new String[]{String.valueOf(list.getId())});
        db.close();
    }

    // Getting list Count
    public int getListCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME_LIST;
        db = mDb.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // get a list of item belonging to the selected list
    public java.util.List<Item> getAllItemWithListId(int id) {
        java.util.List<Item> itemList = new ArrayList<Item>();
        String selectQ = "SELECT  * FROM " + TABLE_NAME_ITEM + " WHERE "
                + KEY_ID_List + " = " + String.valueOf(id) + ";";

        db = mDb.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQ, null);

        Cursor cursor2 = db.query(TABLE_NAME_ITEM, new String[]{KEY_ID_Item,
                        KEY_ID_List, ARTICLE}, KEY_ID_List + "=?",
                new String[]{String.valueOf(id)}, null, null, null,
                null);
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

    public java.util.List<Item> getAllItems() {
        java.util.List<Item> itemList = new ArrayList<Item>();
        String selectQ = "SELECT  * FROM " + TABLE_NAME_ITEM;

        db = mDb.getWritableDatabase();
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
