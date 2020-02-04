package com.example.gym_polyakov.fragmentslessons;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gym_polyakov.My_Custom_Adapter;
import com.example.gym_polyakov.Network;
import com.example.gym_polyakov.R;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_spine, null);

        final ListView listView = view.findViewById(R.id.list_spine);
        final List<String> urls = new ArrayList<>();

        try {
            Network.getInstance().getApi().API_lessons().enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                    if (urls.isEmpty()) {
                        if (response.isSuccessful()) {
                            for(int i = 24; i < 32; i++){
                                urls.add(response.body().getAsJsonArray().get(i).getAsJsonObject().get("url").toString());
                                Log.e("LESSONS", response.body().getAsJsonArray().get(i).getAsJsonObject().get("url").toString());
                            }
                        }
                        else{
                            Toast.makeText(view.getContext(), "Не удалось загрузить уроки", Toast.LENGTH_SHORT).show();
                        }
                    }
                    My_Custom_Adapter my_custom_adapter = new My_Custom_Adapter(view.getContext(), R.layout.item_listview_gym, urls);
                    listView.setAdapter(my_custom_adapter);

                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }
}
