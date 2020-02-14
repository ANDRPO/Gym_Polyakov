package com.example.gym_polyakov.fragmentsplan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.gym_polyakov.R;

public class Fragment_grid extends Fragment {

    private int type;
    private int progress = 0;

    public Fragment_grid(int type){
        this.type = type;
    }


    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, null);
        TextView title = view.findViewById(R.id.tv_grid_title);
        TextView tv_procent = view.findViewById(R.id.tv_procent);
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);


        Log.e("TYPE", String.valueOf(type));
        switch (type){
            case 0:
                title.setText("Push-up");
                progress = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt("PUSHUP", 0);
                break;
            case 1:
                title.setText("Spine");
                progress = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt("SPINE", 0);
                break;
            case 2:
                title.setText("Sit-up");
                progress = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt("SITUP", 0);
                break;
        }
        progressBar.setMax(60);
        progressBar.setProgress(progress);
        tv_procent.setText(procentProgress(progress) + "%");
        if(progress >= 30){
            tv_procent.setTextColor(Color.WHITE);
        }
        Drawable draw = getResources().getDrawable(R.drawable.cusom_progress_bar);
        progressBar.setProgressDrawable(draw);
        FragmentActivity fragment_for_adapter = getActivity();
        GridView gridView = view.findViewById(R.id.gridview);
        grid_view_adapter adapter = new grid_view_adapter(view.getContext(), R.layout.item_frid, progress, fragment_for_adapter, type);
        gridView.setAdapter(adapter);

        return view;
    }

    private int procentProgress(int a){
        return  (a*100)/60;
    }
}
