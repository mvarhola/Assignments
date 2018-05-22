package com.cs442.mvarhola.l6foodorderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;


import com.cs442.mvarhola.l6foodorderapp.data.Order;

import java.util.List;


public class AdminOrderHistory extends AppCompatActivity {

    private List<Order> orders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_history);

        AdminDatabaseSQLiteHelper db = new AdminDatabaseSQLiteHelper(this);

        orders = db.getAllOrders();

        Log.d("orders:",orders.toString());

        ListView lvItems = (ListView) this.findViewById(R.id.orderhistory);

        CustomAdminHistoryListAdapter itemsAdapter = new CustomAdminHistoryListAdapter(this, R.layout.activity_admin_order_history, orders);

        lvItems.setAdapter(itemsAdapter);




    }



}
