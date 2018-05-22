package com.cs442.mvarhola.l6foodorderapp;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs442.mvarhola.l6foodorderapp.data.Food;
import com.cs442.mvarhola.l6foodorderapp.data.Order;

import java.util.List;


public class CustomAdminHistoryListAdapter extends ArrayAdapter<Order> {
    private Context context;
    private List<Order> orders;
    private int layoutResource;


    public CustomAdminHistoryListAdapter(Context context, int layoutResource, List<Order> orders) {
        super(context, layoutResource, orders);
        // TODO Auto-generated constructor stub
        this.layoutResource = layoutResource;
        this.orders = orders;
        this.context = context;

    }



    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater vi;
        vi = LayoutInflater.from(context);
        View rowView=vi.inflate(R.layout.history_item_layout, null,true);

        TextView txt_item_id = (TextView) rowView.findViewById(R.id.item_id);
        TextView txt_item_list = (TextView) rowView.findViewById(R.id.title);
        TextView txt_item_price = (TextView) rowView.findViewById(R.id.item_price);

        Order order= orders.get(position);

        txt_item_id.setText(String.valueOf(order.getId()));
        txt_item_list.setText(order.getItems());
        txt_item_price.setText(order.getTotal());

        return rowView;

    };
}
