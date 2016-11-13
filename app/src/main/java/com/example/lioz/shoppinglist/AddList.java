package com.example.lioz.shoppinglist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.lioz.shoppinglist.DataBase.DataBase;
import com.example.lioz.shoppinglist.DataBase.ListManager;
import com.example.lioz.shoppinglist.DataBase.List;
import com.example.lioz.shoppinglist.DataBase.Item;

/**
 * Created by Lioz on 09/11/2016.
 */

public class AddList extends AppCompatActivity {

    private EditText myListName;
    private List addList;
    private Item myList;
    private ListManager dbList = new ListManager(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlist);
        Button btn =(Button)findViewById(R.id.SaveListButton);
        System.out.println("i'm opening db\n");

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                System.out.println("i'm inserting into the list\n");
                Intent intentAddList = new Intent(AddList.this,MyList.class);
                startActivity(intentAddList);
            }
        });

        dbList.open();
        dbList.addList(new List(1,"birthDay","Paul"));
        ListManager lm = new ListManager(this);
        lm.open();
        java.util.List<List> values = lm.getList();
        ArrayAdapter<List> adapter = new ArrayAdapter<List>(this,android.R.layout.simple_list_item_1,values);
        ListView lv = (ListView)findViewById(R.id.mainList);
        lv.setAdapter(adapter);
        dbList.close();
    }


}
