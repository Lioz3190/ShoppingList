package com.example.lioz.shoppinglist;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;


import com.example.lioz.shoppinglist.Notify.NotificationPublisher;
//import com.example.lioz.shoppinglist.Notify.NotificationReceiverActivity;
import com.example.lioz.shoppinglist.fragments.DatePickerFragment;
import com.example.lioz.shoppinglist.fragments.TimePickerFragment;

import java.util.Calendar;

/**
 * Created by VALENTIN on 10/11/2016.
 */

public class AlertDate extends AppCompatActivity implements DatePickerFragment.onDatePass, TimePickerFragment.onTimePass {



    private Calendar alarm = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_date);
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void scheduleNotification(View v) {

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Anniversaire Liste des courses");
        builder.setContentText("Pensez à faire vos courses !");
        builder.setSmallIcon(R.drawable.ic_shopping_cart_black_24dp);

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, builder.build());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis =  SystemClock.elapsedRealtime() +(alarm.getTimeInMillis() - Calendar.getInstance().getTimeInMillis());
        System.out.println(futureInMillis);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);

        finish();

    }


    @Override
    public void onDatePass(String data) {
        String[] date = data.split("/");
        String year = date[0];
        String month = date[1];
        String day = date[2];
        alarm.set(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
    }

    @Override
    public void onTimePass(String data) {
        String[] time= data.split("/");
        String hour = time[0];
        String minute = time[1];
        alarm.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hour));
        alarm.set(Calendar.MINUTE,Integer.parseInt(minute));
        alarm.set(Calendar.SECOND,0);
    }




    /*public void createNotification(View view) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_shopping_cart_black_24dp)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }*/


}
