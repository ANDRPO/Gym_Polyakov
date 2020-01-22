package com.example.gym_polyakov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class Step1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);
        View b_1_step1 = findViewById(R.id.step1_build_muscule);
        View b_2_step1 = findViewById(R.id.step1_keeping_fit);
        View b_3_step1 = findViewById(R.id.step1_weight_loss);

        b_1_step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Step2.class));
                finish();
            }
        });

        b_2_step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Step2.class));
                finish();
            }
        });

        b_3_step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Step2.class));
                finish();
            }
        });

    }
}
