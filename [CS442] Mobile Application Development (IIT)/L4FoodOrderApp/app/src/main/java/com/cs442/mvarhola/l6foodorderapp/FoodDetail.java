package com.cs442.mvarhola.l6foodorderapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cs442.mvarhola.l6foodorderapp.data.Food;

public class FoodDetail extends AppCompatActivity {

    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        Intent intent = getIntent();
        String itemID = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        id = Integer.parseInt(itemID);

        TextView title = (TextView) findViewById(R.id.textView2);
        TextView itemText = (TextView) findViewById(R.id.textView3);

        EditText itemAmount = (EditText) findViewById(R.id.editText);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String amount = preferences.getString(Integer.toString(id), "");

        DatabaseSQLiteHelper db = new DatabaseSQLiteHelper(this);

        Food food = db.getFood(id);

        title.setText(food.getName());
        itemText.setText(food.getDescription());
        itemAmount.setText(amount);
    }

    public void onBtnClicked(View view){
        EditText itemAmount = (EditText) findViewById(R.id.editText);
        int quantity = Integer.parseInt(itemAmount.getText().toString());

        if (quantity > 0){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(Integer.toString(id),Integer.toString(quantity));
            editor.apply();

            finish();
        }else{
            Toast.makeText(this, "Please enter an amount greater than 0",Toast.LENGTH_SHORT).show();

        }

    }

    public void onResetBtnClicked(View view){
        EditText itemAmount = (EditText) findViewById(R.id.editText);

        itemAmount.setText("0");

        int quantity = Integer.parseInt(itemAmount.getText().toString());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Integer.toString(id),Integer.toString(quantity));
        editor.apply();

    }
}
