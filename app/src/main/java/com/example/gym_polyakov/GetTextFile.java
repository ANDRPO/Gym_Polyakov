package com.example.gym_polyakov;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetTextFile {
    private static GetTextFile mInstance;
    private Retrofit retrofit;
    private String BASE_URL = "";

    public static GetTextFile getInstance(){
        if(mInstance == null){
            mInstance = new GetTextFile();
        }
        return mInstance;
    }

    public GetTextFile(){
        this.retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
