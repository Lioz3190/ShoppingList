package com.example.lioz.shoppinglist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import android.view.View;
import android.widget.*;
/**
 * Created by Lioz on 05/11/2016.
 */

public class MyList extends AppCompatActivity {
    private ArrayList<String> listItems;
    private ArrayAdapter<String> adapter;
    private EditText editTxt;
    private Button btn;
    private int id;
    private String listName;
    public MyList(String listName_){
        listName = listName_;
        listItems = new ArrayList<>();
    }
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_mylist);
        editTxt = (EditText) findViewById(R.id.editTxt);
        btn = (Button)findViewById(R.id.addBtn);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, listItems);
        ListView lv = (ListView)findViewById(R.id.listElement);
        lv.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                listItems.add(editTxt.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
    }

    public int getId(){
        return id;
    }
    public String getListName(){
        return listName;
    }
    }

