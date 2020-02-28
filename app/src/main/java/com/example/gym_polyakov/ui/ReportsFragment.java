package com.example.gym_polyakov.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym_polyakov.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ReportsFragment extends Fragment {


    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("InflateParams") final View view = inflater.inflate(R.layout.fragment_reports, null);
        final Button b_calendar = view.findViewById(R.id.b_calendar);
        RecyclerView recyclerView = view.findViewById(R.id.lv_reports);
        GraphView graphTraining = view.findViewById(R.id.graph_minutes);
        List<Get_Set_Day> dayList = new ArrayList<>();
        b_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(View.inflate(getContext(), R.layout.calendar_alert, null))
                        .setNegativeButton("CANCEL", null)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        final TextView tv_year = ((AlertDialog) dialog).findViewById(R.id.calendar_alert_year);
                        final TextView tv_day = ((AlertDialog) dialog).findViewById(R.id.calendar_alert_day);

                        String[] dayAndYear;
                        CalendarView calendarView = ((AlertDialog) dialog).findViewById(R.id.calendar_alert);
                        dayAndYear = convertDayYear(calendarView.getDate());

                        tv_day.setText(dayAndYear[0]);
                        tv_year.setText(dayAndYear[1]);

                        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                                String[] str;
                                Calendar calendar = new GregorianCalendar();
                                calendar.set(year, month, dayOfMonth);
                                str = convertDayYear(calendar.getTimeInMillis());
                                tv_year.setText(String.valueOf(year));
                                tv_day.setText(str[0]);
                            }
                        });
                    }
                });
                dialog.show();
            }
        });
        Date date = new Date();
        Log.e("Date", date.toString());
        Calendar calendar = new GregorianCalendar();
        Log.e("Calendar", calendar.getTime().toString());
        calendar.add(Calendar.DAY_OF_YEAR,-6);
        for(int i = 0; i < 7; i++){
            dayList.add(new Get_Set_Day(Integer.parseInt(calendar.getTime().toString().substring(8,10)),calendar.getTime().toString().substring(0,2)));
            Log.e("DAYLIST1", String.valueOf(dayList.get(i).getNumber()));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        BarGraphSeries<DataPoint> barGraphSeries = new BarGraphSeries<DataPoint>(new DataPoint[]{
            new DataPoint(0,1),
                new DataPoint(0,2),
                new DataPoint(1,3),
                new DataPoint(2,4)
        });
        graphTraining.setTitle("Training");
        graphTraining.addSeries(barGraphSeries);
        Custom_adapter_numberDay adapter = new Custom_adapter_numberDay(dayList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private String[] convertDayYear(long millis) {
        String[] str = new String[2];
        StringBuilder strINSERT = new StringBuilder();
        Date date = new Date();
        date.setTime(millis);
        strINSERT.append(date.toString().substring(0, 11)).insert(3, ",");
        str[0] = strINSERT.toString().toUpperCase();
        str[1] = date.toString().substring(date.toString().length() - 4);
        return str;
    }
}