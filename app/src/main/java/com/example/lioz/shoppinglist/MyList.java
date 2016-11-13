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
    private ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private ListView listView;
    private EditText editTxt;
    private Button btn;
    // Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting a custom layout for the list activity
        setContentView(R.layout.activity_mylist);

        // Reference
        btn = (Button) findViewById(R.id.addBtn);
        editTxt = (EditText) findViewById(R.id.editTxt);
        listView = (ListView)findViewById(R.id.listElement);
        // Defining the ArrayAdapter to set items to ListView
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);

        // Defining a click event listener for the button "Add"
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listItems.add(editTxt.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
    }

}


