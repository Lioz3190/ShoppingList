package com.example.lioz.shoppinglist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import static com.example.lioz.shoppinglist.DataBase.listOfList;
import static com.example.lioz.shoppinglist.DataBase.listOfList_CREATE;

/**
 * Created by Lioz on 09/11/2016.
 */

public class AddList extends AppCompatActivity {
    private ArrayList<MyList> list ;
    SQLiteDatabase mDb;
    DataBase db;
    EditText myListName;
    public AddList(){
        list = new ArrayList<MyList>();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlist);
        Button btn =(Button)findViewById(R.id.SaveListButton);
        System.out.println("i'm opening db\n");

        myListName = (EditText)findViewById(R.id.editListName);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                System.out.println("i'm inserting into the list\n");
                insertList(new MyList(AddList.this.myListName.getText().toString()));
                ContentValues values = new ContentValues();
                mDb.insert(listOfList,null,values);
                Intent intentAddList = new Intent(AddList.this,MyList.class);
                startActivity(intentAddList);
            }
        });

    }
    public void insertList(MyList myList){
        list.add(myList);
    }

}
