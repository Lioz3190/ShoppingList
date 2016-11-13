package com.example.lioz.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lioz.shoppinglist.DataBase.DataBase;
import com.example.lioz.shoppinglist.DataBase.Item;
import com.example.lioz.shoppinglist.DataBase.List;

/**
 * Created by VALENTIN on 13/11/2016.
 */

public class ItemAdapter extends ArrayAdapter<Item> {

    Context context;
    private int layoutResourceId;
    java.util.List <Item> listItems;
    private DataBase db = new DataBase(getContext());

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

        bought.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    System.out.println(listItems.get(position).isBought());
                    System.out.println(listItems.get(position).getIdItem());
                    System.out.println(listItems.get(position).getArticle());
                    System.out.println("Check");
                    listItems.get(position).setBought(1);
                    db.updateItem(listItems.get(position));
                } else {
                    listItems.get(position).setBought(0);
                    db.updateItem(listItems.get(position));
                }
            }
        });

        return view;
    }

}
