package com.example.gym_polyakov;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class Json_in_out_file {
    List<LatLng> list;
    String distance;
    String time;

    public Json_in_out_file(List<LatLng> list, String distance, String time) {
        this.list = list;
        this.distance = distance;
        this.time = time;
    }

    public List<LatLng> getList() {
        return list;
    }

    public void setList(List<LatLng> list) {
        this.list = list;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
