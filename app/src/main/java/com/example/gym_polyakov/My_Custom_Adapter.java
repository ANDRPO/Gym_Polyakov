package com.example.gym_polyakov;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

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
        ImageView imageView = view.findViewById(R.id.image_in_list);
        Picasso.get().load("https://img.youtube.com/vi" + list.get(position).substring(17).concat("/mqdefault.jpg").replace("\"","")).into(imageView);
        Log.e("LESSONS URLS", list.get(position).substring(17));
        Log.e("LESSONS IMAGE", "https://img.youtube.com/vi" + list.get(position).substring(17).concat("/mqdefault.jpg").replace("\"", ""));
        return view;
    }

}
