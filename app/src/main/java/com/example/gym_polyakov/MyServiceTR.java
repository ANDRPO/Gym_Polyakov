package com.example.gym_polyakov;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyServiceTR extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    class MyTaskTR extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            do {
                if(getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt("TRAINING_CHECK_TIME", 0) != 0){
                    try {
                        Thread.sleep(1000);
                        getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putInt("TRAINING_CHECK_TIME", getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt("TRAINING_CHECK_TIME", 0)-1).apply();
                        Log.e("TRTASK",String.valueOf(getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt("TRAINING_CHECK_TIME", 0)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } while (getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt("TRAINING_CHECK_TIME", 0) != 0);
            return null;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MYTASKTR", "ACTIVATED");
        MyTaskTR myTaskTR = new MyTaskTR();
        myTaskTR.execute();
        return START_STICKY;
    }
}
