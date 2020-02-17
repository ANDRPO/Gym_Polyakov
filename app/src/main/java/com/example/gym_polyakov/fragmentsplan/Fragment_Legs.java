package com.example.gym_polyakov.fragmentsplan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gym_polyakov.R;
import com.example.gym_polyakov.ui.PlanFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Fragment_Legs extends Fragment implements LocationListener {


    private int progress;
    private int count;
    private List<LatLng> latLngs = new ArrayList<>();
    private TextView tv_distance;
    private Button b_stop;
    private Chronometer chronometer;
    boolean check = true;

    Fragment_Legs(int progress) {
        this.progress = progress + 1;
        this.count = progress + 1;
    }

    @SuppressLint("MissingPermission")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_legs_plan, container, false);
        tv_distance = view.findViewById(R.id.distance);
        chronometer = view.findViewById(R.id.chronometer_run);
        b_stop = view.findViewById(R.id.b_stop_legs);
        b_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editor_shared("score", progress);
                Editor_shared("minutes", (int) (getTimeChronometer(chronometer) / 60000));
                getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putInt("LEGS", count).apply();
                chronometer.stop();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new PlanFragment()).commit();
            }
        });
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onLocationChanged(Location location) {
        if (check) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            check = false;
        }
        latLngs.add(new LatLng(location.getLatitude(), location.getLongitude()));
        Log.e("DISTANCE", String.valueOf(SphericalUtil.computeLength(latLngs)));
        if (progress * 100 - SphericalUtil.computeLength(latLngs) >= 0)
            tv_distance.setText(new DecimalFormat("#0.00").format((progress * 100 - SphericalUtil.computeLength(latLngs)) / 1000) + " km");
        else {
            tv_distance.setText("0,0 km");
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private int Return_shared(String a) {
        return getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt(a, 0);
    }

    private void Editor_shared(String a, int b) {
        getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putInt(a, Return_shared(a) + b).apply();
    }

    private long getTimeChronometer(Chronometer chronometer){
        return SystemClock.elapsedRealtime() -   chronometer.getBase();
    }
}
