package com.example.gym_polyakov.steps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gym_polyakov.R;

public class Step4 extends AppCompatActivity {

    public Boolean advanced_bool = false;
    public Boolean keep_on_bool = false;
    public Boolean newbie_bool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step4);

        final View b_keep_on = findViewById(R.id.step4_keep_on);
        final View b_newbie = findViewById(R.id.step4_newbie);
        final View b_advanced = findViewById(R.id.step4_advanced);
        View b_next = findViewById(R.id.button_next_step4);

        b_keep_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advanced_bool = false;
                newbie_bool = false;
                keep_on_bool = true;
                b_keep_on.setBackgroundResource(R.drawable.custom_step2);
                b_advanced.setBackgroundResource(R.drawable.custom_step);
                b_newbie.setBackgroundResource(R.drawable.custom_step);
            }
        });

        b_newbie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advanced_bool = false;
                newbie_bool = true;
                keep_on_bool = false;
                b_keep_on.setBackgroundResource(R.drawable.custom_step);
                b_advanced.setBackgroundResource(R.drawable.custom_step);
                b_newbie.setBackgroundResource(R.drawable.custom_step2);
            }
        });

        b_advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advanced_bool = true;
                newbie_bool = false;
                keep_on_bool = false;
                b_keep_on.setBackgroundResource(R.drawable.custom_step);
                b_advanced.setBackgroundResource(R.drawable.custom_step2);
                b_newbie.setBackgroundResource(R.drawable.custom_step);
            }
        });

        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(advanced_bool || newbie_bool || keep_on_bool){
                    startActivity(new Intent(getApplicationContext(), Step5.class));
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Значение не выбрано", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
