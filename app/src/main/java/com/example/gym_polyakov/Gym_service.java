package com.example.gym_polyakov;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.gym_polyakov.ui.ProfileFragment;

import java.util.concurrent.TimeUnit;

public class Gym_service extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private final String CHANNEL_ID = "NotificationChannelService";

    @SuppressLint("StaticFieldLeak")
    class MyTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            Log.e("CHECKTASK", String.valueOf(getApplication().getApplicationContext().getSharedPreferences("Settings", Context.MODE_PRIVATE).getBoolean("checkN", false)));

            while (getApplicationContext().getSharedPreferences("Settings", Context.MODE_PRIVATE).getBoolean("checkN", false)) {
                try {
                    Thread.sleep(3000);
                    if(getApplicationContext().getSharedPreferences("Settings", Context.MODE_PRIVATE).getBoolean("checkN", false)){
                        NotificationCompat.Builder builder =new NotificationCompat.Builder(Gym_service.this, CHANNEL_ID)
                                .setContentTitle("Gym")
                                .setContentText("Training")
                                .setSmallIcon(R.mipmap.icon)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                .setAutoCancel(true);
                        createNotificationChannel();

                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                        // notificationId is a unique int for each notification that you must define
                        notificationManager.notify(1, builder.build());
                    }
                } catch (Exception e) {
                    Log.e("TASK_ERROR", e.toString());
                }
            }
            return null;
        }

        private void createNotificationChannel() {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Gym", importance);
                channel.setDescription("Training");
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "Сервисы активированы", Toast.LENGTH_SHORT).show();
        MyTask task = new MyTask();
        task.execute();
        return START_STICKY;
    }
}
