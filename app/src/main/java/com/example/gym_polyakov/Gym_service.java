package com.example.gym_polyakov;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.gym_polyakov.ui.ProfileFragment;

public class Gym_service extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    MyTask task = new MyTask();

    class MyTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            while (ProfileFragment.switch_bool) {
                try {
                    Thread.sleep(300000);
                    if(ProfileFragment.switch_bool){

                        Notification n  = new Notification.Builder(getBaseContext())
                                .setContentTitle("New mail from " + "test@gmail.com")
                                .setContentText("Subject")
                                .setAutoCancel(true).build();


                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                        notificationManager.notify(0, n);
                    }
                } catch (Exception e) {
                    Log.e("TASK_ERROR", e.toString());
                }
            }
            return null;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "Сервисы активированы", Toast.LENGTH_SHORT).show();
        task.execute();
        return START_STICKY;
    }
}
