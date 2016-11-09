package com.example.lioz.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

/**
 * Created by Lioz on 09/11/2016.
 */

public class AddList extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlist);
        Button btn =(Button)findViewById(R.id.SaveListButton);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intentAddList = new Intent(AddList.this,MyList.class);
                startActivity(intentAddList);
            }
        });
    }

}
