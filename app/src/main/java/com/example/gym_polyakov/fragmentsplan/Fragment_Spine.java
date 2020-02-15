package com.example.gym_polyakov.fragmentsplan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gym_polyakov.MyServiceTR;
import com.example.gym_polyakov.R;
import com.example.gym_polyakov.ui.PlanFragment;

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
    private Button b_stop;
    private boolean[] step = new boolean[]{false, false};

    ImageView strelka;

    Fragment_Spine(int progress) {
        this.count = progress + 1;
        this.progress = progress + 1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_spine_plan, container, false);

        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        tv_scores = view.findViewById(R.id.tv_scores_spine);
        tv_kcal = view.findViewById(R.id.tv_kcal_spine);

        b_stop = view.findViewById(R.id.b_stop_spine);

        chronometer = view.findViewById(R.id.chronometer_spine);
        strelka = view.findViewById(R.id.im_spine_strelka);

        final View container_for_strelka = view.findViewById(R.id.container_sensor);

        tv_scores.setText(count + "\nScores");

        b_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                container_for_strelka.animate().alpha(0).setDuration(500);
                final TextView b_success = view.findViewById(R.id.b_success_spine);
                b_success.setTextSize(70);
                b_stop.animate().alpha(0).setDuration(600);
                view.findViewById(R.id.gif_spine).animate().alpha(0).setDuration(500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b_success.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Editor_shared("minutes", (int) (getTimeChronometer(chronometer) / 60000));
                                Editor_shared("kcal", kcal);
                                Editor_shared("score", progress - count);
                                Editor_shared_spine("SPINE", progress);
                                getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putInt("TRAINING_CHECK_TIME", getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt("TRAINING_TIME", 0)).apply();
                                getActivity().startService(new Intent(getContext(), MyServiceTR.class));
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new PlanFragment()).commit();
                            }
                        });
                    }
                }, 600);
            }
        });
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
        Log.e("SPINEX", "X:" + event.values[0]);
        Log.e("SPINEY", "Y:" + event.values[1]);
        Log.e("SPINEZ", "Z:" + event.values[2]);
        if (event.values[1] > 0 && event.values[0] * 10.5f < 90 && event.values[0] * 10.5f > -90 && event.values[2] < 6f && event.values[2] > -6f) {
            strelka.setRotation(event.values[0] * 10.5f);
            if (event.values[0] * 10.5f  > 86) {
                strelka.setRotation(90);
                if (!step[0]) {
                    step[0] = true;
                    step[1] = false;
                    downCount();
                }
            } else if (event.values[0] * 10.5f < -86) {
                strelka.setRotation(-90);
                if (!step[1]) {
                    step[1] = true;
                    step[0] = false;
                    downCount();
                }
            }
        } else if (event.values[1] > -1 && (event.values[0] * 10.5f > 90 || event.values[0] * 10.5f < -90) && event.values[2] < 6f && event.values[2] > -6f) {
            if (event.values[0] > 0) {
                strelka.setRotation(90);
                if (!step[0]) {
                    step[0] = true;
                    step[1] = false;
                    downCount();
                }
            } else if (event.values[0] < 0) {
                strelka.setRotation(-90);
                if (!step[1]) {
                    step[1] = true;
                    step[0] = false;
                    downCount();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void downCount() {
        if (count != 0) {
            count--;
            tv_scores.setText(count + "\nScores");
            if (count != 0) {
                tv_kcal.setText((int) (getTimeChronometer(chronometer) / 60000 / count) + "\nKcal");
                kcal = (int) ((chronometer.getBase() / 60000) / count);
            } else {
                tv_kcal.setText((int) ((getTimeChronometer(chronometer) / 60000)) + "\nKcal");
                kcal = (int) ((getTimeChronometer(chronometer) / 60000));
            }
        }
    }

    public long getTimeChronometer(Chronometer chronometer) {
        return SystemClock.elapsedRealtime() - chronometer.getBase();
    }

    public int Return_shared(String a) {
        return getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt(a, 0);
    }

    public void Editor_shared(String a, int b) {
        getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putInt(a, Return_shared(a) + b).apply();
    }
    public void Editor_shared_spine(String a, int b) {
        getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putInt(a, b).apply();
    }
}
