package com.example.lioz.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lioz.shoppinglist.DataBase.Item;
import com.example.lioz.shoppinglist.DataBase.List;

/**
 * Created by VALENTIN on 13/11/2016.
 */

public class ItemAdapter extends ArrayAdapter<Item> {

    Context context;
    private int layoutResourceId;
    java.util.List <Item> listItems;

    public ItemAdapter(Context context, int resource, java.util.List <Item> list) {
        super(context, resource, list);
        this.context = context;
        this.listItems = list;
        this.layoutResourceId = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutResourceId, parent, false);

        }
        TextView item = (TextView) view.findViewById(R.id.nameItem);
        TextView quantity = (TextView) view.findViewById(R.id.quantityItem);
        CheckBox bought = (CheckBox) view.findViewById(R.id.boughtItem);
        Item items = listItems.get(position);
        item.setText(items.getArticle());
        quantity.setText(String.valueOf(items.getQuantity()));
        if (items.isBought()>0){
            bought.setChecked(true);
        }else{
            bought.setChecked(false);
        }


        return view;
    }

}
