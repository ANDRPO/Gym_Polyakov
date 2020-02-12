package com.example.gym_polyakov.fragmentsplan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gym_polyakov.MyServiceTR;
import com.example.gym_polyakov.R;
import com.example.gym_polyakov.ui.PlanFragment;

public class Fragment_Push_Up extends Fragment {
    private Chronometer chronometer;
    private int count;
    private int progress;
    private int kcal;
    private boolean check = true;
    private TextView tv_scores;
    private TextView tv_kcal;

    Fragment_Push_Up(int progress) {
        this.count = progress + 1;
        this.progress = progress + 1;
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_push_up, null);
        tv_scores = view.findViewById(R.id.tv_scores_push_up);
        tv_kcal = view.findViewById(R.id.tv_kcal_push_up);
        final Button Tap = view.findViewById(R.id.b_tap_push_up);
        final Button Stop = view.findViewById(R.id.b_stop_push_up);
        chronometer = view.findViewById(R.id.chronometer_push_up);
        tv_scores.setText(count + "\nScores");

        Tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CHRONOMETR", String.valueOf(SystemClock.elapsedRealtime() - chronometer.getBase()));
                if (check) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    check = false;
                }
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
        });

        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                final Button b_success = view.findViewById(R.id.b_success);
                b_success.setTextSize(70);
                Tap.animate().alpha(0).setDuration(600);
                view.findViewById(R.id.container_button_push_up).animate().translationYBy(-70).setDuration(600);
                Stop.animate().alpha(0).setDuration(600);
                view.findViewById(R.id.gif_push_up).animate().alpha(0).setDuration(500);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Tap.setVisibility(View.INVISIBLE);
                        b_success.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Editor_shared("minutes", (int) (getTimeChronometer(chronometer) / 60000));
                                Editor_shared("kcal", kcal);
                                Editor_shared("score", progress - count);
                                Editor_shared("PUSHUP", progress);
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
