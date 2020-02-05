package com.example.gym_polyakov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.gym_polyakov.DataBase.AppDataBase;
import com.example.gym_polyakov.DataBase.Users;
import com.google.gson.JsonElement;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        final AppDataBase db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "database").allowMainThreadQueries().build();

        final Button b_signup_activity = findViewById(R.id.b_signup_activity);
        Button b_skip = findViewById(R.id.b_skip);
        final Button b_signin = findViewById(R.id.b_signin);
        final View relat_username = findViewById(R.id.view_username);
        final View relat_password = findViewById(R.id.view_password);
        final EditText et_username = findViewById(R.id.et_signin_username);
        final EditText et_password = findViewById(R.id.et_signin_password);
        final ImageView imageView = findViewById(R.id.im_homegym_signin);
        final TextView textView = findViewById(R.id.tv_homegym_signin);

        b_signup_activity.setText(Html.fromHtml("<u>Sign Up</u>"));
        b_skip.setText(Html.fromHtml("<u>Skip</u>"));

        b_signup_activity.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {

                relat_username.animate().alpha(0).translationYBy(-100).setDuration(1000);
                relat_password.animate().alpha(0).translationYBy(-40).setDuration(1000);
                b_signup_activity.animate().alpha(0).setDuration(1000);
                b_signin.animate().alpha(0.1f).translationYBy(100).setDuration(800);
                imageView.animate().translationYBy(-82).setDuration(1000);
                textView.animate().translationYBy(-82).setDuration(1000);

                relat_password.setClickable(false);
                relat_username.setClickable(false);
                b_signin.setClickable(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SignIn.this, SignUp.class));
                        Animatoo.animateFade(SignIn.this);
                        finish();
                    }
                }, 800);

            }
        });


        b_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if (!et_password.getText().toString().isEmpty() && !et_username.getText().toString().isEmpty()) {
                    Users users = new Users();
                    if(db.users_dao().getUser(et_username.getText().toString(), et_password.getText().toString()) != null) {
                        users = db.users_dao().getUser(et_username.getText().toString(), et_password.getText().toString());
                        startActivity(new Intent(getApplicationContext(), BottomNavigationMenu.class));
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Такого пользователя не существует", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Не все поля заполнены", Toast.LENGTH_SHORT).show();
                }*/

                if (!et_username.getText().toString().isEmpty() && !et_password.getText().toString().isEmpty()) {
                    Network.getInstance().getApi().API_sign_in(et_username.getText().toString(), et_password.getText().toString()).enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                            try {
                                if (response.isSuccessful()) {
                                    if(response.body().toString().contains("token")){
                                        SharedPreferences preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("token",response.body().getAsJsonObject().get("notice").getAsJsonObject().get("token").toString());
                                        editor.putString("username",et_username.getText().toString());
                                        editor.apply();
                                        Log.e("RESPONCE TOKEN", response.body().getAsJsonObject().get("notice").getAsJsonObject().get("token").toString());
                                        startActivity(new Intent(getApplicationContext(), BottomNavigationMenu.class));
                                        finish();
                                    }
                                    else if(response.body().toString().contains("active")){
                                        Toast.makeText(getApplicationContext(),"Пользователь уже авторизован",Toast.LENGTH_SHORT).show();
                                    }
                                    else if(response.body().toString().contains("Error username or password")){
                                        Toast.makeText(getApplicationContext(),"Неверные логин или пароль",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),"Произошла ошибка", Toast.LENGTH_SHORT).show();
                                        Log.e("ERROR RESPONCE SIGN IN", response.body().toString());
                                    }
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Произошла ошибка", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                Log.e("EXCEPTION RESPONCE SIGN IN", e.toString());
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Произошла ошибка", Toast.LENGTH_SHORT).show();
                            Log.e("ERROR RESPONCE SIGN IN", call.toString() + " AND " + t.toString());
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"Заполнены не все поля", Toast.LENGTH_SHORT).show();
                }


            }
        });

        b_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    /*for (Users users : db.users_dao().getAll()) {
                        Log.e("DATABASE ID", String.valueOf(users.id));
                        Log.e("DATABASE USERNAME", users.username);
                        Log.e("DATABASE PASSWORD", users.password);
                        Log.e("DATABASE EMAIL", users.email);
                        Log.e("DATABASE GENDER", String.valueOf(users.gender));
                        Log.e("DATABASE HEIGHT", String.valueOf(users.height));
                        Log.e("DATABASE WEIGHT", String.valueOf(users.weight));

                    }*/

                    if (!et_username.getText().toString().isEmpty()) {
                        Network.getInstance().getApi().API_sign_out(et_username.getText().toString()).enqueue(new Callback<JsonElement>() {
                            @Override
                            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                Log.e("SUCCESS OUT", "SUCCESS OUT");
                            }

                            @Override
                            public void onFailure(Call<JsonElement> call, Throwable t) {

                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
