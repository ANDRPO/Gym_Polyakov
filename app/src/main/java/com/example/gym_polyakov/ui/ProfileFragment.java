package com.example.gym_polyakov.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gym_polyakov.Gym_service;
import com.example.gym_polyakov.Network;
import com.example.gym_polyakov.R;
import com.example.gym_polyakov.SignIn;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    public static boolean switch_bool = false;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_profile, null);
        TextView tv_weight = view.findViewById(R.id.tv_profile_weight);
        TextView tv_height = view.findViewById(R.id.tv_profile_height);
        TextView tv_genderTRANS = view.findViewById(R.id.gender_profile);

        tv_weight.setText((int) getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getFloat("weight", 0) + "\nHeight");
        tv_height.setText((int) getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getFloat("height", 0) + "\nWeight");
        View b_biomtric = view.findViewById(R.id.call_alert_edit_profile);
        Button b_signout = view.findViewById(R.id.b_signout_profile);

        final TextView tv_TR = view.findViewById(R.id.tv_training_rest);
        tv_TR.setText(getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt("TRAINING_TIME", 0) + "sec");
        View button_trining = view.findViewById(R.id.training_rest);
        button_trining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final NumberPicker numberPicker = new NumberPicker(getContext());

                numberPicker.setMinValue(0);
                numberPicker.setMaxValue(300);

                builder.setView(numberPicker);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putInt("TRAINING_TIME", numberPicker.getValue()).apply();
                        tv_TR.setText(getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getInt("TRAINING_TIME", 0) + "sec");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
            }
        });



        if (getActivity().getSharedPreferences("Settings",Context.MODE_PRIVATE).getBoolean("male", true)) {
            tv_genderTRANS.setText("female");
        }else{
            tv_genderTRANS.setText("male");
        }
        Switch sw = view.findViewById(R.id.switch_profile);

        if(getActivity().getApplicationContext().getSharedPreferences("Settings", Context.MODE_PRIVATE).getBoolean("checkN", false)){
            sw.setChecked(true);
        }

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("CHECKED", String.valueOf(isChecked));
                getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit().putBoolean("checkN", isChecked).apply();
                getActivity().startService(new Intent(getActivity(), Gym_service.class));
            }
        });

        b_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Network.getInstance().getApi().API_sign_out(getActivity().getSharedPreferences("String", Context.MODE_PRIVATE).getString("username", "defuser")).enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getActivity(), "Выход", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), SignIn.class));
                                getActivity().finish();
                            } else {
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

        b_biomtric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflaterAlert = getActivity().getLayoutInflater();

                builder.setView(inflaterAlert.inflate(R.layout.alert_edit_profile, null))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText et_weight = ((AlertDialog)dialog).findViewById(R.id.weight_alert);
                                EditText et_height = ((AlertDialog)dialog).findViewById(R.id.height_alert);
                                try {
                                    if(!et_height.getText().toString().isEmpty() && !et_weight.getText().toString().isEmpty()){
                                        Network.getInstance().getApi().API_editprofile(
                                                getActivity().getSharedPreferences("Settings",Context.MODE_PRIVATE).getString("token","0"),
                                                et_weight.getText().toString(),
                                                et_height.getText().toString())
                                                .enqueue(new Callback<JsonElement>() {
                                            @Override
                                            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                                                if (response.isSuccessful()) {
                                                    Toast.makeText(getActivity(),"Данные успешно обновлены", Toast.LENGTH_SHORT).show();
                                                }
                                                else{
                                                    Toast.makeText(getActivity(),"Ошибка при подключении", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<JsonElement> call, Throwable t) {
                                                Toast.makeText(getActivity(),"Ошибка при подключении", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                    else{
                                        Toast.makeText(getActivity(),"Не все поля заполнены", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(getActivity(),"Ошибка при подключении", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
                            }
                        }).create().setCanceledOnTouchOutside(true);
                builder.show();
            }
        });
        return view;
    }
}