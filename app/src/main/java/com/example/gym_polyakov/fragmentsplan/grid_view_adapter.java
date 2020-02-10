package com.example.gym_polyakov.fragmentsplan;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.gym_polyakov.R;

import java.util.Map;

import static android.content.Context.NOTIFICATION_SERVICE;

public class grid_view_adapter extends ArrayAdapter<Map> {

    private LayoutInflater inflater;
    private int item;
    private int progress;
    private FragmentActivity fragmentActivity;
    private int type;


    grid_view_adapter(@NonNull Context context, int resource, int progress, FragmentActivity fragmentActivity, int type) {
        super(context, resource);
        inflater = LayoutInflater.from(context);
        this.item = resource;
        this.type = type;
        this.progress = progress;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public int getCount() {
        return 60;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") final View view = inflater.inflate(item, parent, false);
        TextView tv_number = view.findViewById(R.id.tv_grid_item);
        if (progress > position) {

            tv_number.setTextColor(Color.WHITE);
            tv_number.setBackground(view.getContext().getDrawable(R.drawable.disable_button_grid));
        } else if (progress < position) {
            tv_number.setBackground(view.getContext().getDrawable(R.drawable.disable_button_grid_2));
        } else {

            tv_number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (type) {
                        case 0:
                            fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Fragment_Push_Up(progress)).commit();
                            break;
                        case 2:
                            fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Fragment_Sit_Up(progress)).commit();
                            break;
                    }
                }
            });
        }
        tv_number.setText(String.valueOf(position + 1));
        return view;
    }
}
