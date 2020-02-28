package com.example.gym_polyakov;

import com.google.gson.JsonElement;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

    @POST("gpx")
    Call<JsonElement> API_outgpx(
           @Body RequestBody body);

    @POST("getgpx")
    Call<List<Gpx_getset>> API_getgpx(
            @Query("token") String token);

    @POST("deletegpx")
    Call<JsonElement> API_deletegpx(
            @Query("token") String Token,
            @Query("run_id") String Run_id);

    @GET("{url}")
    Call<JsonElement> API_getfile(
            @Path("url") String url_text);
}
