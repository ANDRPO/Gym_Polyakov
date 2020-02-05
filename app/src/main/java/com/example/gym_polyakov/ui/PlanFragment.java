package com.example.gym_polyakov.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
    private ImageView b_hands;
    private ImageView b_spine;
    private ImageView b_torso;
    private ImageView b_legs;

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plan, null);
        final boolean[] swipeup = {false};

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

        b_hands.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeDown() {
                if (swipeup[0]) {
                    Log.e("POSITION_X", String.valueOf(container_from_top_animate.getX()));
                    Log.e("POSITION_Y", String.valueOf(container_from_top_animate.getY()));
                    if (container_from_top_animate.getY() == 190.0) {
                        swipeup[0] = false;
                        container_from_top_animate.animate().translationYBy(200).setDuration(400);
                        container_from_visivility.animate().alpha(1).setDuration(500);
                    }
                }
                Log.e("BALDESH", "DOWN");
            }

            @Override
            public void onSwipeUp() {
                if (!swipeup[0]) {

                    Log.e("POSITION_X", String.valueOf(container_from_top_animate.getX()));
                    Log.e("POSITION_Y", String.valueOf(container_from_top_animate.getY()));
                    if (container_from_top_animate.getY() == 390.0) {
                        swipeup[0] = true;
                        container_from_top_animate.animate().translationYBy(-200).setDuration(400);
                        container_from_visivility.animate().alpha(0).setDuration(500);
                    }
                }
                Log.e("BALDESH", "UP");
            }
        });

        b_spine.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeDown() {
                if (swipeup[0]) {
                    Log.e("POSITION_X", String.valueOf(container_from_top_animate.getX()));
                    Log.e("POSITION_Y", String.valueOf(container_from_top_animate.getY()));
                    if (container_from_top_animate.getY() == 190.0) {
                        swipeup[0] = false;
                        container_from_top_animate.animate().translationYBy(200).setDuration(400);
                        container_from_visivility.animate().alpha(1).setDuration(500);
                    }
                }
                Log.e("BALDESH", "DOWN");
            }

            @Override
            public void onSwipeUp() {
                if (!swipeup[0]) {

                    Log.e("POSITION_X", String.valueOf(container_from_top_animate.getX()));
                    Log.e("POSITION_Y", String.valueOf(container_from_top_animate.getY()));
                    if (container_from_top_animate.getY() == 390.0) {
                        swipeup[0] = true;
                        container_from_top_animate.animate().translationYBy(-200).setDuration(400);
                        container_from_visivility.animate().alpha(0).setDuration(500);
                    }
                }
                Log.e("BALDESH", "UP");
            }
        });

        b_torso.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeDown() {
                if (swipeup[0]) {
                    Log.e("POSITION_X", String.valueOf(container_from_top_animate.getX()));
                    Log.e("POSITION_Y", String.valueOf(container_from_top_animate.getY()));
                    if (container_from_top_animate.getY() == 190.0) {
                        swipeup[0] = false;
                        container_from_top_animate.animate().translationYBy(200).setDuration(400);
                        container_from_visivility.animate().alpha(1).setDuration(500);
                    }
                }
                Log.e("BALDESH", "DOWN");
            }

            @Override
            public void onSwipeUp() {
                if (!swipeup[0]) {

                    Log.e("POSITION_X", String.valueOf(container_from_top_animate.getX()));
                    Log.e("POSITION_Y", String.valueOf(container_from_top_animate.getY()));
                    if (container_from_top_animate.getY() == 390.0) {
                        swipeup[0] = true;
                        container_from_top_animate.animate().translationYBy(-200).setDuration(400);
                        container_from_visivility.animate().alpha(0).setDuration(500);
                    }
                }
                Log.e("BALDESH", "UP");
            }
        });

        b_legs.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeDown() {
                if (swipeup[0]) {
                    Log.e("POSITION_X", String.valueOf(container_from_top_animate.getX()));
                    Log.e("POSITION_Y", String.valueOf(container_from_top_animate.getY()));
                    if (container_from_top_animate.getY() == 190.0) {
                        swipeup[0] = false;
                        container_from_top_animate.animate().translationYBy(200).setDuration(400);
                        container_from_visivility.animate().alpha(1).setDuration(500);
                    }
                }
                Log.e("BALDESH", "DOWN");
            }

            @Override
            public void onSwipeUp() {
                if (!swipeup[0]) {

                    Log.e("POSITION_X", String.valueOf(container_from_top_animate.getX()));
                    Log.e("POSITION_Y", String.valueOf(container_from_top_animate.getY()));
                    if (container_from_top_animate.getY() == 390.0) {
                        swipeup[0] = true;
                        container_from_top_animate.animate().translationYBy(-200).setDuration(400);
                        container_from_visivility.animate().alpha(0).setDuration(500);
                    }
                }
                Log.e("BALDESH", "UP");
            }
        });



        container_from_top_animate.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeDown() {
                if (swipeup[0]) {
                    Log.e("POSITION_X", String.valueOf(container_from_top_animate.getX()));
                    Log.e("POSITION_Y", String.valueOf(container_from_top_animate.getY()));
                    if (container_from_top_animate.getY() == 190.0) {
                        swipeup[0] = false;
                        container_from_top_animate.animate().translationYBy(200).setDuration(400);
                        container_from_visivility.animate().alpha(1).setDuration(500);
                    }
                }
                Log.e("BALDESH", "DOWN");
            }

            @Override
            public void onSwipeUp() {
                if (!swipeup[0]) {

                    Log.e("POSITION_X", String.valueOf(container_from_top_animate.getX()));
                    Log.e("POSITION_Y", String.valueOf(container_from_top_animate.getY()));
                    if (container_from_top_animate.getY() == 390.0) {
                        swipeup[0] = true;
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