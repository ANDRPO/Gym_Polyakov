package com.example.gym_polyakov;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

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
                },800);

            }
        });



        Button b_sign_in = findViewById(R.id.b_signin);
        b_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Network.getInstance().getApi().API_sign_in(et_username.getText().toString(), et_password.getText().toString()).enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        if(response.body().getAsString().contains("active")){
                            Toast.makeText(getApplicationContext(),"Пользователь уже авторизован",Toast.LENGTH_SHORT);
                        }
                        else{

                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {

                    }
                });
            }
        });



    }
}
