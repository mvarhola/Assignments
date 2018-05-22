package com.cs442.mvarhola.l6foodorderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cs442.mvarhola.l6foodorderapp.data.Food;
import com.cs442.mvarhola.l6foodorderapp.data.Order;

import java.util.List;

public class AdminMenu extends AppCompatActivity implements View.OnClickListener{

    private List<Food> foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        DatabaseSQLiteHelper db = new DatabaseSQLiteHelper(this);

        foods = db.getAllFood();

        ListView lvItems = (ListView) this.findViewById(R.id.listView);

        CustomAdminFoodListAdapter itemsAdapter = new CustomAdminFoodListAdapter(this, R.layout.item_admin_layout, foods);

        lvItems.setAdapter(itemsAdapter);

        Button addItemButton = (Button)findViewById(R.id.add_item_button);
        Button orderHistButton = (Button)findViewById(R.id.order_hist_button);
        Button statusButton = (Button)findViewById(R.id.status_button);
        Button calendarButton = (Button)findViewById(R.id.calendar_button);

        addItemButton.setOnClickListener(this);
        orderHistButton.setOnClickListener(this);
        statusButton.setOnClickListener(this);
        calendarButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View rootView) {
        Intent intent;
        switch (rootView.getId()) {
            case R.id.add_item_button:
                intent = new Intent(this, AdminAddItem.class);
                startActivity(intent);
                break;
            case R.id.order_hist_button:
                intent = new Intent(this, AdminOrderHistory.class);
                startActivity(intent);
                break;
            case R.id.status_button:
                showReport();
                break;
            case R.id.calendar_button:
                Toast.makeText(this,"TO BE IMPLEMENTED",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void showReport(){
        AdminDatabaseSQLiteHelper db = new AdminDatabaseSQLiteHelper(this);

        double totalAmount = 0.00;
        int totalOrders = 0;

        List<Order> orders = db.getAllOrders();

        for (Order order: orders) {
            totalAmount += Double.parseDouble(order.getTotal());
            totalOrders += 1;
        }

        String testString = "Here is the report:\n"+"Total earnings: "+Double.toString(totalAmount)+"\n"+"Number of orders: "+Integer.toString(totalOrders);

        Toast.makeText(this,testString,Toast.LENGTH_LONG).show();

    }

}
