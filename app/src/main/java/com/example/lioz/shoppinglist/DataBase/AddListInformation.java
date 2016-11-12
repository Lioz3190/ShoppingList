package com.example.lioz.shoppinglist.DataBase;

import com.example.lioz.shoppinglist.MyList;

import java.util.ArrayList;

/**
 * Created by Lioz on 12/11/2016.
 */

public class AddListInformation {
    private int id ,idList;
    private String listName;
    private String comment;
    public AddListInformation(int id_ , String listName_, int idList_, String comment_){
        this.id = id_;
        this.listName = listName_;
        this.idList = idList_;
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

    public int getIdList() {
        return idList;
    }

    public void setIdList(int idList) {
        this.idList = idList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
