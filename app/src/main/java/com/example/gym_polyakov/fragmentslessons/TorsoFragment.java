package com.example.gym_polyakov.fragmentslessons;

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

import com.example.gym_polyakov.My_Custom_Adapter;
import com.example.gym_polyakov.Network;
import com.example.gym_polyakov.R;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TorsoFragment extends Fragment {

    List<String> urls = new ArrayList<>();

    public TorsoFragment(List<String> urls) {
        for (int i = 18; i < 24; i++) {
            this.urls.add(urls.get(i));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_torso, null);
        ListView listView = view.findViewById(R.id.list_torso);

        My_Custom_Adapter my_custom_adapter = new My_Custom_Adapter(view.getContext(), R.layout.item_listview_gym, urls);
        listView.setAdapter(my_custom_adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urls.get(position).replace("\"", "")));
                startActivity(browserIntent);
            }
        });

        return view;
    }
}
