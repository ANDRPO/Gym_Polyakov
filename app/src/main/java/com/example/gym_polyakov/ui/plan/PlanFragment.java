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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gym_polyakov.OnSwipeTouchListener;
import com.example.gym_polyakov.R;

public class PlanFragment extends Fragment {

    private View container_from_top_animate;
    private View container_from_visivility;
    private boolean swipeup = false;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plan, null);

        container_from_top_animate = view.findViewById(R.id.view_container_profile_HSTL);
        container_from_visivility = view.findViewById(R.id.fragment_off);

        container_from_top_animate.setOnTouchListener(new OnSwipeTouchListener(getContext()){
            @Override
            public void onSwipeDown(){
                if(swipeup) {
                    Log.e("POSITION_X", String.valueOf(container_from_top_animate.getX()));
                    Log.e("POSITION_Y", String.valueOf(container_from_top_animate.getY()));
                    if(container_from_top_animate.getY() == 190.0) {
                        swipeup = false;
                        container_from_top_animate.animate().translationYBy(200).setDuration(400);
                        container_from_visivility.animate().alpha(1).setDuration(500);
                    }
                }
                Log.e("BALDESH", "DOWN");
            }

            @Override
            public void onSwipeUp(){
                if(!swipeup) {

                    Log.e("POSITION_X", String.valueOf(container_from_top_animate.getX()));
                    Log.e("POSITION_Y", String.valueOf(container_from_top_animate.getY()));
                    if(container_from_top_animate.getY() == 390.0) {
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