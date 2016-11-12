package com.example.lioz.shoppinglist.DataBase;

import java.util.ArrayList;

/**
 * Created by Lioz on 12/11/2016.
 */

public class MyListInformation {
    private int id,quantity;
    String article;
    boolean bought;
    public MyListInformation(int id_,String article_,int quantity_){
        this.id = id_;
        this.article = article_;
        this.quantity = quantity_;
        this.bought = false;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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
