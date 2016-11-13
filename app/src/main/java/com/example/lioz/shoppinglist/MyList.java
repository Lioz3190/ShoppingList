package com.example.lioz.shoppinglist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;

import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.lioz.shoppinglist.DataBase.DataBase;
import com.example.lioz.shoppinglist.DataBase.Item;
import com.example.lioz.shoppinglist.DataBase.List;

/**
 * Created by Lioz on 05/11/2016.
 */
public class MyList extends AppCompatActivity {
    private ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private ListView listView;
    private EditText editTxt;
    private Button btn;
    private DataBase db;
    private Integer idList;
    // Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting a custom layout for the list activity
        setContentView(R.layout.activity_mylist);
        idList = getIntent().getExtras().getInt("ID_LIST");

        // Reference
        btn = (Button) findViewById(R.id.addBtn);
        editTxt = (EditText) findViewById(R.id.editTxt);
        listView = (ListView)findViewById(R.id.listElement);
        // Defining the ArrayAdapter to set items to ListView

        db = new DataBase(this);
        java.util.List<Item> list = db.getAllItems();

        Log.i("Test",list.toString());
        final ItemAdapter adapter = new ItemAdapter(this,R.layout.list_items,list);
        ListView lv = (ListView)findViewById(R.id.listElement);
        lv.setAdapter(adapter);



        // Defining a click event listener for the button "Add"
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                db.addItem(new Item(idList,editTxt.getText().toString(),1));
                adapter.notifyDataSetChanged();
            }
        });
    }

}


