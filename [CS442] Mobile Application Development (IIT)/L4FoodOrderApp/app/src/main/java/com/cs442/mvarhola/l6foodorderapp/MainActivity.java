package com.cs442.mvarhola.l6foodorderapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cs442.mvarhola.l6foodorderapp.fragments.FoodMenuFragment;

public class MainActivity extends AppCompatActivity implements FoodMenuFragment.OnItemSelectedListener {


    public static final String EXTRA_MESSAGE = "foodID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void openFoodInfo(int position) {
        Intent intent = new Intent(this, FoodDetail.class);
        intent.putExtra(EXTRA_MESSAGE, Integer.toString(position));
        startActivity(intent);
    }

    @Override
    public void onFoodItemSelected(int position) {

        openFoodInfo(position);

    }



}
