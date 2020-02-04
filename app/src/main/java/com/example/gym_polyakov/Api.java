package com.example.gym_polyakov;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @POST("signin")
    Call<JsonElement> API_sign_in(
            @Query("username") String Username,
            @Query("password") String Password);

    @POST("signup")
    Call<JsonElement> API_sign_up(
            @Query("username") String Username,
            @Query("email") String Email,
            @Query("password") String Password,
            @Query("weight") String Weight,
            @Query("height") String Height);

    @POST("signout")
    Call<JsonElement> API_sign_out(
            @Query("username") String Username);

    @GET("lessons")
    Call<JsonElement> API_lessons();

    @POST("profile")
    Call<JsonElement> API_prifole(
            @Query("token") String Token);

    @POST("editeprofile")
    Call<JsonElement> API_editprofile(
            @Query("token") String Token,
            @Query("weight") String Weight,
            @Query("height") String Height);

}
