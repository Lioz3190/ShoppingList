package com.example.lioz.shoppinglist.DataBase;

import com.example.lioz.shoppinglist.MyList;

import java.util.ArrayList;

/**
 * Created by Lioz on 12/11/2016.
 */

public class List {
    private String listName;
    private String comment;
    public List(String listName_, String comment_){
        this.listName = listName_;
        this.comment = comment_;
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
