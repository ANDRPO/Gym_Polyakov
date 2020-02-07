package com.example.gym_polyakov.fragmentsplan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gym_polyakov.R;

import java.util.List;
import java.util.Map;

public class grid_view_adapter extends ArrayAdapter<Map> {

    private LayoutInflater inflater;
    private int item;
    private int progress;


    public grid_view_adapter(@NonNull Context context, int resource, int progress) {
        super(context, resource);
        inflater = LayoutInflater.from(context);
        this.item = resource;
        this.progress = progress;
    }

    @Override
    public int getCount() {
        return 60;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = inflater.inflate(item, null);
        Button b_number = view.findViewById(R.id.button_grid);
        if(progress > position){
            b_number.setActivated(false);
            b_number.setClickable(false);
            b_number.setTextColor(Color.WHITE);
            b_number.setBackground(view.getContext().getDrawable(R.drawable.disable_button_grid));
        }
        else if(progress < position){
            b_number.setActivated(false);
            b_number.setClickable(false);
        }
        b_number.setText(String.valueOf(position+1));
        return view;
    }
}
