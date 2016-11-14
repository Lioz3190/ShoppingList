package com.example.lioz.shoppinglist;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lioz.shoppinglist.DataBase.DataBase;
import com.example.lioz.shoppinglist.DataBase.Item;

import java.util.ArrayList;

/**
 * Created by Lioz on 05/11/2016.
 */
public class MyList extends AppCompatActivity {
    private ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private ListView listView;
    private EditText name;
    private EditText quantity;
    private Button addItem;
    private DataBase db;
    private Integer idList;

    // Called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        db = new DataBase(this);
        idList = getIntent().getExtras().getInt("ID_LIST");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.action_addlist);
        fab.setImageResource(R.drawable.addlistwhite);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog item_dialog = new Dialog(view.getContext());
                item_dialog.setContentView(R.layout.dialog_add_item);
                name = (EditText) item_dialog.findViewById(R.id.editItemName);
                quantity = (EditText) item_dialog.findViewById(R.id.editQuantity);
                addItem = (Button) item_dialog.findViewById(R.id.addItem);
                addItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (quantity.getText().toString().trim().length() == 0) {
                            db.addItem(new Item(idList, name.getText().toString(), 1, 0));
                        }else {
                            db.addItem(new Item(idList, name.getText().toString(), Integer.parseInt(quantity.getText().toString()), 0));
                        }
                        item_dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                });

                item_dialog.show();
            }
        });


        listView = (ListView) findViewById(R.id.listElement);

        java.util.List<Item> list = db.getAllItems(idList);
        if (list.size() != 0) {
            final ItemAdapter adapter = new ItemAdapter(this, R.layout.list_items, list,idList);
            ListView lv = (ListView) findViewById(R.id.listElement);
            lv.setAdapter(adapter);
        }


    }
}




