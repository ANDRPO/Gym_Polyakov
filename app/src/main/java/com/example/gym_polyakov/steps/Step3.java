package com.example.gym_polyakov.steps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gym_polyakov.R;

public class Step3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3);
        ImageView imageView = findViewById(R.id.photo_female_step3);
        imageView.animate().translationXBy(-275).setDuration(700);
        Button hands = findViewById(R.id.b_hands_step3);
        Button spine = findViewById(R.id.b_spine_step3);
        Button torso = findViewById(R.id.b_torso_step3);
        Button legs = findViewById(R.id.b_legs_step3);

        hands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Step4.class));
                finish();
            }
        });

        spine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Step4.class));
                finish();
            }
        });

        torso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Step4.class));
                finish();
            }
        });

        legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Step4.class));
                finish();
            }
        });
    }
}