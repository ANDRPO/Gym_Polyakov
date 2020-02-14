package com.example.gym_polyakov.fragmentsplan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gym_polyakov.R;

public class Fragment_Spine extends Fragment implements SensorEventListener {

    private Sensor sensor;
    private SensorManager sensorManager;

    private Chronometer chronometer;

    private int count;
    private int progress;
    private int kcal;


    private boolean check = true;

    private TextView tv_scores;
    private TextView tv_kcal;
    private boolean[] step = new boolean[]{false, false};

    ImageView strelka;

    Fragment_Spine(int progress) {
        this.count = progress + 1;
        this.progress = progress + 1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spine_plan, container, false);

        chronometer = view.findViewById(R.id.chronometer_spine);
        strelka = view.findViewById(R.id.im_spine_strelka);
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        return view;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (check) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            check = false;
        }
        Log.e("SPINEX" ,"X:" + event.values[0]);
        Log.e("SPINEY" ,"Y:" + event.values[1]);
        Log.e("SPINEZ" ,"Z:" + event.values[2]);
        if (event.values[1] > 0 && event.values[0] * 10.5f < 90 && event.values[0] * 10.5f > -90 && event.values[2] < 6f && event.values[2] > -6f) {
            strelka.setRotation(event.values[0] * 10.5f);
        }
        else if (event.values[1] > -1 && (event.values[0] * 10.5f > 90 || event.values[0] * 10.5f < -90) && event.values[2] < 6f && event.values[2] > -6f) {
            if (event.values[0] > 0) {
                strelka.setRotation(90);
                if (!step[0]) {
                    step[0] = true;
                    step[1] = false;
                    downCount();
                }
            }
            else if (event.values[0] < 0)
                strelka.setRotation(-90);
                if(!step[1]){
                    step[1] = true;
                    step[0] = false;
                    downCount();
                }
        }
        else{
            Toast.makeText(getActivity(), "Делай сука нормально блять, дрищ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void downCount(){
        if (count != 0) {
            count--;
            tv_scores.setText(count + "\nScores");
            if (count != 0) {
                tv_kcal.setText((int) (getTimeChronometer(chronometer) / 60000 / count) + "\nKcal");
                kcal = (int)((chronometer.getBase() / 60000) / count);
            } else {
                tv_kcal.setText((int) ((getTimeChronometer(chronometer) / 60000)) + "\nKcal");
                kcal = (int)((getTimeChronometer(chronometer) / 60000));
            }
        }
    }

    public long getTimeChronometer(Chronometer chronometer){
        return SystemClock.elapsedRealtime() -   chronometer.getBase();
    }

    public int Return_shared(String a) {
        return getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt(a, 0);
    }

    public void Editor_shared(String a, int b) {
        getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putInt(a, Return_shared(a) + b).apply();
    }
}
