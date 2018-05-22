package com.cs442.mvarhola.l6foodorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cs442.mvarhola.l6foodorderapp.data.Food;

import java.util.LinkedList;
import java.util.List;

public class DatabaseSQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FoodDB";

    public DatabaseSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseContract.Foods.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Foods.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) Food + get all Food + delete all Food
     */

    // Food table name
    private static final String TABLE_FOOD = "foods";

    // Food Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_DESC= "description";

    public String [] COLUMNS = {KEY_ID,KEY_NAME,KEY_PRICE,KEY_DESC};

    public void addFood(Food food){
        Log.d("addFood", food.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName()); // get title
        values.put(KEY_PRICE, food.getPrice()); // get price
        values.put(KEY_DESC, food.getDescription()); // get description

//        Log.d("addFood ContentValues: ",values.toString());

        // 3. insert
        db.insert(TABLE_FOOD, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }




    // Get All Food
    public List<Food> getAllFood() {
        List<Food> foods = new LinkedList<Food>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_FOOD;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build Food and add it to list
        Food food = null;
        if (cursor.moveToFirst()) {
            do {
                food = new Food();

//                Log.d("fooddatabase:    ", String.valueOf(cursor.getLong(cursor.getColumnIndex("_id"))));

                food.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                food.setName(cursor.getString(cursor.getColumnIndex("name")));
                food.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                food.setDescription(cursor.getString(cursor.getColumnIndex("description")));


                // Add Food to Food
                foods.add(food);
            } while (cursor.moveToNext());
        }

        Log.d("getAllFood()", foods.toString());

        // return foods
        return foods;
    }

    public Food getFood(int id){

        List<Food> foods = getAllFood();
        Food food = foods.get(id);

        Log.d("getFood("+id+")", food.toString());

        // 5. return Food
        return food;
    }

    // Updating single Food
    public int updateFood(Food food) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", food.getName()); // get title
        values.put("price", food.getPrice()); // get author
        values.put("description", food.getDescription()); // get author

        // 3. updating row
        int i = db.update(TABLE_FOOD, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(food.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single Food
    public void deleteFood(Food food) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_FOOD,
                KEY_ID+" = ?",
                new String[] { String.valueOf(food.getId()) });

        // 3. close
        db.close();

        Log.d("deleteFood", food.toString());

    }

    // Deleting all Food
    public void deleteAllFood() {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_FOOD,null,null);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_FOOD + "'");

        // 3. close
        db.close();

        Log.d("deleteAllFood"," food deleted");

    }



}