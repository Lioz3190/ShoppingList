package com.example.lioz.shoppinglist.DataBase;

import java.util.ArrayList;

/**
 * Created by Lioz on 12/11/2016.
 */

public class Item {
    private int idItem, idList,quantity;
    String article;
    boolean bought;
    public Item(int idi_,int idl_, String article_, int quantity_){
        this.idItem = idi_;
        this.idList = idl_;
        this.article = article_;
        this.quantity = quantity_;
        this.bought = false;
    }

    public Item(int idl_, String article_, int quantity_){
        this.idList = idl_;
        this.article = article_;
        this.quantity = quantity_;
        this.bought = false;
    }

    public int getIdItem(){
        return this.idItem;
    }

    public void setIdItem(int id) {
        this.idItem = id;
    }

    public int getIdList(){
        return this.idList;
    }

    public void setIdList(int id) {
        this.idList = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
