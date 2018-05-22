package com.cs442.mvarhola.l6foodorderapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cs442.mvarhola.l6foodorderapp.CustomFoodListAdapter;
import com.cs442.mvarhola.l6foodorderapp.DatabaseSQLiteHelper;
import com.cs442.mvarhola.l6foodorderapp.R;
import com.cs442.mvarhola.l6foodorderapp.data.Food;

import java.util.List;

public class FoodMenuFragment extends Fragment {

    CustomFoodListAdapter itemsAdapter;
    List<Food> foods;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseSQLiteHelper db = new DatabaseSQLiteHelper(getContext());
        foods = db.getAllFood();

//        for(int i = 0; i < foods.size(); i++) {
//            Log.d("FoodMenu: ",foods.get(i).getName());
//        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_menu, parent, false);

        ListView lvItems = (ListView) view.findViewById(R.id.lvItems);

        itemsAdapter = new CustomFoodListAdapter(getContext(), R.layout.item_layout, foods);

        lvItems.setAdapter(itemsAdapter);


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ListView lvItems = (ListView) view.findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listener.onFoodItemSelected(position);
            }
        });
    }

    private OnItemSelectedListener listener;

    @Override
    public void onResume(){
        super.onResume();
        ListView lvItems = getView().findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnItemSelectedListener){
            this.listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement FoodMenuFragment.OnItemSelectedListener");
        }
    }




    public interface OnItemSelectedListener {
        void onFoodItemSelected(int position);
    }

}
