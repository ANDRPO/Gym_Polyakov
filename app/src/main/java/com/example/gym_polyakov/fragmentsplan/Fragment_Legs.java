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

import com.example.gym_polyakov.Json_in_out_file;
import com.example.gym_polyakov.Network;
import com.example.gym_polyakov.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.maps.android.SphericalUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Legs extends Fragment implements LocationListener {

    private final static String FILE_SAVEGPX = "History.txt";
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
        final View view = inflater.inflate(R.layout.fragment_legs_plan, container, false);
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
                Json_in_out_file JIOF = new Json_in_out_file(latLngs, tv_distance.getText().toString(), chronometer.getText().toString());
                String jsonlist = new Gson().toJson(JIOF);
                Log.e("CHRNOMETR GET TEXT", chronometer.getText().toString());
                Log.e("OUT JSON", jsonlist);
                setFileSavegpx(jsonlist);
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

    private long getTimeChronometer(Chronometer chronometer) {
        return SystemClock.elapsedRealtime() - chronometer.getBase();
    }

    private void getFileSavegpx() {
        try {
            FileInputStream fileInput = getActivity().openFileInput(FILE_SAVEGPX);
            InputStreamReader reader = new InputStreamReader(fileInput);
            BufferedReader buffer = new BufferedReader(reader);
            StringBuffer strBuffer = new StringBuffer();
            String lines = buffer.readLine();
            Log.e("GETFULESAVEGPX", lines);
            fileInput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFileSavegpx(String text) {
        FileOutputStream gpx_file = null;
        try {
            gpx_file = getActivity().openFileOutput(FILE_SAVEGPX, Context.MODE_PRIVATE);
            gpx_file.write(text.getBytes());
            gpx_file.close();
            @SuppressLint("SdCardPath") File file = new File("/data/user/0/com.example.gym_polyakov/files/History.txt");
            RequestBody requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file);

            Log.e("FILE", file.toString());
            Log.e("GPX_FILE", gpx_file.toString());

            Date currentDate = new Date();
            String dateText = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(currentDate);
            String timeText = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(currentDate);

            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("token", getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("token", "0"))
                    .addFormDataPart("time", timeText)
                    .addFormDataPart("date", dateText)
                    .addFormDataPart("name", "name.txt", requestFile).build();

            Network.getInstance().getApi().API_outgpx(requestBody).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Fragment_RunList()).commit();
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new Fragment_RunList()).commit();
                }

            });
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.nav_host_fragment, new Fragment_RunList()).commit();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
