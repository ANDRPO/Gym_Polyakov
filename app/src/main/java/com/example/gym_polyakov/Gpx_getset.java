package com.example.gym_polyakov;

import java.io.File;

import okhttp3.MultipartBody;

public class Gpx_getset {
    String Id;
    String user_id;
    String urlGpx;
    String time;
    String date;

    public Gpx_getset(String id, String user_id, String urlGpx, String time, String date) {
        Id = id;
        this.user_id = user_id;
        this.urlGpx = urlGpx;
        this.time = time;
        this.date = date;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUrlGpx() {
        return urlGpx;
    }

    public void setUrlGpx(String urlGpx) {
        this.urlGpx = urlGpx;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
//     "Id": "7",
//             "user_id": "138",
//             "urlGpx": "http://gym.areas.su/up/20200220-5e4e15ef234eapostfile.txt",
//             "time": "4",
//             "date": "14.12.2000"

}
