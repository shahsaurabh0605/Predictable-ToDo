package com.example.good.todo_app;

/**
 * Created by good on 25/12/15.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class TimeAlarm
        extends BroadcastReceiver{
    Calendar cal= Calendar.getInstance();


    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager nm=null;

        nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);


        PendingIntent contentIntent = PendingIntent.getActivity(context, intent.getExtras().getInt("ID"),
                intent, 0);


        Notification notif = new Notification(android.R.drawable.ic_dialog_alert,
                "Easy Lists",System.currentTimeMillis());

        notif.defaults |= Notification.DEFAULT_SOUND;
        notif.flags |= Notification.FLAG_AUTO_CANCEL;
      //  notif.setLatestEventInfo(context, "Lists app",intent.getExtras().getString("NOTIFICATION"), contentIntent);
        notif.when=intent.getExtras().getLong("LONG");

        nm.notify(intent.getExtras().getInt("ID"), notif);


        //  context.startActivity(new Intent(context, ReservationListing.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }


}
