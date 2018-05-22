package com.cs442.mvarhola.l6foodorderapp;

import android.provider.BaseColumns;

/**
 * Created by p0rt on 10/8/17.
 */

public class AdminDatabaseContract {

    private AdminDatabaseContract() {
    }

    public static class Orders implements BaseColumns {
        public static final String TABLE_NAME = "orders";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_ITEMS = "items";
        public static final String COLUMN_TOTAL = "total";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TIMESTAMP + " TEXT, " +
                COLUMN_ITEMS + " TEXT, " +
                COLUMN_TOTAL + " TEXT" + ")";
    }


}
