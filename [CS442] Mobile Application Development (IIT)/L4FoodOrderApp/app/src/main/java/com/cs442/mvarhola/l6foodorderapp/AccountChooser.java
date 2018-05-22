package com.cs442.mvarhola.l6foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cs442.mvarhola.l6foodorderapp.data.Food;

/**
 * Created by p0rt on 10/6/17.
 */

public class AccountChooser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_chooser);

        DatabaseSQLiteHelper db = new DatabaseSQLiteHelper(this);
        AdminDatabaseSQLiteHelper db2 = new AdminDatabaseSQLiteHelper(this);

        db.deleteAllFood();
        db2.deleteAllOrders();

        //Add food items
        db.addFood(new Food("Margherita Pizza","19.99","Pizza Margherita is a typical Neapolitan pizza, made with San Marzano tomatoes, mozzarella fior di latte, fresh basil, salt and extra-virgin olive oil."));
        db.addFood(new Food("White Pizza","13.99","White Pizza is typically covered in ricotta and mozzarella instead of tomato sauce, and it is also be topped with garlic, vegetables, clams, and Alfredo sauce."));
        db.addFood(new Food("Grandma Pizza","16.99","Grandma pie is square or rectangular pizza that has been cooked in an olive oil-coated pan. It's covered in a thin layer of mozzarella cheese, and in uncooked canned or fresh tomatoes, and when it comes out of the oven, the thick crust is a little crispy."));
        db.addFood(new Food("Deep Dish Pizza","22.99","The pan in which it is baked gives the pizza its characteristically high edge and a deep surface for large amounts of cheese and a chunky tomato sauce."));
        db.addFood(new Food("Toast sandwich","0.99","A toast sandwich is a sandwich made with two thin slices of bread in which the filling is a thin slice of buttered toast. An 1861 recipe says to add salt and pepper to taste."));


    }

    public void onCustomerButtonClicked(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onAdminButtonClicked(View view){
        Intent intent = new Intent(this, AdminMenu.class);
        startActivity(intent);
    }

    public void onResetButtonClicked(View view){
        Intent intent = new Intent(this, AdminMenu.class);
        startActivity(intent);
    }

}
