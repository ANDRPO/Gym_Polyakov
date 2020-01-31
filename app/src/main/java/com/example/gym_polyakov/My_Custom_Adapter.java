package com.example.gym_polyakov;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class My_Custom_Adapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;
    private int item;
    private List<String> list;
    public My_Custom_Adapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        item = resource;
        list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(item,  null);
        return view;
    }
}
