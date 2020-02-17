package com.example.gym_polyakov.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gym_polyakov.OnSwipeTouchListener;
import com.example.gym_polyakov.R;
import com.example.gym_polyakov.fragmentsplan.Fragment_Legs;
import com.example.gym_polyakov.fragmentsplan.Fragment_Spine;
import com.example.gym_polyakov.fragmentsplan.Fragment_grid;

public class PlanFragment extends Fragment {

    private View container_from_visivility;
    private ImageView b_hands;
    private ImageView b_spine;
    private ImageView b_torso;
    private ImageView b_legs;
    private float VISIBILITY_PLAN;

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plan, null);

        container_from_visivility = view.findViewById(R.id.fragment_off);
        b_hands = view.findViewById(R.id.b_hands);
        b_spine = view.findViewById(R.id.b_spine);
        b_torso = view.findViewById(R.id.b_torso);
        b_legs = view.findViewById(R.id.b_legs);

        ScrollView scrollView = view.findViewById(R.id.plan_scrollview);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.e("ScrollX", String.valueOf(scrollX));
                Log.e("ScrollY", String.valueOf(scrollY));
                VISIBILITY_PLAN = (((float) scrollY * 100) / 19800);
                Log.e("VISIBILITY", String.valueOf(VISIBILITY_PLAN));
                container_from_visivility.setAlpha(1 - VISIBILITY_PLAN);


            }
        });

        ((TextView) view.findViewById(R.id.tv_plan_minutes)).setText(Return_shared("minutes") + "\nMinutes");
        ((TextView) view.findViewById(R.id.tv_plan_minutes_visible)).setText(Return_shared("minutes") + "\nMinutes");
        ((TextView) view.findViewById(R.id.tv_plan_training)).setText(Return_shared("score") + "\nTraining");
        ((TextView) view.findViewById(R.id.tv_plan_training_visible)).setText(Return_shared("score") + "\nTraining");
        ((TextView) view.findViewById(R.id.tv_plan_kcal)).setText(Return_shared("kcal") + "\nKcal");
        ((TextView) view.findViewById(R.id.tv_plan_kcal_visible)).setText(Return_shared("kcal") + "\nKcal");

        b_hands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("PRESSED_HANDS", "TRUE");
                if (getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt("TRAINING_CHECK_TIME", 0) == 0)
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Fragment_grid(0)).commit();
                else
                    Toast.makeText(getActivity(), "Время ещё не пришло", Toast.LENGTH_SHORT).show();
            }
        });

        b_spine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("PRESSED_SPINE", "TRUE");
                if (getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt("TRAINING_CHECK_TIME", 0) == 0)
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Fragment_grid(1)).commit();
                else
                    Toast.makeText(getActivity(), "Время ещё не пришло", Toast.LENGTH_SHORT).show();
            }
        });

        b_torso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt("TRAINING_CHECK_TIME", 0) == 0) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Fragment_grid(2)).commit();
                } else {
                    Toast.makeText(getActivity(), "Время ещё не пришло", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt("TRAINING_CHECK_TIME", 0) == 0) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Fragment_grid(3)).commit();
                } else {
                    Toast.makeText(getActivity(), "Время ещё не пришло", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public int Return_shared(String a) {
        return getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt(a, 0);
    }
}