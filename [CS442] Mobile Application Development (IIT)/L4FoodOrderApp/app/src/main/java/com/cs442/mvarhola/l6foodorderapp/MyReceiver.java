package com.cs442.mvarhola.l6foodorderapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    public static final String ACTION_REFRESH_ALARM =
            "com.cs442.mvarhola.l6foodorderapp.ACTION_REFRESH_ALARM";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Intent startIntent = new Intent(context, MyIntentService.class);
        context.startService(startIntent);

    }

}
