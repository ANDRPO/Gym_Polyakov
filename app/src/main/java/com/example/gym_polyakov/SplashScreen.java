package com.example.gym_polyakov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getSharedPreferences("Settings", Context.MODE_PRIVATE).getBoolean("first", false)) {
                    startActivity(new Intent(getApplicationContext(), SignIn.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), Step1.class));
                    finish();
                }

            }
        }, 2000);
    }
}
