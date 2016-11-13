package com.example.lioz.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lioz.shoppinglist.DataBase.DataBase;
import com.example.lioz.shoppinglist.DataBase.List;


/**
 * Created by Lioz on 09/11/2016.
 */

public class AddList extends AppCompatActivity {

    private TextView name, comment;
    private DataBase db ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlist);

        db = new DataBase(this);

        Button btn =(Button)findViewById(R.id.SaveListButton);
        name = (TextView) findViewById(R.id.editListName);
        comment = (TextView) findViewById(R.id.editComment);
        System.out.println("i'm opening db ");

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                System.out.println("i'm inserting into the list\n");
                List list = new List(name.getText().toString(),comment.getText().toString());
                db.addList(list);
                Intent intentAddList = new Intent(AddList.this,MyList.class);
                System.out.println(list.getId());
                intentAddList.putExtra("ID_LIST",list.getId());
                startActivity(intentAddList);
            }
        });


    }


}
