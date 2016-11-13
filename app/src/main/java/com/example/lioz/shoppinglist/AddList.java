package com.example.lioz.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
        dbList.addList(new List("birthDay","Paul"));
        dbList.getList();
    }


}
