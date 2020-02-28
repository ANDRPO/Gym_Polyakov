package com.example.gym_polyakov.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym_polyakov.R;

import java.util.List;

public class Custom_adapter_numberDay extends RecyclerView.Adapter<Custom_adapter_numberDay.MyViewHolder> {

    List<Get_Set_Day> list;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View v;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
        }
    }

    public Custom_adapter_numberDay(List<Get_Set_Day> list){
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar,parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ((TextView)holder.itemView.findViewById(R.id.day_number_reports)).setText(list.get(position).getNumber() + "");
        ((TextView)holder.itemView.findViewById(R.id.day_name_reports)).setText(list.get(position).getDay());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
