package com.example.gym_polyakov.ui.lessons;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.gym_polyakov.PageAdapter;
import com.example.gym_polyakov.R;
import com.google.android.material.tabs.TabLayout;

public class LessonsFragment extends Fragment {

    private TabLayout tabLayout;
    private PageAdapter pageAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lessons, null);

        ViewPager viewPager = view.findViewById(R.id.view_pager_lessons);
        tabLayout = view.findViewById(R.id.tab_layout);

        pageAdapter = new PageAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        pageAdapter.addTitle("Hands");
        pageAdapter.addTitle("Spine");
        pageAdapter.addTitle("Torso");
        pageAdapter.addTitle("Legs");
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}