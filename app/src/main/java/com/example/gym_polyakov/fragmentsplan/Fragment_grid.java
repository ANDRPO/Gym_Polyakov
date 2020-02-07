package com.example.gym_polyakov.fragmentsplan;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gym_polyakov.R;

public class Fragment_grid extends Fragment {

    int type;

    public Fragment_grid(int type){
        this.type = type;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, null);
        TextView title = view.findViewById(R.id.tv_grid_title);
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setProgress(10);

        Log.e("TYPE", String.valueOf(type));
        switch (type){
            case 0:
                title.setText("Push-up");
                break;
            case 2:
                title.setText("Sit-up");
                break;
        }

        Drawable draw = getResources().getDrawable(R.drawable.cusom_progress_bar);
// set the drawable as progress drawable
        progressBar.setProgressDrawable(draw);

        GridView gridView = view.findViewById(R.id.gridview);
        grid_view_adapter adapter = new grid_view_adapter(view.getContext(), R.layout.item_frid, 60);
        gridView.setAdapter(adapter);

        return view;
    }
}
