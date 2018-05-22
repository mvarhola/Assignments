package com.cs442.mvarhola.l6foodorderapp;

/**
 * Created by p0rt on 10/8/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cs442.mvarhola.l6foodorderapp.data.Order;

import java.util.LinkedList;
import java.util.List;


public class AdminDatabaseSQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "OrderDB";

    public AdminDatabaseSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(AdminDatabaseContract.Orders.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AdminDatabaseContract.Orders.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) Food + get all Food + delete all Food
     */

    // Food table name
    private static final String TABLE_ORDERS = "orders";

    // Food Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_ITEMS = "items";
    private static final String KEY_TOTAL= "total";

    public String [] COLUMNS = {KEY_ID,KEY_TIMESTAMP,KEY_ITEMS,KEY_TOTAL};

    public void addOrder(Order order){
        Log.d("addOrder", order.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TIMESTAMP, order.getTimestamp()); // get timestamp
        values.put(KEY_ITEMS, order.getItems()); // get items
        values.put(KEY_TOTAL, order.getTotal()); // get total price

//        Log.d("addOrder ContentValues: ",values.toString());

        // 3. insert
        db.insert(TABLE_ORDERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }




    // Get All Food
    public List<Order> getAllOrders() {
        List<Order> orders = new LinkedList<Order>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ORDERS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build order and add it to list
        Order order= null;
        if (cursor.moveToFirst()) {
            do {
                order = new Order();

                Log.d("fooddatabase:    ", String.valueOf(cursor.getLong(cursor.getColumnIndex("_id"))));

                order.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                order.setTimestamp(cursor.getString(cursor.getColumnIndex("timestamp")));
                order.setItems(cursor.getString(cursor.getColumnIndex("items")));
                order.setTotal(cursor.getString(cursor.getColumnIndex("total")));

                orders.add(order);
            } while (cursor.moveToNext());
        }

        Log.d("getAllFood()", orders.toString());

        // return orders
        return orders;
    }

    public Order getOrder(int id){

        List<Order> orders = getAllOrders();
        Order order = orders.get(id);

        Log.d("getOrder("+id+")", order.toString());

        // 5. return Food
        return order;
    }



    // Deleting all orders
    public void deleteAllOrders() {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_ORDERS,null,null);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_ORDERS + "'");

        // 3. close
        db.close();

        Log.d("deleteAllOrders:"," food deleted");

    }



}