package com.example.gym_polyakov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Step5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step5);

        View next_step5 = findViewById(R.id.button_next_step5);
        final EditText et_h = findViewById(R.id.et_step5_Height);
        final EditText et_w = findViewById(R.id.et_step5_Weight);

        SharedPreferences preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        next_step5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_h.getText().toString().isEmpty() && !et_w.getText().toString().isEmpty()) {
                    editor.putBoolean("first", true).apply();
                    editor.putFloat("weight", Float.valueOf(et_h.getText().toString())).apply();
                    editor.putFloat("weight", Float.valueOf(et_w.getText().toString())).apply();
                    startActivity(new Intent(getApplicationContext(), SignIn.class));
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Value not selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
