package com.example.gym_polyakov.steps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gym_polyakov.R;

public class Step2 extends AppCompatActivity {
    public static Boolean bool_female = false;
    public static Boolean bool_male = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);
        final View female = findViewById(R.id.button_female);
        final View male = findViewById(R.id.button_male);
        View next_step2 = findViewById(R.id.button_next_step2);
        SharedPreferences preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bool_male) {
                    bool_male = false;
                }
                bool_female = true;
                female.setBackgroundResource(R.drawable.custom_step2);
                male.setBackgroundResource(R.drawable.custom_step);
            }
        });

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bool_female) {
                    bool_female = false;
                }
                bool_male = true;
                female.setBackgroundResource(R.drawable.custom_step);
                male.setBackgroundResource(R.drawable.custom_step2);
            }
        });

        next_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bool_female) {
                    startActivity(new Intent(getApplicationContext(), Step3.class));
                    editor.putBoolean("male", false);
                    editor.apply();
                    finish();
                } else if (bool_male) {
                    startActivity(new Intent(getApplicationContext(), Step3_1.class));
                    editor.putBoolean("male", true);
                    editor.apply();
                    finish();
                } else
                    Toast.makeText(getApplicationContext(), "Пол не выбран", Toast.LENGTH_SHORT).show();
            }
        });
    }
}