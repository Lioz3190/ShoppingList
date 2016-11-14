package com.example.lioz.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private int layoutResourceId, idlist;
    java.util.List <Item> listItems;
    private Item items;
    private DataBase db = new DataBase(getContext());

    public ItemAdapter(Context context, int resource, java.util.List <Item> list,int idList) {
        super(context, resource, list);
        this.context = context;
        this.listItems = list;
        this.layoutResourceId = resource;
        this.idlist = idList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutResourceId, parent, false);

        }
        final TextView item = (TextView) view.findViewById(R.id.nameItem);
        TextView quantity = (TextView) view.findViewById(R.id.quantityItem);
        CheckBox bought = (CheckBox) view.findViewById(R.id.boughtItem);
            items = listItems.get(position);
            item.setText(items.getArticle());
            quantity.setText(String.valueOf(items.getQuantity()));
            System.out.println(items.isBought());
            if (items.isBought()>0){
                bought.setChecked(true);
                item.setPaintFlags(item.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }else{
                bought.setChecked(false);
            }


        bought.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    listItems.get(position).setBought(1);
                    item.setPaintFlags(item.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    db.updateItem(listItems.get(position));

                } else {
                    listItems.get(position).setBought(0);
                    item.setPaintFlags(0);
                    db.updateItem(listItems.get(position));
                }
            }
        });

        Button removeItem = (Button) view.findViewById(R.id.remove_item);
        removeItem.setTag(position);
        removeItem.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new DataBase(context);
                db.getWritableDatabase();
                //int index = (int) view.getTag();
                listItems = db.getAllItems(idlist);
                listItems.remove(position);
                db.deleteItem(items);
                Intent intentMyList = new Intent(context, MyList.class);
                intentMyList.putExtra("ID_LIST", idlist);
                context.startActivity(intentMyList);
                db.close();
            }
        });

        return view;
    }

}
