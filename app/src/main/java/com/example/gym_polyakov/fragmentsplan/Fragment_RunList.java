package com.example.gym_polyakov.fragmentsplan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.example.gym_polyakov.Custom_adapter_runlist;
import com.example.gym_polyakov.Gpx_getset;
import com.example.gym_polyakov.Network;
import com.example.gym_polyakov.R;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_RunList extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_runlist, container, false);
        final List<String> Urls = new ArrayList<>();
        final List<Gpx_getset> gpx_getsets = new ArrayList<>();
        final ListView listView = view.findViewById(R.id.lv_run);
        Network.getInstance().getApi().API_getgpx(getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("token", "0")).enqueue(new Callback<List<Gpx_getset>>() {
            @Override
            public void onResponse(Call<List<Gpx_getset>> call, Response<List<Gpx_getset>> response) {

                try {
                    if (response.isSuccessful()) {
                        gpx_getsets.addAll(response.body());
                        Log.e("GPX_GETSET", gpx_getsets.get(0).getUrlGpx());
                        for (Gpx_getset gpx : gpx_getsets) {
                            Urls.add(gpx.getUrlGpx().substring(23));
                        }
                        final Custom_adapter_runlist adapter = new Custom_adapter_runlist(getActivity(), R.layout.item_runlist, Urls);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setMessage("Delete this training?")
                                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Network.getInstance().getApi().API_deletegpx(getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("token", "0"), gpx_getsets.get(position).getId()).enqueue(new Callback<JsonElement>() {
                                                    @Override
                                                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                                        if (response.isSuccessful()) {
                                                            Toast.makeText(getActivity(), "Delete", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<JsonElement> call, Throwable t) { }
                                                });
                                                Urls.remove(position);
                                                gpx_getsets.remove(position);
                                                //adapter.remove(adapter.getItem(position));
                                                adapter.notifyDataSetChanged();
                                            }
                                        })
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        }).create().show();
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "Connect error", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Connect error catch", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Gpx_getset>> call, Throwable t) {

            }
        });


        return view;
    }


}
