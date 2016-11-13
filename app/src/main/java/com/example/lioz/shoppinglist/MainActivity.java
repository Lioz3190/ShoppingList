package com.example.lioz.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.lioz.shoppinglist.DataBase.DataBase;
import com.example.lioz.shoppinglist.DataBase.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBase(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab2 = (FloatingActionButton)findViewById(R.id.action_removelist);
        fab2.setImageResource(R.drawable.trash_can);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                java.util.List<List> list = db.getAllList();
                for (int i = 1 ; i <= list.size();i++) {
                    if (((CheckBox) view).isChecked()) {
                        db.deleteListWithId(i);
                    }
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.action_addlist);
        fab.setImageResource(R.drawable.addlistwhite);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddList = new Intent(MainActivity.this,AddList.class);
                startActivity(intentAddList);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        java.util.List<List> list = db.getAllList();
        if (list.size() != 0){
            ListAdapter adapter = new ListAdapter(this,R.layout.remove_item_list_of_list,list);
            ListView lv = (ListView)findViewById(R.id.mainList);
            lv.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_myshop:
                Intent intentShop = new Intent(MainActivity.this,PlacesAPIActivity.class);
                startActivity(intentShop);
                break;
            default:
                break;
        }
            return super.onOptionsItemSelected(item);
        }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
       switch(item.getItemId()) {

           case R.id.action_myshop:
               Intent intentShop = new Intent(MainActivity.this,PlacesAPIActivity.class);
               startActivity(intentShop);
               break;
           case R.id.action_addlist:
               Intent intentAddList = new Intent(MainActivity.this,AddList.class);
               startActivity(intentAddList);
               break;
           case R.id.alert_date:
               Intent intentAlertDate = new Intent(MainActivity.this,AlertDate.class);
               startActivity(intentAlertDate);
               break;
       }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void displayListView (){

    }
}
