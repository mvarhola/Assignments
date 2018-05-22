package com.cs442.mvarhola.l6foodorderapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs442.mvarhola.l6foodorderapp.data.Food;

import java.util.List;

/**
 * Created by p0rt on 10/6/17.
 */

public class CustomAdminFoodListAdapter extends ArrayAdapter<Food> {
    private Context context;
    private List<Food> foods;
    private int layoutResource;


    public CustomAdminFoodListAdapter(Context context, int layoutResource, List<Food> foods) {
        super(context, layoutResource, foods);
        // TODO Auto-generated constructor stub
        this.layoutResource = layoutResource;
        this.foods = foods;
        this.context = context;

    }



    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater vi;
        vi = LayoutInflater.from(context);
        View rowView=vi.inflate(R.layout.item_admin_layout, null,true);

        TextView txt_item_id = (TextView) rowView.findViewById(R.id.item_id);
        TextView txt_item_name = (TextView) rowView.findViewById(R.id.title);
        TextView txt_item_price = (TextView) rowView.findViewById(R.id.item_price);

        Log.d("CustomFoodListAdapter: ", String.valueOf(foods.get(position)));

        Food food = foods.get(position);

        Log.d("CustomFoodListAdapter: ", String.valueOf(food.getId()));

        txt_item_id.setText(String.valueOf(food.getId()));
        txt_item_name.setText(food.getName());
        txt_item_price.setText(food.getPrice());

        return rowView;

    };
}
