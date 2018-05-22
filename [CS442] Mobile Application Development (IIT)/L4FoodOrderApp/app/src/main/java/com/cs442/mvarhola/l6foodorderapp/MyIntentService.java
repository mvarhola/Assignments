package com.cs442.mvarhola.l6foodorderapp;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


public class MyIntentService extends IntentService {

    private Notification.Builder foodNotificationBuilder;
    public static final int NOTIFICATION_ID = 1;
    SQLiteDatabase foodDB = null;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //To get the data for the intent

        //Extracting data from Cursor
        Log.d("Notification", "Inside onHandle Intent");


        // Trigger a notification.
        broadcastNotification("Success");

        Toast.makeText(this,"Order Success!",Toast.LENGTH_SHORT).show();
    }

    private void broadcastNotification(String orderHist){
        Log.d("Notification", "Inside Broadcast");
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        foodNotificationBuilder
                .setContentIntent(pendingIntent)
                .setContentTitle("Notification Time!")
                .setContentText("data is " + orderHist);

        double vibrateLength = 100*Math.exp(0.53*5);
        long[] vibrate = new long[] {100, 100, (long)vibrateLength };
        foodNotificationBuilder.setVibrate(vibrate);

        NotificationManager notificationManager
                = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID,
                foodNotificationBuilder.getNotification());
    }
    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d("Notification", "Inside StartCommand");
        int alarmType = AlarmManager.ELAPSED_REALTIME_WAKEUP;
        long timeToRefresh = SystemClock.elapsedRealtime() +
                5*60*1000;
        alarmManager.setInexactRepeating(alarmType, timeToRefresh,
                5*60*1000, alarmIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("Notification", "Inside onCreate");
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        String ALARM_ACTION;
        ALARM_ACTION =
                MyReceiver.ACTION_REFRESH_ALARM;
        Intent intentToFire = new Intent(ALARM_ACTION);
        alarmIntent =
                PendingIntent.getBroadcast(this, 0, intentToFire, 0);

        foodNotificationBuilder = new Notification.Builder(this);
        foodNotificationBuilder
                .setAutoCancel(true)
                .setTicker("Order Time!!!!")
                .setSmallIcon(R.mipmap.ic_launcher);
        super.onCreate();
    }
}
