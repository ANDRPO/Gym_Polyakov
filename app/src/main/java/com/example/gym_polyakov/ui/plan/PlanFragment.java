package com.example.gym_polyakov.ui.plan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gym_polyakov.OnSwipeTouchListener;
import com.example.gym_polyakov.R;

public class PlanFragment extends Fragment {

    private View container_from_top_animate;
    private View container_from_visivility;
    private boolean swipeup = false;
    private ImageView b_hands;
    private ImageView b_spine;
    private ImageView b_torso;
    private ImageView b_legs;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plan, null);

        container_from_top_animate = view.findViewById(R.id.view_container_profile_HSTL);
        container_from_visivility = view.findViewById(R.id.fragment_off);
        b_hands = view.findViewById(R.id.b_hands);
        b_spine = view.findViewById(R.id.b_spine);
        b_torso = view.findViewById(R.id.b_torso);
        b_legs = view.findViewById(R.id.b_legs);

        b_hands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        b_spine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        b_torso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        b_legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        container_from_top_animate.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeDown() {
                if (swipeup) {
                    Log.e("POSITION_X", String.valueOf(container_from_top_animate.getX()));
                    Log.e("POSITION_Y", String.valueOf(container_from_top_animate.getY()));
                    if (container_from_top_animate.getY() == 190.0) {
                        swipeup = false;
                        container_from_top_animate.animate().translationYBy(200).setDuration(400);
                        container_from_visivility.animate().alpha(1).setDuration(500);
                    }
                }
                Log.e("BALDESH", "DOWN");
            }

            @Override
            public void onSwipeUp() {
                if (!swipeup) {

                    Log.e("POSITION_X", String.valueOf(container_from_top_animate.getX()));
                    Log.e("POSITION_Y", String.valueOf(container_from_top_animate.getY()));
                    if (container_from_top_animate.getY() == 390.0) {
                        swipeup = true;
                        container_from_top_animate.animate().translationYBy(-200).setDuration(400);
                        container_from_visivility.animate().alpha(0).setDuration(500);
                    }
                }
                Log.e("BALDESH", "UP");
            }
        });
        return view;
    }
}