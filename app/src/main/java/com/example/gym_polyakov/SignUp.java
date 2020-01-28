package com.example.gym_polyakov;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {


    public Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final EditText et_user = findViewById(R.id.login_signup);
        final EditText et_mail = findViewById(R.id.mail_signup);
        final EditText et_pass_first = findViewById(R.id.password_signup_first);
        final EditText et_pass_second = findViewById(R.id.password_signup_second);
        Button back = findViewById(R.id.b_back_signup);
        Button sign_up = findViewById(R.id.b_signup_signup);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_user.getText().toString().isEmpty() && !et_mail.getText().toString().isEmpty() && (et_pass_first.getText().toString().equals(et_pass_second.getText().toString())) && et_mail.getText().toString().contains("@") && !et_pass_first.getText().toString().isEmpty() && !et_pass_second.getText().toString().isEmpty()) {
                    Network.getInstance().getApi().API_sign_up(
                            et_user.getText().toString(),
                            et_mail.getText().toString(),
                            et_pass_first.getText().toString(),
                            String.valueOf(getSharedPreferences("Settings", Context.MODE_PRIVATE).getFloat("weight", 0)),
                            String.valueOf(getSharedPreferences("Settings", Context.MODE_PRIVATE).getFloat("height", 0))).enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            Log.e("SP1", String.valueOf(getSharedPreferences("Settings", Context.MODE_PRIVATE).getFloat("weight", 0)));
                            Log.e("SP2", String.valueOf(getSharedPreferences("Settings", Context.MODE_PRIVATE).getFloat("weight", 0)));
                            Log.e("TRIX", response.body().toString());
                            if (response.isSuccessful() && response.body().toString().contains("\"Success\"")) {
                                Network.getInstance().getApi().API_sign_out(et_user.getText().toString()).enqueue(new Callback<JsonElement>() {
                                    @Override
                                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                        if (response.body().toString().contains("User log out")) {
                                            Log.e("SUCCESS LOG OUT", response.toString());
                                            onBackPressed();
                                        } else if(response.body().toString().contains("User is not log in")){
                                            Log.e("SUCCESS NOT LOG OUT", response.toString());
                                            onBackPressed();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<JsonElement> call, Throwable t) {
                                        Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Произошла ошибка", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    if (et_user.getText().toString().isEmpty() || et_mail.getText().toString().isEmpty() || et_pass_first.getText().toString().isEmpty() || et_pass_second.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Не все поля заполнены", Toast.LENGTH_SHORT).show();
                    } else if (!et_mail.getText().toString().contains("@")) {
                        Toast.makeText(getApplicationContext(), "Некорректный E-mail", Toast.LENGTH_SHORT).show();
                    } else if (!et_pass_first.getText().toString().equals(et_pass_second.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }


    @Override
    public void onBackPressed() {
        View v_user = findViewById(R.id.signup_view_username);
        View v_mail = findViewById(R.id.signup_view_email);
        View v_pass_first = findViewById(R.id.signup_view_password_first);
        View v_pass_second = findViewById(R.id.signup_view_password_second);
        Button b_signup = findViewById(R.id.b_signup_signup);
        ImageView imageView = findViewById(R.id.im_homegym_signup);
        TextView textView = findViewById(R.id.tv_homegym_signup);
        v_user.animate().alpha(0).translationYBy(100).setDuration(1000);
        v_pass_first.animate().alpha(0).translationYBy(40).setDuration(1000);
        v_mail.animate().alpha(0).setDuration(800);
        v_pass_second.animate().alpha(0).setDuration(800);
        imageView.animate().translationYBy(82).setDuration(1000);
        textView.animate().translationYBy(82).setDuration(1000);
        b_signup.animate().alpha(0).translationYBy(-100).setDuration(1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(context, SignIn.class));
                Animatoo.animateFade(context);
                finish();
            }
        }, 800);

    }
}
