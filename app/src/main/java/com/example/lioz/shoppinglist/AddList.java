package com.example.lioz.shoppinglist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import com.example.lioz.shoppinglist.DataBase.AddListDB;
import com.example.lioz.shoppinglist.DataBase.AddListInformation;
import com.example.lioz.shoppinglist.DataBase.DataBase;
import com.example.lioz.shoppinglist.DataBase.MyListInformation;

import java.util.ArrayList;

/**
 * Created by Lioz on 09/11/2016.
 */

public class AddList extends AppCompatActivity {

    private EditText myListName;
    private AddListInformation addList;
    private MyListInformation myList;
    private AddListDB dbList;
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
                dbList.open();
                dbList.addList(addList,myList);
                dbList.close();
                Intent intentAddList = new Intent(AddList.this,MyList.class);
                startActivity(intentAddList);
            }
        });
    }


}
