package com.example.gym_polyakov.fragmentsplan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gym_polyakov.R;

import java.util.List;
import java.util.Map;

public class grid_view_adapter extends ArrayAdapter<Map> {

    private LayoutInflater inflater;
    private int item;
    private int count;


    public grid_view_adapter(@NonNull Context context, int resource, @NonNull int count) {
        super(context, resource, count);
        inflater = LayoutInflater.from(context);
        this.item = resource;
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = inflater.inflate(item, null);
        Button b_number = view.findViewById(R.id.button_grid);
        b_number.setText(String.valueOf(position+1));
        return view;
    }
}
