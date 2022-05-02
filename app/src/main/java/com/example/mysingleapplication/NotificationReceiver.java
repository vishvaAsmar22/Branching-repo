package com.example.mysingleapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;


public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {



        Intent intent1 = new Intent(context,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,0);


       Notification notification = new NotificationCompat.Builder(context,"singleApp")
               .setSmallIcon(R.drawable.ic_launcher_background)
               .setContentTitle("Reminder..!!")
               .setContentText("here to complete your task")
               .setAutoCancel(true)
               .setDefaults(NotificationCompat.DEFAULT_ALL)
               .setPriority(NotificationCompat.PRIORITY_HIGH)
               .setContentIntent(pendingIntent)
               .build();

       NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
       notificationManagerCompat.notify(123,notification);






    }
}
