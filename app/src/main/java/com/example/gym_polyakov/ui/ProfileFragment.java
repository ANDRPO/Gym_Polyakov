package com.example.gym_polyakov.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gym_polyakov.Network;
import com.example.gym_polyakov.R;
import com.example.gym_polyakov.SignIn;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_profile, null);
        Button b_signout = view.findViewById(R.id.b_signout_profile);
        b_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Network.getInstance().getApi().API_sign_out(getActivity().getSharedPreferences("String", Context.MODE_PRIVATE).getString("username", "defuser")).enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(getActivity(), "Выход", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), SignIn.class));
                                getActivity().finish();
                            }
                            else{
                                Toast.makeText(getActivity(), "Ошибка при подключении", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {
                            Toast.makeText(getActivity(), "Ошибка при подключении", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Ошибка при подключении", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}