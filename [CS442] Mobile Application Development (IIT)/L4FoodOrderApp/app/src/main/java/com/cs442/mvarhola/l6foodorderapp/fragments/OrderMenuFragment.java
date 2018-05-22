package com.cs442.mvarhola.l6foodorderapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.cs442.mvarhola.l6foodorderapp.AdminDatabaseSQLiteHelper;
import com.cs442.mvarhola.l6foodorderapp.DatabaseSQLiteHelper;
import com.cs442.mvarhola.l6foodorderapp.MainActivity;
import com.cs442.mvarhola.l6foodorderapp.MyIntentService;
import com.cs442.mvarhola.l6foodorderapp.R;
import com.cs442.mvarhola.l6foodorderapp.data.Food;
import com.cs442.mvarhola.l6foodorderapp.data.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class OrderMenuFragment extends Fragment implements View.OnClickListener {


    int position = 0;

    Button orderButton;
    Button resetButton;
    TextView totalAmount;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            if(getArguments() != null) {
                position = getArguments().getInt("position", 0);
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_order_menu, parent, false);

        orderButton = (Button) rootView.findViewById(R.id.order_button);
        resetButton = (Button) rootView.findViewById(R.id.reset_order_button);
        totalAmount = (TextView) rootView.findViewById(R.id.textView4);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TEST:","clicked!");
                Intent startIntent = new Intent(getContext(), MyIntentService.class);
                getContext().startService(startIntent);
            }
        });
        resetButton.setOnClickListener(this);

        return rootView;
    }

    public void update(){
        totalAmount.setText(Double.toString(calcTotal()));
    }


    public void onResume(){
        super.onResume();
        update();
    }



    public double calcTotal(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        DatabaseSQLiteHelper db1 = new DatabaseSQLiteHelper(getContext());

        List<Food> foods = db1.getAllFood();

        double total = 0.00;

        for (int i = 0; i < foods.size(); i++) {
            double cost = Double.parseDouble(foods.get(i).getPrice());
            double amount = 0.00;

            if (preferences.contains(Integer.toString(i))){
                amount = Integer.parseInt(preferences.getString(Integer.toString(i), ""));
            }else{
                amount = 0.00;
            }
            total += cost*amount;
        }

        return total;
    }

    @Override
    public void onClick(View rootView) {
//        switch (rootView.getId()) {
//            case R.id.order_button:
//                onPlaceOrder();

               /* break;
            case R.id.reset_order_button:
                onResetOrder();
                break;*/
//        }
    }

    public void clearPreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public void onPlaceOrder(){

        DatabaseSQLiteHelper db1 = new DatabaseSQLiteHelper(getContext());
        AdminDatabaseSQLiteHelper db2 = new AdminDatabaseSQLiteHelper(getContext());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        List<Food> foods = db1.getAllFood();

        Order order = new Order();
        String fooditems = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy @ HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());

        for (int i = 0; i < foods.size(); i++) {

            String foodname = foods.get(i).getName();
            String foodamount = preferences.getString(Integer.toString(i),"");



            if (!foodamount.equals("") && Integer.parseInt(foodamount) > 0){
                fooditems += foodamount + " " + foodname + "\n";
            }
        }

        order.setTimestamp(currentDateandTime);
        order.setItems(fooditems);
        order.setTotal(Double.toString(calcTotal()));

        db2.addOrder(order);


//        pushEventToCalendar(getContext(),"ORDER","Order Is Ready!");

//        Toast.makeText(getActivity(),"Order placed!",Toast.LENGTH_SHORT).show();
        clearPreferences();

        onResume();
    }




    public void onResetOrder(){

        Toast.makeText(getActivity(),"Order Reset!",Toast.LENGTH_SHORT).show();
        clearPreferences();
        onResume();

    }

    private void pushEventToCalendar(Context context, String title, String description) {

//        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//        String startDate = sdf.format(new Date());
//
//        ContentValues event = new ContentValues();
//        event.put("calendar_id", 1);
//        event.put("title", title);
//        event.put("description", description);
//        event.put("eventTimezone", TimeZone.getDefault().getID());
//        event.put("dtstart", startDate);
//
//        event.put("allDay", 0); // 0 for false, 1 for true
//        event.put("eventStatus", 1);
//        event.put("hasAlarm", 1); // 0 for false, 1 for true
//
//        String eventUriString = "content://com.android.calendar/events";
//        Uri eventUri = context.getApplicationContext()
//                .getContentResolver()
//                .insert(Uri.parse(eventUriString), event);
//        long eventID = Long.parseLong(eventUri.getLastPathSegment());

        Toast.makeText(getActivity(),"Calendar Entry Made!",Toast.LENGTH_SHORT).show();
/*
        return eventID;*/
    }



}
