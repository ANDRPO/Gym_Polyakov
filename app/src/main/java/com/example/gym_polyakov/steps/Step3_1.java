package com.example.gym_polyakov.steps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gym_polyakov.R;

public class Step3_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3_1);
        ImageView photo_man = findViewById(R.id.photo_man_step3);
        photo_man.animate().translationXBy(-275).setDuration(700);
        Button hands = findViewById(R.id.b_hands_step3_1);
        Button spine = findViewById(R.id.b_spine_step3_1);
        Button torso = findViewById(R.id.b_torso_step3_1);
        Button legs = findViewById(R.id.b_legs_step3_1);

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
