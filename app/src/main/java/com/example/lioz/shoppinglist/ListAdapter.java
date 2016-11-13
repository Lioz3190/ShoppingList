package com.example.lioz.shoppinglist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lioz.shoppinglist.DataBase.List;

/**
 * Created by VALENTIN on 13/11/2016.
 */

public class ListAdapter extends ArrayAdapter<List> {

    Context context;
    private int layoutResourceId;
    java.util.List <List> shoppingList;

    public ListAdapter(Context context, int resource, java.util.List <List> list) {
        super(context, resource, list);
        this.context = context;
        this.shoppingList = list;
        this.layoutResourceId = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutResourceId, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intentMyList = new Intent(context, MyList.class);
                    intentMyList.putExtra("ID_LIST",shoppingList.get(position).getId());
                    context.startActivity(intentMyList);
                }

                ;
            });
        }
        TextView name = (TextView) view.findViewById(R.id.nameList);
        TextView comment = (TextView) view.findViewById(R.id.comment);
        List list = shoppingList.get(position);
        name.setText(list.getListName());
        comment.setText(list.getComment());

        return view;
    }
}
