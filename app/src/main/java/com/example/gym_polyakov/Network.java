package com.example.gym_polyakov;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    private static Network mInstance;
    private Retrofit retrofit;
    private String BASE_URL = "http://gym.areas.su/";

    public static Network getInstance(){
        if(mInstance == null){
            mInstance = new Network();
        }
        return mInstance;
    }

    public Network(){
        this.retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
