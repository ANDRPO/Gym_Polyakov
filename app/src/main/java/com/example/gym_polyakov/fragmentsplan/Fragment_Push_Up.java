package com.example.gym_polyakov.fragmentsplan;

import android.os.Bundle;
import android.os.Handler;
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

import com.example.gym_polyakov.R;

public class Fragment_Push_Up extends Fragment {
    private Chronometer chronometer;
    private int count = 0;
    boolean check = true;
    boolean stop = false;
    TextView tv_scores;
    TextView tv_minutes;
    TextView tv_kcal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_push_up, null);
        tv_scores = view.findViewById(R.id.tv_scores_push_up);
        tv_minutes = view.findViewById(R.id.tv_minutes_push_up);
        tv_kcal = view.findViewById(R.id.tv_kcal_push_up);
        Button Tap = view.findViewById(R.id.b_tap_push_up);
        Button Stop = view.findViewById(R.id.b_stop_push_up);

        Tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (check) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {

                                chronometer.start();
                            do {
                                Log.e("CHRONOMETER", String.valueOf(chronometer.getBase()));
                            } while (!stop);

                        }
                    });
                }
            }
        });

        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop = true;
            }
        });

        return view;
    }
}
