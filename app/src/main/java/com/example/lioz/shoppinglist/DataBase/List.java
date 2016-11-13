package com.example.lioz.shoppinglist.DataBase;

import com.example.lioz.shoppinglist.MyList;

import java.util.ArrayList;

/**
 * Created by Lioz on 12/11/2016.
 */

public class List {
    private int id ;
    private String listName;
    private String comment;
    public List(int id_ , String listName_, String comment_){
        this.id = id_;
        this.listName = listName_;
        this.comment = comment_;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getListName(){
        return this.listName;
    }
    public void setListName(String name){
        this.listName = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
