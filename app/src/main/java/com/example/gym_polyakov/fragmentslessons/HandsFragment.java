package com.example.gym_polyakov.fragmentslessons;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gym_polyakov.BottomNavigationMenu;
import com.example.gym_polyakov.My_Custom_Adapter;
import com.example.gym_polyakov.Network;
import com.example.gym_polyakov.R;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class HandsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_hands, container, false);
        final ListView listView = view.findViewById(R.id.list_hands);
        final List<String> urls = new ArrayList<>();
        final My_Custom_Adapter[] my_custom_adapter = new My_Custom_Adapter[1];
        try {
            Network.getInstance().getApi().API_lessons().enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    if (urls.isEmpty()) {
                        if (response.isSuccessful()) {
                            for(int i = 0; i < 10; i++){
                                urls.add(response.body().getAsJsonArray().get(i).getAsJsonObject().get("url").toString());
                                Log.e("LESSONS", response.body().getAsJsonArray().get(i).getAsJsonObject().get("url").toString());
                            }
                        }
                        else{
                            Toast.makeText(view.getContext(), "Не удалось загрузить уроки", Toast.LENGTH_SHORT).show();
                        }
                    }

                    my_custom_adapter[0] = new My_Custom_Adapter(getContext(), R.layout.item_listview_gym, urls);
                    listView.setAdapter(my_custom_adapter[0]);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urls.get(position)));
                            startActivity(browserIntent);
                        }
                    });

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
