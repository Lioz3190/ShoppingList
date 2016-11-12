package com.example.lioz.shoppinglist.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import com.example.lioz.shoppinglist.AddList;
import com.example.lioz.shoppinglist.MyList;
import com.example.lioz.shoppinglist.R;

/**
 * Created by Lioz on 11/11/2016.
 */

public class AddListDB {
    // database table's variables
    private static final String TABLE_NAME = "ListOfList";
    private static final String TABLE_NAME_CHILD = "MyList";
    public static final String KEY_ID_ListOfList = "IdListOfList";
    public static final String KEY_ID_MyList = "IdMyList";
    public static final String LIST_NAME = "ListName";
    public static final String COMMENT = "Comment";

    // create and drop SQL query
    public static final String CREATE_TABLE_LIST = "CREATE TABLE IF NOT EXISTS " +TABLE_NAME+
            "("+ KEY_ID_ListOfList +"INTEGER PRIMARY KEY,"+ LIST_NAME + " VARCHAR2(10)"+KEY_ID_MyList+" INTEGER,"+COMMENT+" VARCHAR2(10), FOREIGN KEY ("+KEY_ID_MyList+") REFERENCES "+TABLE_NAME_CHILD+"("+KEY_ID_MyList+");";
    public static final String DROP_TABLE_LIST = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
    // my database
    private DataBase mDb;
    private SQLiteDatabase db;

    public AddListDB(Context context){
        mDb = DataBase.getInstance(context);
    }
    // open database
    public void open(){db = mDb.getWritableDatabase();}
    //close database
    public void close(){
        db.close();
    }
    // add a new list to the main activity
    public long addList(AddListInformation addList, MyListInformation myList){
        ContentValues values = new ContentValues();
        values.put(KEY_ID_ListOfList,addList.getId());
        values.put(LIST_NAME,addList.getListName());
        values.put(KEY_ID_MyList,myList.getId());
        values.put(COMMENT,addList.getComment());
        return db.insert(TABLE_NAME,null,values);
    }
    // get every list of the main activity
    public Cursor getAddList(){
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
    }

    public int updateAddList(int id, AddListInformation addList){
        ContentValues values = new ContentValues();
        values.put(LIST_NAME,addList.getListName());
        values.put(COMMENT,addList.getComment());
        return db.update(TABLE_NAME,values, KEY_ID_MyList + " = "+ id,null);
    }
    public int removeAddList(int id, MyListInformation myList){
        return db.delete(TABLE_NAME, KEY_ID_MyList + " = " + id , null);
    }
}
