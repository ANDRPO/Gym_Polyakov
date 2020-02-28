package com.example.gym_polyakov;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Custom_adapter_runlist extends ArrayAdapter<String> {

    LayoutInflater inflater;
    int item;
    List<String> list;


    public Custom_adapter_runlist(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        item = resource;
        list = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") final View view = inflater.inflate(item, null);
        final TextView distance = view.findViewById(R.id.distance_runlist);
        final TextView time = view.findViewById(R.id.time_runlist);
        GetTextFile.getInstance().getApi().API_getfile(list.get(position)).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                distance.append(response.body().getAsJsonObject().get("distance").toString().replace("\"",""));
                time.append(response.body().getAsJsonObject().get("time").toString().replace("\"",""));
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });

        return view;
    }
}
